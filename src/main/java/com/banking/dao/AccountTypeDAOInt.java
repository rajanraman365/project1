package com.banking.dao;

import java.util.List;

import com.banking.dtos.AccountTypesDTO;



public interface AccountTypeDAOInt {

	public long add(AccountTypesDTO dto);
	
	public void delete(AccountTypesDTO dto);
	
	public AccountTypesDTO findByAccountType(String aType);
	
	public AccountTypesDTO findBypk(long pk);
	
	public void update(AccountTypesDTO dto);
	
	public List<AccountTypesDTO> list();
	
	public List<AccountTypesDTO>list(int pageNo,int pageSize);
	
	public List<AccountTypesDTO> search(AccountTypesDTO dto);
	
	public List<AccountTypesDTO> getAllAccountsTypeName(AccountTypesDTO dto);
	
	public List<AccountTypesDTO> search(AccountTypesDTO dto,int pageNo,int pageSize);
}
