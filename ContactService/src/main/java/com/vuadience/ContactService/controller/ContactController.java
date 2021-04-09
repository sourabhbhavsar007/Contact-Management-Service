package com.vuadience.ContactService.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vuadience.ContactService.model.Contact;
import com.vuadience.ContactService.service.ContactService;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
	
	
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    

	@Autowired
	private ContactService contactService;

	 public ContactController(ContactService contactService) {
	      this.contactService = contactService;
	 }
	 
	 
	 /**
	  * Endpoint for getting all contacts
	  * 
	  */
	 
	 @GetMapping("/contacts")
	 public ResponseEntity<List<Contact>> findAllContacts() {
	        
		 List<Contact> contacts = contactService.findAllContacts();
	     logger.info("Querying for all the contacts...");
	     return contacts != null ? new ResponseEntity<>(new ArrayList<Contact>(contacts), HttpStatus.OK) 
	                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);        
	 }
	 
	 
	 
	 /**
	  * 
	  * Endpoint for fetching particular contact by searching on contactId
	  * 
	  * @param contactId
	  * @return
	  */
	 
	 @GetMapping(value = "/contacts/{contactId}")
	 public ResponseEntity<Contact> findContactById(@PathVariable long contactId) {
	     
		 logger.info("Querying for contactId " + contactId);
		 
		 try {
	         return contactService.findById(contactId);  // return 200, with json body
	     
		 } catch (RuntimeException e) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
	     }
		 
	 }
	 
	 
	 /**
	  * Endpoint to add new contact. This stores newly added contact in our in-memory H2 database.
	  * 
	  * @param contact
	  * @return
	  * @throws URISyntaxException
	  */
	 
	 @PostMapping(value = "/contacts")
	 public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) throws URISyntaxException {
	     
		 logger.info("Request to add contact : " + contact);
		 
		 return contactService.addContact(contact);
	     
	 }
	 
	 
	 
	 /**
	  * Endpoint to fliter on zipcode. This will work similar to LIKE clause in SQL.
	  * 
	  * We pass a query/keyword and only records whose zipcode matches the given query are fetched.
	  * 
	  * For example : if we pass query as 42, records whose zipocde has 42 in it, will be fetched, like 425000, 428980, 435642
	  * 
	  * @param zipcode
	  * @return
	  */
	 
	 @GetMapping("/contacts/filter/zipcode/{zipcode}")
	 public ResponseEntity<List<Contact>> filterByZipCode(@PathVariable("zipcode") String zipcode) {
	     
	     logger.info("Querying to filter on zipcode with keyword : " + zipcode);
		 List<Contact> contacts = contactService.filterByZipCode(zipcode);
	     return contacts != null ? new ResponseEntity<>(new ArrayList<Contact>(contacts), HttpStatus.OK) 
	                : new ResponseEntity<>(HttpStatus.NOT_FOUND);        
	 }
	 
	 
	 
	 /**
	  * Endpoint to filter on full name. This will work similar to LIKE clause in SQL.
	  * 
	  * We pass a query/keyword and only records whose full name matches the given query are fetched.
	  * 
	  * For example : if we pass query as Jam, records whose full name has Jam in it, will be fetched, like James, Jamie, Jameson
	  * 
	  * @param fullname
	  * @return
	  */
	 
	 @GetMapping("/contacts/filter/name/{name}")
	 public ResponseEntity<List<Contact>> filterByFullName(@PathVariable("name") String name) {
	     
	     logger.info("Querying to filter on name with keyword : " + name);
		 List<Contact> contacts = contactService.filterByFullName(name);
	     return contacts != null ? new ResponseEntity<>(new ArrayList<Contact>(contacts), HttpStatus.OK) 
	                : new ResponseEntity<>(HttpStatus.NOT_FOUND);        
	 }
	 
	 
	 
}
