package com.banking.form;

import javax.validation.constraints.NotEmpty;

import com.banking.dtos.AccountTypesDTO;
import com.banking.dtos.BaseDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AccountTypesForm extends BaseForm {

	@NotEmpty(message = "Account Type is required")
	private String accountType;
	@Override
	public BaseDTO getDTO() {
		AccountTypesDTO bean = new AccountTypesDTO();
		bean.setId(id);
		bean.setTypeName(accountType);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		AccountTypesDTO bean = (AccountTypesDTO)bDto;
		id = bean.getId();
		accountType = bean.getTypeName();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		
	}

}
