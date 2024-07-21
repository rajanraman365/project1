package com.banking.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.AccountDAOInt;
import com.banking.dao.CreditCardDAOInt;
import com.banking.dtos.CreditCardDTO;
import com.banking.exception.DuplicateRecordException;



@Service
public class CreditCardServiceImpl implements CreditCardServiceInt{

	private static Logger log = Logger.getLogger(CreditCardServiceImpl.class.getName());

	@Autowired
	private CreditCardDAOInt dao;

	@Override
	@Transactional
	public long add(CreditCardDTO dto) throws DuplicateRecordException {
		log.info("CreditCardServiceImpl Add method start");
		
		  CreditCardDTO existDto = findByAccountType(dto.getAccountId());
		  if(existDto!=null) throw new
		  DuplicateRecordException("Credit card for this Account already exist");
		 
		
		long pk = dao.add(dto);
		log.info("CreditCardServiceImpl Add method end");
		return pk;
	}

	

	@Override
	public List<CreditCardDTO> list() {
		// TODO Auto-generated method stub
		log.info("CreditCardServiceImpl list method start");
		List<CreditCardDTO> list = dao.list();
		log.info("CreditCardServiceImpl list method end");
		return list;
	}

	@Override
	public List<CreditCardDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("CreditCardServiceImpl list method start");
		List<CreditCardDTO> list = dao.list(pageNo, pageSize);
		log.info("CreditCardServiceImpl list method end");
		return list;
	}

	@Override
	public List<CreditCardDTO> search(CreditCardDTO dto) {
		// TODO Auto-generated method stub
		log.info("CreditCardServiceImpl search method start");
		List<CreditCardDTO> list = dao.search(dto);
		log.info("CreditCardServiceImpl search method end");
		return list;
	}

	@Override
	public List<CreditCardDTO> search(CreditCardDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("CreditCardServiceImpl search method start");
		List<CreditCardDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("CreditCardServiceImpl search method end");
		return list;
	}



	@Override
	@Transactional
	public void delete(CreditCardDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}



	@Override
	public CreditCardDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("CreditCardServiceImpl findBypk method start");
		CreditCardDTO dto = dao.findBypk(pk);
		log.info("TaxiBrandImpl findBypk method end");
		return dto;
	}



	@Override
	public CreditCardDTO findByAccountType(long aType) {
		log.info("CreditCardServiceImpl findByAccountType method start");
		CreditCardDTO dto = dao.findByAccount(aType);
		log.info("CreditCardServiceImpl findByAccountType method end");
		return dto;
	}



	@Override
	@Transactional
	public void update(CreditCardDTO dto) {
		// TODO Auto-generated method stub
		dao.update(dto);
	}



	@Override
	public List<CreditCardDTO> getUserCreditcardByUserId(long userId) {
		log.info("CreditCardServiceImpl getUserCreditcardByAccount() method start");
		List<CreditCardDTO> list = dao.getUserCreditcardByUserId(userId);
		log.info("CreditCardServiceImpl getUserCreditcardByAccount() method end");
		return list;
	}



	@Override
	public CreditCardDTO findByCreditAccountNumber(String aType) {
		log.info("CreditCardServiceImpl findByCreditAccountNumber method start");
		CreditCardDTO dto = dao.findByCreditAccountNumber(aType);
		log.info("CreditCardServiceImpl findByCreditAccountNumber method end");
		return dto;
	}



	






}
