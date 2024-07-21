package com.banking.dao;

import java.math.BigDecimal;
import java.util.Iterator;
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

import com.banking.dtos.AccountDTO;

@Repository
public class AccountDAOImpl implements AccountDAOInt{

	private static Logger log = Logger.getLogger(AccountDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(AccountDTO dto) {
		// TODO Auto-generated method stub
		log.info("AccountDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("AccountDAOImpl Add method ends");
		return pk;
	}




	@Override
	public List<AccountDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<AccountDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AccountDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<AccountDTO> query = session.createQuery("from AccountDTO", AccountDTO.class);
		List<AccountDTO> list = query.getResultList();
		log.info("AccountDAOImpl List method End");
		return list;
	}

	@Override
	public AccountDTO findByAccountType(String aType) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AccountDTO.class);
		criteria.add(Restrictions.eq("accountType", aType));
		AccountDTO dto = (AccountDTO) criteria.uniqueResult();
		return dto;
	}
	@Override
	public List<AccountDTO> search(AccountDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<AccountDTO> search(AccountDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AccountDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AccountDTO.class);
		//criteria.add(Restrictions.eq("status", dto.getStatus()));
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
		log.info("AccountDAOImpl Search method End");
		return criteria.list();
	}




	@Override
	@Transactional
	public void delete(AccountDTO dto) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Query<AccountDTO> query = session.createSQLQuery("delete from account_types where id = "+dto.getId()+"");
		query.executeUpdate();
		//entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}




	@Override
	public AccountDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("AccountDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		AccountDTO dto = (AccountDTO) session.get(AccountDTO.class, pk);
		System.out.println("dto "+dto.getId());
		log.info("AccountDAOImpl FindByPk method End");
		return dto;
	}
	
	@Override
	public AccountDTO findByUserId(long pk) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AccountDTO.class);
		criteria.add(Restrictions.eq("userId", pk));
		AccountDTO dto = (AccountDTO) criteria.uniqueResult();
		return dto;
	}




	@Override
	public void update(AccountDTO dto) {
		// TODO Auto-generated method stub
		log.info("AccountDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		org.hibernate.query.Query<AccountDTO> query = session.createSQLQuery("Update account set amount="+dto.getAmount()+" where id="+dto.getId()+"");
		query.executeUpdate();
		log.info("AccountDAOImpl update method End");
	}




	@Override
	public List<AccountDTO> getUserAccountsById(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AccountDTO.class);
		//criteria.add(Restrictions.eq("userId", userId));
		Criterion c1 = Restrictions.eq("userId", userId);
	    Criterion c2 = Restrictions.eq("status", "OPEN");
	    Criterion c3 = Restrictions.and(c1, c2);
	    criteria.add(c3);
		log.info("AccountDAOImpl Search method End");
		return criteria.list();
	}
	
	@Override
	public List<AccountDTO> getUserAccounts(AccountDTO dto) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AccountDTO.class);
		criteria.add(Restrictions.eq("status", "OPEN"));
	    
		return criteria.list();
	}




	@Override
	public Long getTotalAmount(long userId) {
		Session session = entityManager.unwrap(Session.class);
		String sql = "SELECT SUM(amount) FROM AccountDTO WHERE userId="+userId+" AND status='OPEN'";
		System.out.println(sql);
		AccountDTO bean = null;
		Long amount = 0l;
		Long row = 0L;
		org.hibernate.query.Query<AccountDTO> query = session.createQuery(sql);
		 List<AccountDTO> resultList = query.getResultList();
		 for(Iterator it=query.iterate();it.hasNext();)
		  {
		  row = (Long) it.next();
		   System.out.print("sum: " + row);
		  }
		 amount = row;
	     System.out.println(amount);
		return amount;
	}




	@Override
	public Long getAmountByAccount(long userId, long accountId) {
		Session session = entityManager.unwrap(Session.class);
		String hql = "FROM AccountDTO WHERE userId="+userId+" AND id="+accountId+"";
		System.out.println(hql);
		AccountDTO bean = null;
		Long amount = 0l;
		
		org.hibernate.query.Query<AccountDTO> query = session.createQuery(hql);
		 List<AccountDTO> resultList = query.getResultList();
	     Iterator<AccountDTO> itr = resultList.iterator();
	     if(itr.hasNext()) {
	    	 bean = itr.next();
	    	 amount = bean.getAmount();
	     }
	     System.out.println(amount);
		return amount;
	}




	@Override
	public void changeStatus(AccountDTO dto) {
		// TODO Auto-generated method stub
		log.info("AccountDAOImpl changeStatus method Start");
		Session session = entityManager.unwrap(Session.class);
		org.hibernate.query.Query<AccountDTO> query = session.createSQLQuery("Update account set status='CLOSED' where id="+dto.getId()+"");
		query.executeUpdate();
		log.info("AccountDAOImpl changeStatus method End");
	}
}
