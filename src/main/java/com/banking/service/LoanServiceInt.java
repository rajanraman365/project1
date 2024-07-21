package com.banking.service;

import java.math.BigDecimal;
import java.util.List;

import com.banking.dtos.LoanDTO;
import com.banking.exception.DuplicateRecordException;

public interface LoanServiceInt {

	public long add(LoanDTO dto) throws DuplicateRecordException;

	public void delete(LoanDTO dto);
	
	public void changeStatus(LoanDTO dto);
	
	public Double calculateSimpleInterest(Double pricipal, Integer rate, Integer year);
	
	public Double calculateMonthlyPayment(Double pricipal, Double si, Integer year);

	public LoanDTO findByAccountType(String aType);

	public LoanDTO findBypk(long pk);
	
	public List<LoanDTO> getUserAccountsById(long userId);

	public void update(LoanDTO dto) throws DuplicateRecordException;

	public List<LoanDTO> list();

	public List<LoanDTO> list(int pageNo, int pageSize);

	public List<LoanDTO> search(LoanDTO dto);

	public List<LoanDTO> search(LoanDTO dto, int pageNo, int pageSize);
	
public Long getTotalAmount(long userId);
	
	public Long getAmountByAccount(long userId, long accountId);
}
