package com.example.LoginwithJWTdemo.dao;


import com.example.LoginwithJWTdemo.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepositorylmpl implements UserRepository{

	// define field for entity manager
	@Autowired
	private EntityManager entityManager;
	
		
	@Override
	public User findByUserName(String username) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where username=:uName", User.class);
		theQuery.setParameter("uName", username);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}
		
		
		return theUser;
	}

	@Override
	public void save(User user) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(user);
		
	}

	@Override
	public User findById(int user_id) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		User theUser = currentSession.get(User.class, user_id);

		return theUser;
	}

	

}
