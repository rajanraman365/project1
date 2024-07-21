package com.banking.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.banking.dtos.AccountDTO;
import com.banking.dtos.UserDTO;
import com.banking.exception.DuplicateRecordException;
import com.banking.form.AccountForm;
import com.banking.service.AccountServiceInt;
import com.banking.service.AccountTypeServiceInt;
import com.banking.service.UserServiceInt;
import com.banking.util.DataUtility;

@RestController
public class TransactionCtl extends BaseCtl {

	private Logger log = Logger.getLogger(TransactionCtl.class.getName());

	protected static final String OP_DEPOSIT = "Deposit";
	protected static final String OP_WITHDRAW = "Withdraw";
	protected static final String OP_SEND = "Send";

	@Autowired
	private AccountServiceInt service;
	@Autowired
	private AccountTypeServiceInt accountTypeServiceInt;
	@Autowired
	private UserServiceInt userServiceInt;

	@ModelAttribute
	public void preload(Model model, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");

		model.addAttribute("accountTypeList", accountTypeServiceInt.search(null));

	}

	@GetMapping("/home/user/account/deposit")
	public ModelAndView display(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") AccountForm form, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deposit");
		UserDTO user = (UserDTO) session.getAttribute("user");

		// Get Accounts
		model.addAttribute("userAccounts", service.getUserAccountsById(user.getId()));
		if (form.getId() > 0) {
			AccountDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return mv;
	}

	@PostMapping("/home/user/account/deposit")
	public ModelAndView submit(@Valid @ModelAttribute("form") AccountForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) throws DuplicateRecordException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("deposit");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/user/account/deposit");
		}
		if (OP_DEPOSIT.equalsIgnoreCase(form.getOperation())) {

			if (form.getAmount() == null) {
				model.addAttribute("error", "Enter Amount");
				return mv;
			}
			if (form.getAmount() <= 0) {
				model.addAttribute("error", "Amount cannot be negative");
				return mv;
			}

			AccountDTO bean = (AccountDTO) populateDTO(form.getDTO(), request);

			UserDTO user = (UserDTO) session.getAttribute("user");
			int accountId = DataUtility.getInt(form.getAccountName());
			// Get Account Balance
			Long currentAmount = service.getAmountByAccount(user.getId(), accountId);
			// Add new amount to old
			Long newAmount = currentAmount + form.getAmount();
			// Set the Value
			bean.setAmount(newAmount);
			bean.setId(accountId);
			model.addAttribute("userAccounts", service.getUserAccountsById(user.getId()));

			if (bean.getId() > 0) {
				service.update(bean);
				model.addAttribute("success", "Amount Deposit Successfully!!!!");
			}
			return mv;
		}

		return mv;
	}

	@GetMapping("/home/user/account/transfer")
	public ModelAndView displayTransferForm(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") AccountForm form, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("transfer");
		UserDTO user = (UserDTO) session.getAttribute("user");

		// Get Accounts
		model.addAttribute("userAccounts", service.getUserAccountsById(user.getId()));
		if (form.getId() > 0) {
			AccountDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return mv;
	}

	@PostMapping("/home/user/account/transfer")
	public ModelAndView submitTransfer(@Valid @ModelAttribute("form") AccountForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) throws DuplicateRecordException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("transfer");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/user/account/transfer");
		}
		if (OP_SEND.equalsIgnoreCase(form.getOperation())) {

			if (form.getAmount() == null) {
				model.addAttribute("error", "Enter Amount");
				return mv;
			}
			if (form.getAmount() <= 0) {
				model.addAttribute("error", "Invalid amount entered");
				return mv;
			}

			AccountDTO bean = (AccountDTO) populateDTO(form.getDTO(), request);

			UserDTO user = (UserDTO) session.getAttribute("user");
			int fromAccountId = DataUtility.getInt(form.getFrom());
			int toAccountId = DataUtility.getInt(form.getTo());
			System.out.println("from: " + fromAccountId + " to: " + toAccountId);
			if (fromAccountId == 0 || toAccountId == 0) {
				model.addAttribute("error", "Please choose To & From account");
				return mv;
			}
			// Check if money transfered into same acc
			if (fromAccountId == toAccountId) {
				model.addAttribute("error", "Incorrect Transaction. Please choose different account");
				return mv;
			}

			// Get Account Balance
			Long currentAmountFromAccount = service.getAmountByAccount(user.getId(), fromAccountId);
			Long currentAmountToAccount = service.getAmountByAccount(user.getId(), toAccountId);

			if (form.getAmount() > currentAmountFromAccount) {
				model.addAttribute("error", "Transfer cannot be initiated. Insufficient Balance in the Account");
				return mv;
			}
			// sub new amount to old
			Long newAmountInFrom = currentAmountFromAccount - form.getAmount();
			// Set the Value
			bean.setAmount(newAmountInFrom);
			bean.setId(fromAccountId);
			model.addAttribute("userAccounts", service.getUserAccountsById(user.getId()));

			// add amount to
			Long newAmountInTo = currentAmountToAccount + form.getAmount();

			AccountDTO bean2 = (AccountDTO) populateDTO(form.getDTO(), request);
			service.update(bean);
			bean2.setAmount(newAmountInTo);
			bean2.setId(toAccountId);
			service.update(bean2);
			model.addAttribute("success", "Amount Transfered Successfully!!!!");

			return mv;
		}

		return mv;
	}

	@GetMapping("/home/user/account/withdraw")
	public ModelAndView displayWithdrawForm(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") AccountForm form, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("withdraw");
		UserDTO user = (UserDTO) session.getAttribute("user");

		// Get Accounts
		model.addAttribute("userAccounts", service.getUserAccountsById(user.getId()));
		if (form.getId() > 0) {
			AccountDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return mv;
	}

	@PostMapping("/home/user/account/withdraw")
	public ModelAndView submitWithdrawForm(@Valid @ModelAttribute("form") AccountForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) throws DuplicateRecordException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("withdraw");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return new ModelAndView("redirect:/home/user/account/withdraw");
		}
		try {
			if (OP_WITHDRAW.equalsIgnoreCase(form.getOperation())) {

				if (form.getAmount() == null) {
					model.addAttribute("error", "Enter Amount");
					return mv;
				}
				if (form.getAmount() <= 0) {
					model.addAttribute("error", "Amount cannot be negative");
					return mv;
				}

				AccountDTO bean = (AccountDTO) populateDTO(form.getDTO(), request);

				UserDTO user = (UserDTO) session.getAttribute("user");
				int accountId = DataUtility.getInt(form.getAccountName());
				// Get Account Balance
				Long currentAmount = service.getAmountByAccount(user.getId(), accountId);

				// Check if withdraw money > balnace
				if (form.getAmount() >= currentAmount) {
					model.addAttribute("error", "Amount cannot be greater than Current Balance");
					return mv;
				}

				// Add new amount to old
				Long newAmount = currentAmount - form.getAmount();
				// Set the Value
				bean.setAmount(newAmount);
				bean.setId(accountId);
				model.addAttribute("userAccounts", service.getUserAccountsById(user.getId()));

				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Amount Withdawn Successfully!!!!");
				}
				return mv;
			}
		} catch (NumberFormatException e) {
			model.addAttribute("error", "Enter Valid data in Amount");
			return mv;
		}
		return mv;
	}
}
