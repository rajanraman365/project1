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
import com.banking.dtos.AccountTypesDTO;
import com.banking.dtos.UserDTO;
import com.banking.exception.DuplicateRecordException;
import com.banking.form.AccountForm;
import com.banking.form.AccountTypesForm;
import com.banking.service.AccountServiceInt;
import com.banking.service.AccountTypeServiceInt;
import com.banking.util.DataUtility;

@RestController
public class AccountCtl extends BaseCtl {

	private Logger log = Logger.getLogger(AccountCtl.class.getName());

	protected static final String OP_CLOSE = "Close";
	@Autowired
	private AccountServiceInt service;
	@Autowired
	private AccountTypeServiceInt accountTypeServiceInt;

	@ModelAttribute
	public void preload(Model model) {

		model.addAttribute("accountTypeList", accountTypeServiceInt.getAllAccountsTypeName(null));

	}

	@GetMapping("/home/user/account")
	public ModelAndView display(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") AccountForm form, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("account");

		if (form.getId() > 0) {
			AccountDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return mv;
	}

	@PostMapping("/home/user/account")
	public ModelAndView submit(@Valid @ModelAttribute("form") AccountForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("account");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/user/account");
		}
		System.out.println("accoutn " + form.getAccountType());
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return mv;
				}

				AccountDTO bean = (AccountDTO) populateDTO(form.getDTO(), request);
				int accountNo = DataUtility.generateAccountNumber();
				UserDTO user = (UserDTO) session.getAttribute("user");
				bean.setUser(user);
				bean.setUserId(user.getId());
				bean.setAccountNumber(accountNo);
				bean.setAmount(0l);
				bean.setStatus("OPEN");
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Account update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Account Created Successfully!!!!");
				}
				return mv;
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return mv;
		}

		return mv;
	}

	@RequestMapping(value = "/home/accounts", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") AccountForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("accountList");
		if (OP_RESET.equalsIgnoreCase(operation)) {
			return new ModelAndView("redirect:/home/accounts");
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

		if (OP_CLOSE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					AccountDTO dto = new AccountDTO();
					dto.setId(id);
					service.changeStatus(dto);
				}
				model.addAttribute("success", "Account Closed Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AccountDTO dto = (AccountDTO) form.getDTO();

		List<AccountDTO> list = service.search(dto, pageNo, pageSize);
		List<AccountDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		UserDTO user = (UserDTO) session.getAttribute("user");
		model.addAttribute("totalAmount", service.getTotalAmount(user.getId()));
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
}
