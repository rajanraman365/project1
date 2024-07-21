package com.banking.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dao.AccountTypeDAOInt;
import com.banking.dtos.AccountTypesDTO;
import com.banking.exception.DuplicateRecordException;



@Service
public class AccountTypeServiceImpl implements AccountTypeServiceInt{

	private static Logger log = Logger.getLogger(AccountTypeServiceImpl.class.getName());

	@Autowired
	private AccountTypeDAOInt dao;

	@Override
	@Transactional
	public long add(AccountTypesDTO dto) throws DuplicateRecordException {
		log.info("AccountTypeServiceImpl Add method start");
		AccountTypesDTO existDto = findByAccountType(dto.getTypeName());
		if(existDto!=null) 
			throw new DuplicateRecordException("Account Type already exist");
		
		long pk = dao.add(dto);
		log.info("AccountTypeServiceImpl Add method end");
		return pk;
	}

	

	@Override
	public List<AccountTypesDTO> list() {
		// TODO Auto-generated method stub
		log.info("AccountTypeServiceImpl list method start");
		List<AccountTypesDTO> list = dao.list();
		log.info("AccountTypeServiceImpl list method end");
		return list;
	}

	@Override
	public List<AccountTypesDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AccountTypeServiceImpl list method start");
		List<AccountTypesDTO> list = dao.list(pageNo, pageSize);
		log.info("AccountTypeServiceImpl list method end");
		return list;
	}

	@Override
	public List<AccountTypesDTO> search(AccountTypesDTO dto) {
		// TODO Auto-generated method stub
		log.info("AccountTypeServiceImpl search method start");
		List<AccountTypesDTO> list = dao.search(dto);
		log.info("AccountTypeServiceImpl search method end");
		return list;
	}

	@Override
	public List<AccountTypesDTO> search(AccountTypesDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AccountTypeServiceImpl search method start");
		List<AccountTypesDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("AccountTypeServiceImpl search method end");
		return list;
	}



	@Override
	@Transactional
	public void delete(AccountTypesDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}



	@Override
	public AccountTypesDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("AccountTypeServiceImpl findBypk method start");
		AccountTypesDTO dto = dao.findBypk(pk);
		log.info("TaxiBrandImpl findBypk method end");
		return dto;
	}



	@Override
	@Transactional
	public void update(AccountTypesDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		log.info("AccountTypeServiceImpl update method start");
		dao.update(dto);
		log.info("AccountTypeServiceImpl update method start");
	}



	@Override
	public AccountTypesDTO findByAccountType(String aType) {
		// TODO Auto-generated method stub
		AccountTypesDTO findByAccountType = dao.findByAccountType(aType);
		return findByAccountType;
	}



	@Override
	public List<AccountTypesDTO> getAllAccountsTypeName(AccountTypesDTO dto) {
		log.info("AccountTypeServiceImpl getAllAccountsTypeName method start");
		List<AccountTypesDTO> list = dao.getAllAccountsTypeName(dto);
		log.info("AccountTypeServiceImpl getAllAccountsTypeName method end");
		return list;
	}
}
