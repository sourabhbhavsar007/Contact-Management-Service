package com.vuadience.ContactService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vuadience.ContactService.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {


	@Query(value = "SELECT * FROM CONTACTS C WHERE C.ZIPCODE like %:zipcode% ", nativeQuery = true)
	List<Contact> filterByZipcode(@Param("zipcode") String zipcode);

	@Query(value = "SELECT * FROM CONTACTS C WHERE C.FULL_NAME like %:name% ", nativeQuery = true)
	List<Contact> filterByFullName(String name);

	
}
