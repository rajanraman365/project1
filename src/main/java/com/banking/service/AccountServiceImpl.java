package com.banking.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.AccountDAOInt;
import com.banking.dtos.AccountDTO;
import com.banking.exception.DuplicateRecordException;



@Service
public class AccountServiceImpl implements AccountServiceInt{

	private static Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

	@Autowired
	private AccountDAOInt dao;

	@Override
	@Transactional
	public long add(AccountDTO dto) throws DuplicateRecordException {
		log.info("AccountServiceImpl Add method start");
		/*
		 * AccountDTO existDto = findByAccountType(dto.getAccountType());
		 * if(existDto!=null) throw new
		 * DuplicateRecordException("Account Type already exist");
		 */
		
		long pk = dao.add(dto);
		log.info("AccountServiceImpl Add method end");
		return pk;
	}

	

	@Override
	public List<AccountDTO> list() {
		// TODO Auto-generated method stub
		log.info("AccountServiceImpl list method start");
		List<AccountDTO> list = dao.list();
		log.info("AccountServiceImpl list method end");
		return list;
	}

	@Override
	public List<AccountDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AccountServiceImpl list method start");
		List<AccountDTO> list = dao.list(pageNo, pageSize);
		log.info("AccountServiceImpl list method end");
		return list;
	}

	@Override
	public List<AccountDTO> search(AccountDTO dto) {
		// TODO Auto-generated method stub
		log.info("AccountServiceImpl search method start");
		List<AccountDTO> list = dao.search(dto);
		log.info("AccountServiceImpl search method end");
		return list;
	}

	@Override
	public List<AccountDTO> search(AccountDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AccountServiceImpl search method start");
		List<AccountDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("AccountServiceImpl search method end");
		return list;
	}



	@Override
	@Transactional
	public void delete(AccountDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}



	@Override
	public AccountDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("AccountServiceImpl findBypk method start");
		AccountDTO dto = dao.findBypk(pk);
		log.info("AccountServiceImpl findBypk method end");
		return dto;
	}

	@Override
	public AccountDTO findByUserId(long pk) {
		// TODO Auto-generated method stub
		log.info("AccountServiceImpl findBypk method start");
		AccountDTO dto = dao.findByUserId(pk);
		log.info("AccountServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(AccountDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		log.info("AccountServiceImpl update method start");
		dao.update(dto);
		log.info("AccountServiceImpl update method start");
	}



	@Override
	public AccountDTO findByAccountType(String aType) {
		// TODO Auto-generated method stub
		AccountDTO findByAccountType = dao.findByAccountType(aType);
		return findByAccountType;
	}



	@Override
	public List<AccountDTO> getUserAccountsById(long userId) {
		log.info("AccountServiceImpl search method start");
		List<AccountDTO> list = dao.getUserAccountsById(userId);
		log.info("AccountServiceImpl search method end");
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
	public void changeStatus(AccountDTO dto) {
		// TODO Auto-generated method stub
		dao.changeStatus(dto);
	}
	
	@Override
	public List<AccountDTO> getUserAccounts(AccountDTO dto) {
		log.info("AccountServiceImpl getUserAccounts method start");
		List<AccountDTO> list = dao.getUserAccounts(dto);
		log.info("AccountServiceImpl getUserAccounts method end");
		return list;
	}
}
