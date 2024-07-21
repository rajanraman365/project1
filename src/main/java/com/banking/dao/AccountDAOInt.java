package com.banking.dao;

import java.math.BigDecimal;
import java.util.List;

import com.banking.dtos.AccountDTO;

public interface AccountDAOInt {

	public long add(AccountDTO dto);

	public void delete(AccountDTO dto);
	
	public void changeStatus(AccountDTO dto);
	
	public Long getTotalAmount(long userId);
	
	public Long getAmountByAccount(long userId, long accountId);

	public AccountDTO findByAccountType(String aType);

	public AccountDTO findBypk(long pk);
	
	public AccountDTO findByUserId(long pk);
	
	public List<AccountDTO> getUserAccountsById(long userId);

	public void update(AccountDTO dto);

	public List<AccountDTO> list();

	public List<AccountDTO> list(int pageNo, int pageSize);

	public List<AccountDTO> search(AccountDTO dto);

	public List<AccountDTO> search(AccountDTO dto, int pageNo, int pageSize);

	List<AccountDTO> getUserAccounts(AccountDTO dto);
}
