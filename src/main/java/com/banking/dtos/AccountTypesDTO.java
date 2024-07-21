package com.banking.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account_types")
@Setter
@Getter
public class AccountTypesDTO extends BaseDTO {

	@Column(name = "type_name",length = 255)
	private String typeName;
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
