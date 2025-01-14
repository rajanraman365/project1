package com.banking.form;

import java.sql.Timestamp;

import com.banking.dtos.BaseDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class BaseForm {

	protected long id;

	protected String createdBy;

	protected String modifiedBy;

	protected Timestamp createdDateTime;

	protected Timestamp modifiedDateTime;

	protected int pageNo = 1;

	protected int pageSize = 10;

	protected long[] ids;

	protected int listsize;

	protected int total;

	private String operation;
	private int pagenosize;

	

	public abstract BaseDTO getDTO();

	public abstract void populate(BaseDTO bDto);

}
