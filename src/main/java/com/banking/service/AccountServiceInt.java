package com.banking.service;

import java.math.BigDecimal;
import java.util.List;

import com.banking.dtos.AccountDTO;
import com.banking.exception.DuplicateRecordException;

public interface AccountServiceInt {

	public long add(AccountDTO dto) throws DuplicateRecordException;

	public void delete(AccountDTO dto);
	
	List<AccountDTO> getUserAccounts(AccountDTO dto);
	
	public void changeStatus(AccountDTO dto);

	public AccountDTO findByAccountType(String aType);

	public AccountDTO findBypk(long pk);
	
	public AccountDTO findByUserId(long pk);
	
	public List<AccountDTO> getUserAccountsById(long userId);

	public void update(AccountDTO dto) throws DuplicateRecordException;

	public List<AccountDTO> list();

	public List<AccountDTO> list(int pageNo, int pageSize);

	public List<AccountDTO> search(AccountDTO dto);

	public List<AccountDTO> search(AccountDTO dto, int pageNo, int pageSize);
	
public Long getTotalAmount(long userId);
	
	public Long getAmountByAccount(long userId, long accountId);
}
