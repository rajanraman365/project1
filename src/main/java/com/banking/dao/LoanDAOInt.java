package com.banking.dao;

import java.math.BigDecimal;
import java.util.List;

import com.banking.dtos.LoanDTO;
import com.banking.dtos.LoanDTO;

public interface LoanDAOInt {

	public long add(LoanDTO dto);

	public void delete(LoanDTO dto);
	
	public void changeStatus(LoanDTO dto);
	
	public Long getTotalAmount(long userId);
	
	public Long getAmountByAccount(long userId, long accountId);

	public LoanDTO findByAccountType(String aType);

	public LoanDTO findBypk(long pk);
	
	public List<LoanDTO> getUserAccountsById(long userId);

	public void update(LoanDTO dto);

	public List<LoanDTO> list();

	public List<LoanDTO> list(int pageNo, int pageSize);

	public List<LoanDTO> search(LoanDTO dto);

	public List<LoanDTO> search(LoanDTO dto, int pageNo, int pageSize);
	
	
}
