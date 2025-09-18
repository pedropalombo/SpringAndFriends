package com.rest.webservices.restful_web_services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservices.restful_web_services.user.User;

// interface to persist data onto H2's db
// JPARepo -> db's methods | <Table's_Class, Table's_ID_type>
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
