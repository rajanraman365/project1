package com.banking.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banking.dtos.AccountTypesDTO;
import com.banking.dtos.UserDTO;



@Repository
public class AccountTypeDAOImpl implements AccountTypeDAOInt {

	private static Logger log = Logger.getLogger(AccountTypeDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(AccountTypesDTO dto) {
		// TODO Auto-generated method stub
		log.info("AccountTypeDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("AccountTypeDAOImpl Add method ends");
		return pk;
	}




	@Override
	public List<AccountTypesDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<AccountTypesDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AccountTypeDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<AccountTypesDTO> query = session.createQuery("from AccountTypesDTO", AccountTypesDTO.class);
		List<AccountTypesDTO> list = query.getResultList();
		log.info("AccountTypeDAOImpl List method End");
		return list;
	}

	@Override
	public AccountTypesDTO findByAccountType(String aType) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AccountTypesDTO.class);
		criteria.add(Restrictions.eq("typeName", aType));
		AccountTypesDTO dto = (AccountTypesDTO) criteria.uniqueResult();
		return dto;
	}
	@Override
	public List<AccountTypesDTO> search(AccountTypesDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<AccountTypesDTO> search(AccountTypesDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AccountTypeDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AccountTypesDTO.class);
		
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
		log.info("AccountTypeDAOImpl Search method End");
		return criteria.list();
	}




	@Override
	@Transactional
	public void delete(AccountTypesDTO dto) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Query<AccountTypesDTO> query = session.createSQLQuery("delete from account_types where id = "+dto.getId()+"");
		query.executeUpdate();
		//entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}




	@Override
	public AccountTypesDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("AccountTypeDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		AccountTypesDTO dto = (AccountTypesDTO) session.get(AccountTypesDTO.class, pk);
		System.out.println("dto "+dto.getId());
		log.info("AccountTypeDAOImpl FindByPk method End");
		return dto;
	}




	@Override
	public void update(AccountTypesDTO dto) {
		// TODO Auto-generated method stub
		log.info("AccountTypeDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("AccountTypeDAOImpl update method End");
	}




	@Override
	public List<AccountTypesDTO> getAllAccountsTypeName(AccountTypesDTO dto) {
		log.info("AccountTypeDAOImpl getAllAccountsTypeName method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AccountTypesDTO.class);
		Criterion c1 = Restrictions.eq("typeName", "Savings");
	    Criterion c2 = Restrictions.eq("typeName", "Checking");
	    Criterion c3 = Restrictions.or(c1, c2);
	    criteria.add(c3);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			
			
		}
		log.info("AccountTypeDAOImpl Search method End");
		return criteria.list();
	}
}
