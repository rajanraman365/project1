package com.banking.dao;

import java.util.List;


import com.banking.dtos.CreditCardDTO;

public interface CreditCardDAOInt {

	public long add(CreditCardDTO dto);

	public void delete(CreditCardDTO dto);
	
	public List<CreditCardDTO> getUserCreditcardByUserId(long userId);
	
	public void update(CreditCardDTO dto);

	public CreditCardDTO findByAccount(long aType);

	public CreditCardDTO findBypk(long pk);

	public List<CreditCardDTO> list();

	public List<CreditCardDTO> list(int pageNo, int pageSize);

	public List<CreditCardDTO> search(CreditCardDTO dto);

	public List<CreditCardDTO> search(CreditCardDTO dto, int pageNo, int pageSize);

	CreditCardDTO findByCreditAccountNumber(String aType);
}
