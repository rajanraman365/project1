package com.banking.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.banking.dtos.AccountDTO;
import com.banking.dtos.CreditCardDTO;
import com.banking.dtos.LoanDTO;
import com.banking.dtos.UserDTO;
import com.banking.exception.DuplicateRecordException;
import com.banking.form.CreditForm;
import com.banking.form.LoanForm;
import com.banking.service.AccountServiceInt;
import com.banking.service.AccountTypeServiceInt;
import com.banking.service.LoanServiceInt;
import com.banking.service.UserServiceInt;
import com.banking.util.DataUtility;

@RestController
public class LoanCtl extends BaseCtl {

	private Logger log = Logger.getLogger(LoanCtl.class.getName());

	protected static final String OP_PAYLOAN = "Pay Loan";
	@Autowired
	private AccountServiceInt accountServiceInt;
	@Autowired
	private AccountTypeServiceInt accountTypeServiceInt;
	@Autowired
	private UserServiceInt userServiceInt;
	@Autowired
	private LoanServiceInt service;

	@ModelAttribute
	public void preload(Model model) {

		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setStatus("OPEN");
		model.addAttribute("accountTypeList", accountServiceInt.getUserAccounts(accountDTO));
	}

	@GetMapping("/home/admin/loan")
	public ModelAndView display(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") LoanForm form, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loan");
		UserDTO user = (UserDTO) session.getAttribute("user");

		// Get Accounts
		model.addAttribute("userAccounts", accountServiceInt.list());
		if (form.getId() > 0) {
			LoanDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return mv;
	}

	@PostMapping("/home/admin/loan")
	public ModelAndView submit(@Valid @ModelAttribute("form") LoanForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loan");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/admin/loan");
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return mv;
				}

				LoanDTO bean = (LoanDTO) populateDTO(form.getDTO(), request);
				AccountDTO account = accountServiceInt.findBypk(form.getAccountId());
				bean.setAccount(account);
				bean.setAccountId(form.getAccountId());
				UserDTO userDTO = userServiceInt.findBypk(account.getUserId());
				bean.setUser(userDTO);
				bean.setUserId(userDTO.getId());

				Double si = service.calculateSimpleInterest(bean.getOriginalLoanAmount(), bean.getInterestRate(),
						bean.getTermLength());
				bean.setSimpleInterest(si);
				Double totalAmount = bean.getOriginalLoanAmount() + si;
				bean.setTotalAmount(totalAmount);
				Double monthlyPayment = service.calculateMonthlyPayment(bean.getOriginalLoanAmount(), si,
						bean.getTermLength());
				bean.setMonthlyPaymentAmount(monthlyPayment);
				bean.setPayableAmount(totalAmount);
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Loan Updated Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Loan Created Successfully!!!!");
				}
				model.addAttribute("monthlyPayment", monthlyPayment);
				model.addAttribute("si", si);
				return mv;
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return mv;
		}

		return mv;
	}

	@RequestMapping(value = "/home/admin/loans", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") LoanForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loanList");
		if (OP_RESET.equalsIgnoreCase(operation)) {
			return new ModelAndView("redirect:/home/admin/loans");
		}
		UserDTO user = (UserDTO) session.getAttribute("user");
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		LoanDTO dto = (LoanDTO) form.getDTO();
		dto.setUserId(user.getId());
		dto.setUser(user);
		List<LoanDTO> list = service.search(dto, pageNo, pageSize);
		List<LoanDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return mv;
	}

	@RequestMapping(value = "/home/user/loans", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchLoanList(@ModelAttribute("form") LoanForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userloanList");
		if (OP_RESET.equalsIgnoreCase(operation)) {
			return new ModelAndView("redirect:/home/user/loans");
		}
		UserDTO user = (UserDTO) session.getAttribute("user");

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		LoanDTO dto = (LoanDTO) form.getDTO();
		dto.setUserId(user.getId());
		dto.setUser(user);
		List<LoanDTO> list = service.search(dto, pageNo, pageSize);
		List<LoanDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return mv;
	}

	@GetMapping("/home/user/loan")
	public ModelAndView displayLoanPayment(@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long accountId, Long pId, @ModelAttribute("form") LoanForm form,
			HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("payloan");
		System.out.println("form id: " + id + "Account Id:  " + accountId);
		UserDTO user = (UserDTO) session.getAttribute("user");

		// Save account in session
		session.setAttribute("accountId", accountId);

		// Get Accounts
		model.addAttribute("userAccounts", accountServiceInt.list());
		if (form.getId() > 0) {
			LoanDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return mv;
	}

	@PostMapping("/home/user/loan/pay")
	public ModelAndView submitPayment(@Valid @ModelAttribute("form") LoanForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request, @RequestParam(required = false) Long id)
			throws DuplicateRecordException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("payloan");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/user/loans");
		}

		session.setAttribute("loanid", id);
		System.out.println("req:   " + request.getParameter("amount"));
		if (request.getParameter("amount").isEmpty()) {

			model.addAttribute("error", "Enter Valid Amount");
			return mv;

		}
		Double loanPay = Double.parseDouble(request.getParameter("amount"));
		if (loanPay <= 0) {
			model.addAttribute("error", "It cannot be Zero or less than 0");
			return mv;
		}
		if (OP_PAYLOAN.equalsIgnoreCase(form.getOperation())) {

			// if (!request.getParameter("amount").isEmpty()) {

			// }
			Double loanPayment = Double.parseDouble(request.getParameter("amount"));
			Long accountId = (Long) session.getAttribute("accountId");

			UserDTO user = (UserDTO) session.getAttribute("user");
			System.out.println("LoanCtl.submitPayment(): " + loanPayment);
			// Get Account Balance
			Long currentAmount = accountServiceInt.getAmountByAccount(user.getId(), accountId);
			System.out.println("Current amount is " + currentAmount);

			if (currentAmount < loanPayment) {
				model.addAttribute("error", "Insufficient Balance in this account");
				return mv;
			}
			LoanDTO bean = (LoanDTO) populateDTO(form.getDTO(), request);
			LoanDTO loanDTO = service.findBypk(bean.getId());
			Double remainingAmount = loanDTO.getPayableAmount();
			// loan payment can never exceed the total amount remaining on the loan
			if (remainingAmount < loanPayment) {
				model.addAttribute("error", "Loan payment can never exceed the total amount remaining on the loan");
				return mv;
			}
			// subtract amount from entered amount

			Long newAmount = (long) (currentAmount - loanPayment);
			Double payableAmount = remainingAmount - loanPayment;
			if (payableAmount == 0) {
				model.addAttribute("error", "Loan Payment Successfully Done.");
			}
			bean.setPayableAmount(payableAmount);
			System.out.println("bean.getId(): " + bean.getId());
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setId(accountId);
			accountDTO.setAmount(newAmount);
			if (bean.getId() > 0) {
				service.update(bean);
				accountServiceInt.update(accountDTO);
				model.addAttribute("success", "Loan Paid Successfully!!!!");
			}

			return mv;
		}

		return mv;
	}
}
