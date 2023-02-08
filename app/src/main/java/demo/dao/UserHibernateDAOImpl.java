package demo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.model.User;


@Repository
@Primary
public class UserHibernateDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public User getUser(int id) {
		Session session=sessionFactory.getCurrentSession();
		User user=session.find(User.class, id);
		return user;
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery < User > cq = cb.createQuery(User.class);
        Root < User > root = cq.from(User.class);
        cq.select(root);
        return session.createQuery(cq).getResultList();
	}

	@Override
	@Transactional
	public int createUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		return user.getId();
	}

	@Override
	@Transactional
	public int updateUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		return user.getId();
	}

	@Override
	@Transactional
	public int deleteUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<User> query=session.createNativeQuery("DELETE from users where id=:id");
		query.setParameter("id", id);
		return query.executeUpdate();
	}

}
