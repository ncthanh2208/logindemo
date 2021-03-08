package com.example.LoginwithJWTdemo.dao;


import com.example.LoginwithJWTdemo.entity.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Role findRoleByName(String theRoleName) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);
		
		Role theRole = null;
		try {
			theRole = theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}
		return theRole;
	}

}
