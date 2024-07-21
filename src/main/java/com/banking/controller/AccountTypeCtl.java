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

import com.banking.dtos.AccountTypesDTO;
import com.banking.exception.DuplicateRecordException;
import com.banking.form.AccountTypesForm;
import com.banking.service.AccountTypeServiceInt;

@RestController
public class AccountTypeCtl extends BaseCtl {

	private Logger log = Logger.getLogger(AccountTypeCtl.class.getName());

	@Autowired
	private AccountTypeServiceInt service;

	@GetMapping("/home/admin/account-type")
	public ModelAndView display(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") AccountTypesForm form, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("accountType");
		if (form.getId() > 0) {
			AccountTypesDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return mv;
	}

	@PostMapping("/home/admin/account-type")
	public ModelAndView submit(@Valid @ModelAttribute("form") AccountTypesForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("accountType");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/admin/account-type");
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return mv;
				}
				AccountTypesDTO bean = (AccountTypesDTO) populateDTO(form.getDTO(), request);
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Account Type update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Account Type Added Successfully!!!!");
				}
				return mv;
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "/home/admin/account-type/search", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView searchList(@ModelAttribute("form") AccountTypesForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("accountTypeList");
		if (OP_RESET.equalsIgnoreCase(operation)) {
			return new ModelAndView("redirect:/home/admin/account-type/search");
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

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					AccountTypesDTO dto = new AccountTypesDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AccountTypesDTO dto = (AccountTypesDTO) form.getDTO();

		List<AccountTypesDTO> list = service.search(dto, pageNo, pageSize);
		List<AccountTypesDTO> totallist = service.search(dto);
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
}
