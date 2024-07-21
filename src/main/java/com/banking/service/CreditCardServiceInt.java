package com.banking.service;

import java.math.BigDecimal;
import java.util.List;

import com.banking.dtos.AccountTypesDTO;
import com.banking.dtos.CreditCardDTO;
import com.banking.exception.DuplicateRecordException;

public interface CreditCardServiceInt {

	public long add(CreditCardDTO dto) throws DuplicateRecordException;

	public void delete(CreditCardDTO dto);
	
	CreditCardDTO findByCreditAccountNumber(String aType);

	public List<CreditCardDTO> getUserCreditcardByUserId(long userId);

	public CreditCardDTO findBypk(long pk);

	public List<CreditCardDTO> list();
	
	public void update(CreditCardDTO dto);
	
	

	public List<CreditCardDTO> list(int pageNo, int pageSize);

	public List<CreditCardDTO> search(CreditCardDTO dto);

	public List<CreditCardDTO> search(CreditCardDTO dto, int pageNo, int pageSize);

	CreditCardDTO findByAccountType(long aType);

}
