package com.banking.controller;

import java.util.HashMap;
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
import com.banking.dtos.AccountTypesDTO;
import com.banking.dtos.CreditCardDTO;
import com.banking.dtos.UserDTO;
import com.banking.exception.DuplicateRecordException;
import com.banking.form.AccountForm;
import com.banking.form.AccountTypesForm;
import com.banking.form.CreditForm;
import com.banking.service.AccountServiceInt;
import com.banking.service.AccountTypeServiceInt;
import com.banking.service.CreditCardServiceInt;
import com.banking.service.UserServiceInt;
import com.banking.util.DataUtility;

@RestController
public class CreditCardCtl extends BaseCtl {

	private Logger log = Logger.getLogger(CreditCardCtl.class.getName());

	protected static final String OP_SEND = "Send";

	@Autowired
	private CreditCardServiceInt service;
	@Autowired
	private AccountServiceInt accountServiceInt;
	@Autowired
	private AccountTypeServiceInt accountTypeServiceInt;
	@Autowired
	private UserServiceInt userServiceInt;

	@ModelAttribute
	public void preload(Model model) {

		model.addAttribute("accountTypeList", accountServiceInt.search(null));

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("January", "January");
		map2.put("February", "February");
		map2.put("March", "March");
		map2.put("April", "April");
		map2.put("May", "May");
		map2.put("June", "June");
		map2.put("July", "July");
		map2.put("August", "August");
		map2.put("September", "September");
		map2.put("October", "October");
		map2.put("November", "November");
		map2.put("December", "December");
		model.addAttribute("months", map2);

		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("2023", "2023");
		map3.put("2024", "2024");
		map3.put("2025", "2025");
		map3.put("2026", "2026");
		map3.put("2027", "2027");
		map3.put("2028", "2028");
		map3.put("2029", "2029");
		map3.put("2030", "2030");
		map3.put("2031", "2031");
		model.addAttribute("years", map3);
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setStatus("OPEN");
		model.addAttribute("accountTypeList", accountServiceInt.getUserAccounts(accountDTO));
	}

	@GetMapping("/home/admin/credit-card")
	public ModelAndView display(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") CreditForm form, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("creditcard");
		UserDTO user = (UserDTO) session.getAttribute("user");

		// Get Accounts
		model.addAttribute("userAccounts", accountServiceInt.list());
		if (form.getId() > 0) {
			CreditCardDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return mv;
	}

	@PostMapping("/home/admin/credit-card")
	public ModelAndView submit(@Valid @ModelAttribute("form") CreditForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("creditcard");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/admin/credit-card");
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return mv;
				}

				CreditCardDTO bean = (CreditCardDTO) populateDTO(form.getDTO(), request);
				AccountDTO account = accountServiceInt.findBypk(form.getAccountId());
				bean.setAccounts(account);
				bean.setAccountId(form.getAccountId());
				bean.setUser(account.getUser());
				bean.setUserId(account.getUserId());

				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Credit Card Detail update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Credit Card Detail Created Successfully!!!!");
				}
				return mv;
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return mv;
		}

		return mv;
	}

	@RequestMapping(value = { "/home/admin/credit-cards", "/home/user/credit-cards" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") CreditForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("creditCardList");
		if (OP_RESET.equalsIgnoreCase(operation)) {
			return new ModelAndView("redirect:/home/admin/credit-cards");
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		CreditCardDTO dto = (CreditCardDTO) form.getDTO();

		List<CreditCardDTO> list = service.search(dto, pageNo, pageSize);
		List<CreditCardDTO> totallist = service.search(dto);
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

	@GetMapping("/home/user/credit-card/payment")
	public ModelAndView displayCreditCardPayment(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") CreditForm form, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("creditCardPayment");
		UserDTO user = (UserDTO) session.getAttribute("user");
		System.out.println("User Id: " + user.getId());

		// Get Accounts
		model.addAttribute("userAccounts", service.getUserCreditcardByUserId(user.getId()));
		if (form.getId() > 0) {
			CreditCardDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return mv;
	}

	@PostMapping("/home/user/credit-card/payment")
	public ModelAndView submitPayment(@Valid @ModelAttribute("form") CreditForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("creditCardPayment");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/user/credit-card/payment");
		}

		if (OP_SEND.equalsIgnoreCase(form.getOperation())) {

			Double amount = Double.parseDouble(request.getParameter("amount"));
			System.out.println("Amount is >> " + amount + " Credit Card No. " + form.getCreditCardNumber());
			UserDTO user = (UserDTO) session.getAttribute("user");
			CreditCardDTO creditCardDTO = service.findByCreditAccountNumber(form.getCreditCardNumber());

			// get card limit
			Long cardLimit = creditCardDTO.getCreditLimit();

			Long newCardLimit = (long) (cardLimit - amount);

			CreditCardDTO bean = (CreditCardDTO) populateDTO(form.getDTO(), request);
			bean.setCreditLimit(newCardLimit);
			bean.setId(creditCardDTO.getId());

			service.update(bean);
			model.addAttribute("success", "Credit Card Payment Done Successfully!!!!");

			return mv;
		}

		return mv;
	}

}
