package com.banking.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.LoanDAOInt;
import com.banking.dtos.LoanDTO;
import com.banking.exception.DuplicateRecordException;



@Service
public class LoanServiceImpl implements LoanServiceInt{

	private static Logger log = Logger.getLogger(LoanServiceImpl.class.getName());

	private static final DecimalFormat df = new DecimalFormat("0.00");
	@Autowired
	private LoanDAOInt dao;

	@Override
	@Transactional
	public long add(LoanDTO dto) throws DuplicateRecordException {
		log.info("LoanServiceImpl Add method start");
		/*
		 * LoanDTO existDto = findByAccountType(dto.getAccountType());
		 * if(existDto!=null) throw new
		 * DuplicateRecordException("Account Type already exist");
		 */
		
		long pk = dao.add(dto);
		log.info("LoanServiceImpl Add method end");
		return pk;
	}

	

	@Override
	public List<LoanDTO> list() {
		// TODO Auto-generated method stub
		log.info("LoanServiceImpl list method start");
		List<LoanDTO> list = dao.list();
		log.info("LoanServiceImpl list method end");
		return list;
	}

	@Override
	public List<LoanDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("LoanServiceImpl list method start");
		List<LoanDTO> list = dao.list(pageNo, pageSize);
		log.info("LoanServiceImpl list method end");
		return list;
	}

	@Override
	public List<LoanDTO> search(LoanDTO dto) {
		// TODO Auto-generated method stub
		log.info("LoanServiceImpl search method start");
		List<LoanDTO> list = dao.search(dto);
		log.info("LoanServiceImpl search method end");
		return list;
	}

	@Override
	public List<LoanDTO> search(LoanDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("LoanServiceImpl search method start");
		List<LoanDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("LoanServiceImpl search method end");
		return list;
	}



	@Override
	@Transactional
	public void delete(LoanDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}



	@Override
	public LoanDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("LoanServiceImpl findBypk method start");
		LoanDTO dto = dao.findBypk(pk);
		log.info("TaxiBrandImpl findBypk method end");
		return dto;
	}



	@Override
	@Transactional
	public void update(LoanDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		log.info("LoanServiceImpl update method start");
		dao.update(dto);
		log.info("LoanServiceImpl update method start");
	}



	@Override
	public LoanDTO findByAccountType(String aType) {
		// TODO Auto-generated method stub
		LoanDTO findByAccountType = dao.findByAccountType(aType);
		return findByAccountType;
	}



	@Override
	public List<LoanDTO> getUserAccountsById(long userId) {
		log.info("LoanServiceImpl search method start");
		List<LoanDTO> list = dao.getUserAccountsById(userId);
		log.info("LoanServiceImpl search method end");
		return list;
	}



	@Override
	public Long getTotalAmount(long userId) {
		// TODO Auto-generated method stub
		return dao.getTotalAmount(userId);
	}



	@Override
	public Long getAmountByAccount(long userId, long accountId) {
		// TODO Auto-generated method stub
		return dao.getAmountByAccount(userId, accountId);
	}



	@Override
	@Transactional
	public void changeStatus(LoanDTO dto) {
		// TODO Auto-generated method stub
		dao.changeStatus(dto);
	}
	
	@Override
	public Double calculateSimpleInterest(Double pricipal, Integer rate, Integer year) {
		Double simpleInterest;
		simpleInterest = ((pricipal * rate * year)/100);
		return simpleInterest;
	}



	@Override
	public Double calculateMonthlyPayment(Double pricipal, Double si, Integer year) {
		Double montlyPayment;
		montlyPayment = ((si + pricipal)/(year*12));
		String afterFormating = df.format(montlyPayment);
		Double.parseDouble(afterFormating);
		return Double.parseDouble(afterFormating);
	}
}
