package com.banking.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.banking.dtos.AccountDTO;
import com.banking.dtos.BaseDTO;
import com.banking.dtos.LoanDTO;
import com.banking.dtos.UserDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class LoanForm extends BaseForm {

	
	private AccountDTO account;

	@NotNull(message = "Account Id cannot be empty")
	private Long accountId;

	@NotNull(message = "Original Loan Amount cannot be empty")
	private Double originalLoanAmount;
	
	
	private Double monthlyPaymentAmount;
	
	
	private Double simpleInterest;
	
	private Double totalAmount;
	
	@NotNull(message = "Rate % cannot be empty")
	private Integer interestRate;

	@NotNull(message = "Term length cannot be empty")
	private Integer termLength;
	
	@NotEmpty(message = "Loan Name cannot be empty")
	private String loanName;
	
	private Double payableAmount;
	
	private UserDTO user;
	
	
	private Long userId;
	@Override
	public BaseDTO getDTO() {
		LoanDTO bean = new LoanDTO();
		bean.setId(id);
		bean.setLoanName(loanName);
		bean.setAccount(account);
		bean.setAccountId(accountId);
		bean.setInterestRate(interestRate);
		bean.setMonthlyPaymentAmount(monthlyPaymentAmount);
		bean.setOriginalLoanAmount(originalLoanAmount);
		bean.setSimpleInterest(simpleInterest);
		bean.setTermLength(termLength);
		bean.setTotalAmount(totalAmount);
		bean.setPayableAmount(payableAmount);
		bean.setUser(user);
		bean.setUserId(userId);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		LoanDTO bean = (LoanDTO)bDto;
		id = bean.getId();
		account = bean.getAccount();
		loanName = bean.getLoanName();
		totalAmount = bean.getTotalAmount();
		accountId = bean.getAccountId();
		interestRate = bean.getInterestRate();
		monthlyPaymentAmount = bean.getMonthlyPaymentAmount();
		originalLoanAmount = bean.getOriginalLoanAmount();
		simpleInterest = bean.getSimpleInterest();
		termLength = bean.getTermLength();
		user = bean.getUser();
		userId = bean.getUserId();
		payableAmount = bean.getPayableAmount();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();	
		
	}

}
