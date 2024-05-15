package com.invoice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.Service.InvoiceInterface;
import com.invoice.entity.InvoiceDetails;
import com.invoice.entity.UserEntity;


@RestController
public class InvoiceRestController { 
	
	@Autowired
	private InvoiceInterface service;
	
	@PostMapping("/reg")
	public ResponseEntity<String> userReg(@RequestBody UserEntity user){
		 UserEntity registration = service.registration(user);
		if(registration!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Registered");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Give unquie Deatils");
		
	}
	
	@GetMapping("/login")
	public ResponseEntity<String> userLogin(@RequestParam("email")String email,@RequestParam("password") String password ){
		 boolean login = service.login(email, password);
		if(login) {
			return ResponseEntity.status(HttpStatus.OK).body("Logined");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid credentials");
		
	}
	
	@PostMapping("/invoice/{userId}")
	public ResponseEntity<String> createInvoice(@RequestBody InvoiceDetails details, @PathVariable("userId")Integer userId){
		System.out.println(userId);
		boolean invoice = service.createInvoice(details, userId);
		
		if(invoice) {
			return ResponseEntity.status(HttpStatus.CREATED).body("createdInvoice");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed To create");
		
	}
	
	
	@PutMapping("/edit/{userId}")
	public ResponseEntity<String> editInvoice(@RequestBody InvoiceDetails details,@PathVariable("userId") Integer userId){
		boolean invoice = service.editInvoice(details,userId);
		if(invoice) {
			return ResponseEntity.status(HttpStatus.OK).body("Edited");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed To Edit");
		
	}
	
	@DeleteMapping("/delete/{invoiceId}")
	public ResponseEntity<String> deleteInvoice(@PathVariable("invoiceId") Integer invoiceId){
		
		 boolean deleteInvoice = service.deleteInvoice(invoiceId);
		if(deleteInvoice) {
			return ResponseEntity.status(HttpStatus.OK).body("deleted");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed To delete");
		
	}
	
	
	@GetMapping("/userInvoices/{userId}")
	public ResponseEntity<List<InvoiceDetails>> getUserInvoices(@PathVariable("userId") Integer userId){
		 List<InvoiceDetails> userInvoices = service.getAllInvoices(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(userInvoices);
		
	}
	
	
	
	
	
	
	

}
