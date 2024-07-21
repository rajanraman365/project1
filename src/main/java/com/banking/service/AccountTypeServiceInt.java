package com.banking.service;

import java.util.List;

import com.banking.dtos.AccountTypesDTO;
import com.banking.exception.DuplicateRecordException;


public interface AccountTypeServiceInt {

	public long add(AccountTypesDTO dto) throws DuplicateRecordException;
	
	public void delete(AccountTypesDTO dto);
	
	public List<AccountTypesDTO> getAllAccountsTypeName(AccountTypesDTO dto);
	
	public AccountTypesDTO findBypk(long pk);
	
	public AccountTypesDTO findByAccountType(String aType);

	public void update(AccountTypesDTO dto) throws DuplicateRecordException;

	public List<AccountTypesDTO> list();

	public List<AccountTypesDTO> list(int pageNo, int pageSize);

	public List<AccountTypesDTO> search(AccountTypesDTO dto);

	public List<AccountTypesDTO> search(AccountTypesDTO dto, int pageNo, int pageSize);
}
