package com.banking.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banking.dtos.AccountDTO;
import com.banking.dtos.AccountTypesDTO;
import com.banking.dtos.CreditCardDTO;

@Repository
public class CreditCardDAOImpl implements CreditCardDAOInt {

	private static Logger log = Logger.getLogger(CreditCardDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(CreditCardDTO dto) {
		// TODO Auto-generated method stub
		log.info("CreditCardDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("CreditCardDAOImpl Add method ends");
		return pk;
	}

	@Override
	public List<CreditCardDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<CreditCardDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("CreditCardDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<CreditCardDTO> query = session.createQuery("from CreditCardDTO", CreditCardDTO.class);
		List<CreditCardDTO> list = query.getResultList();
		log.info("CreditCardDAOImpl List method End");
		return list;
	}

	@Override
	public CreditCardDTO findByAccount(long aType) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CreditCardDTO.class);
		criteria.add(Restrictions.eq("accountId", aType));
		CreditCardDTO dto = (CreditCardDTO) criteria.uniqueResult();
		return dto;
	}
	
	@Override
	public CreditCardDTO findByCreditAccountNumber(String aType) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CreditCardDTO.class);
		criteria.add(Restrictions.eq("creditCardNumber", aType));
		CreditCardDTO dto = (CreditCardDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(CreditCardDTO dto) {
		// TODO Auto-generated method stub
		log.info("CreditCardDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		org.hibernate.query.Query<CreditCardDTO> query = session.createSQLQuery("Update creditcard set credit_limit="+dto.getCreditLimit()+" where id="+dto.getId()+"");
		query.executeUpdate();
		log.info("CreditCardDAOImpl update method End");
	}
	@Override
	public List<CreditCardDTO> search(CreditCardDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<CreditCardDTO> search(CreditCardDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("CreditCardDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CreditCardDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("CreditCardDAOImpl Search method End");
		return criteria.list();
	}

	@Override
	@Transactional
	public void delete(CreditCardDTO dto) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Query<CreditCardDTO> query = session.createSQLQuery("delete from account_types where id = " + dto.getId() + "");
		query.executeUpdate();
		// entityManager.remove(entityManager.contains(dto) ? dto :
		// entityManager.merge(dto));
	}

	@Override
	public CreditCardDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("CreditCardDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		CreditCardDTO dto = (CreditCardDTO) session.get(CreditCardDTO.class, pk);
		System.out.println("dto " + dto.getId());
		log.info("CreditCardDAOImpl FindByPk method End");
		return dto;
	}
	
	@Override
	public List<CreditCardDTO> getUserCreditcardByUserId(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CreditCardDTO.class);
		criteria.add(Restrictions.eq("userId", userId));
		//Criterion c1 = Restrictions.eq("accountId", userId);
	    //Criterion c2 = Restrictions.eq("status", "OPEN");
	    //Criterion c3 = Restrictions.and(c1, c2);
	    //criteria.add(c3);
		log.info("CreditCardDAOImpl getUserCreditcardByAccount() method End");
		return criteria.list();
	}

	

}
