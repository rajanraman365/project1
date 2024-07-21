package com.banking.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.banking.dtos.AccountDTO;
import com.banking.dtos.BaseDTO;
import com.banking.dtos.CreditCardDTO;
import com.banking.dtos.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreditForm extends BaseForm {

	private AccountDTO accounts;
	@NotNull(message = "Please select is required")
	private Long accountId;
	@NotNull(message = "Credit Limit is required")
	private Long creditLimit;
	@NotEmpty(message = "Issue Date is required")
	private String date;
	@NotEmpty(message = "Credit Card Number is required")
	@Pattern(regexp="(^[0-9]{12})*$",message = "Number is Invalid. It should be Valid 12 Digit")
	private String creditCardNumber;
	@NotEmpty(message = "Month Expiry is required")
	private String monthExpiry;
	@NotEmpty(message = "Year is required")
	private String year;
	@NotEmpty(message = "CVV is required")
	@Pattern(regexp="(^[0-9]{3})*$",message = "CVV is Invalid. It should be a 3 digit valid number")
	private String cvv;

	private UserDTO user;
	private Long userId;
	@Override
	public BaseDTO getDTO() {
		CreditCardDTO bean = new CreditCardDTO();
		bean.setId(id);
		bean.setAccountId(accountId);
		bean.setAccounts(accounts);
		bean.setCreditCardNumber(creditCardNumber);
		bean.setCreditLimit(creditLimit);
		bean.setYear(year);
		bean.setCvv(cvv);
		bean.setMonthExpiry(monthExpiry);
		bean.setDate(date);
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

		CreditCardDTO bean = (CreditCardDTO) bDto;
		id = bean.getId();
		creditCardNumber = bean.getCreditCardNumber();
		accountId = bean.getAccountId();
		accounts = bean.getAccounts();
		creditLimit = bean.getCreditLimit();
		cvv = bean.getCvv();
		year = bean.getYear();
		monthExpiry = bean.getMonthExpiry();
		user = bean.getUser();
		userId = bean.getUserId();
		date = bean.getDate();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
	}
}
