package com.banking.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.banking.dtos.AccountDTO;
import com.banking.dtos.BaseDTO;
import com.banking.dtos.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountForm extends BaseForm {

	private Long userId;

	private Integer accountNumber;
	@NotEmpty(message = "Please select account type")
	private String accountType;

	private UserDTO user;
	
	private Long amount;
	@NotEmpty(message = "Account Name is required")
	private String accountName;

	private String from;

	private String to;
	
	private String status;

	@Override
	public BaseDTO getDTO() {
		AccountDTO bean = new AccountDTO();
		bean.setId(id);
		bean.setAccountName(accountName);
		bean.setAccountNumber(accountNumber);
		bean.setAmount(amount);
		bean.setUser(user);
		bean.setUserId(userId);
		bean.setAccountType(accountType);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setFrom(from);
		bean.setTo(to);
		bean.setStatus(status);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		AccountDTO bean = (AccountDTO) bDto;
		id = bean.getId();
		accountName = bean.getAccountName();
		accountNumber = bean.getAccountNumber();
		accountType = bean.getAccountType();
		user = bean.getUser();
		userId = bean.getUserId();
		amount = bean.getAmount();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		from = bean.getFrom();
		to = bean.getTo();
		status = bean.getStatus();
	}

}
