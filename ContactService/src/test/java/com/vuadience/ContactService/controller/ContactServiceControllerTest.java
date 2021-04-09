package com.vuadience.ContactService.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.vuadience.ContactService.AbstractTest;
import com.vuadience.ContactService.model.Address;
import com.vuadience.ContactService.model.Contact;


public class ContactServiceControllerTest extends AbstractTest {

   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
	
   @Test
   public void test_getAllContacts() throws Exception {
      String uri = "/contacts";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Contact[] contacts = super.mapFromJson(content, Contact[].class);
      assertTrue(contacts.length > 0);
   }
   
   @Test
   public void test_addContact() throws Exception {
      String uri = "/contacts";
      Contact contact = new Contact();
      contact.setContactId(10000L);
      contact.setFullName("James Bond");
      contact.setDateOfBirth(new Date(System.currentTimeMillis()));
      contact.setAddress(new Address("London", "411027"));
      
      String inputJson = super.mapToJson(contact);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(201, status);
   }
   
}