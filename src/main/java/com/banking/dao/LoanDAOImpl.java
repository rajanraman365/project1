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

import com.banking.dtos.LoanDTO;

@Repository
public class LoanDAOImpl implements LoanDAOInt{

	private static Logger log = Logger.getLogger(LoanDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(LoanDTO dto) {
		// TODO Auto-generated method stub
		log.info("LoanDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("LoanDAOImpl Add method ends");
		return pk;
	}




	@Override
	public List<LoanDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<LoanDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("LoanDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<LoanDTO> query = session.createQuery("from LoanDTO", LoanDTO.class);
		List<LoanDTO> list = query.getResultList();
		log.info("LoanDAOImpl List method End");
		return list;
	}

	@Override
	public LoanDTO findByAccountType(String aType) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(LoanDTO.class);
		criteria.add(Restrictions.eq("accountType", aType));
		LoanDTO dto = (LoanDTO) criteria.uniqueResult();
		return dto;
	}
	@Override
	public List<LoanDTO> search(LoanDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<LoanDTO> search(LoanDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("LoanDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(LoanDTO.class);
		if(dto.getUser().getRoleId()!=1) {
			criteria.add(Restrictions.eq("userId", dto.getUserId()));
		}
		
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
		log.info("LoanDAOImpl Search method End");
		return criteria.list();
	}




	@Override
	@Transactional
	public void delete(LoanDTO dto) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Query<LoanDTO> query = session.createSQLQuery("delete from account_types where id = "+dto.getId()+"");
		query.executeUpdate();
		//entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}




	@Override
	public LoanDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("LoanDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		LoanDTO dto = (LoanDTO) session.get(LoanDTO.class, pk);
		System.out.println("dto "+dto.getId());
		log.info("LoanDAOImpl FindByPk method End");
		return dto;
	}




	@Override
	public void update(LoanDTO dto) {
		// TODO Auto-generated method stub
		log.info("LoanDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		org.hibernate.query.Query<LoanDTO> query = session.createSQLQuery("Update loan set payable_amount="+dto.getPayableAmount()+" where id="+dto.getId()+"");
		query.executeUpdate();
		log.info("LoanDAOImpl update method End");
	}




	@Override
	public List<LoanDTO> getUserAccountsById(long userId) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(LoanDTO.class);
		//criteria.add(Restrictions.eq("userId", userId));
		Criterion c1 = Restrictions.eq("userId", userId);
	    Criterion c2 = Restrictions.eq("status", "OPEN");
	    Criterion c3 = Restrictions.and(c1, c2);
	    criteria.add(c3);
		log.info("LoanDAOImpl Search method End");
		return criteria.list();
	}




	@Override
	public Long getTotalAmount(long userId) {
		Session session = entityManager.unwrap(Session.class);
		String sql = "SELECT SUM(amount) FROM LoanDTO WHERE userId="+userId+" AND status='OPEN'";
		System.out.println(sql);
		LoanDTO bean = null;
		Long amount = 0l;
		Long row = 0L;
		org.hibernate.query.Query<LoanDTO> query = session.createQuery(sql);
		 List<LoanDTO> resultList = query.getResultList();
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
		String hql = "FROM LoanDTO WHERE userId="+userId+" AND id="+accountId+"";
		System.out.println(hql);
		LoanDTO bean = null;
		Long amount = 0l;
		
		org.hibernate.query.Query<LoanDTO> query = session.createQuery(hql);
		 List<LoanDTO> resultList = query.getResultList();
	     Iterator<LoanDTO> itr = resultList.iterator();
	     if(itr.hasNext()) {
	    	 bean = itr.next();
	    //	 amount = bean.getAmount();
	     }
	     System.out.println(amount);
		return amount;
	}




	@Override
	public void changeStatus(LoanDTO dto) {
		// TODO Auto-generated method stub
		log.info("LoanDAOImpl changeStatus method Start");
		Session session = entityManager.unwrap(Session.class);
		org.hibernate.query.Query<LoanDTO> query = session.createSQLQuery("Update account set status='CLOSED' where id="+dto.getId()+"");
		query.executeUpdate();
		log.info("LoanDAOImpl changeStatus method End");
	}




	
}
