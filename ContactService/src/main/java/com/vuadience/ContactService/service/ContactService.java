package com.vuadience.ContactService.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.vuadience.ContactService.model.Contact;
import com.vuadience.ContactService.repository.ContactRepository;

@Service
public class ContactService {

	private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

	@Autowired
	private ContactRepository contactRepository;

	public ContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}

	public ResponseEntity<Contact> findById(long contactId) {
		return contactRepository.findById(contactId).map(contact -> ResponseEntity.ok().body(contact))
				.orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<@Valid Contact> addContact(@Valid Contact contact) throws URISyntaxException {

		try {
			Contact newContact = contactRepository.save(contact);
			logger.info("Contact added to H2 database : " + contact);
			return ResponseEntity.created(new URI("/add/" + newContact.getContactId())).body(contact);

		} catch (HttpStatusCodeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	public List<Contact> filterByZipCode(String zipcode) {
		return contactRepository.filterByZipcode(zipcode);
	}

	public List<Contact> filterByFullName(String name) {
		return contactRepository.filterByFullName(name);
	}

}
