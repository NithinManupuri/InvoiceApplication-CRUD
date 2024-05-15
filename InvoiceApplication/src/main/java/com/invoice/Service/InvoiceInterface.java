package com.invoice.Service;

import java.util.List;

import com.invoice.entity.InvoiceDetails;
import com.invoice.entity.UserEntity;

public interface InvoiceInterface {

	
	public UserEntity registration(UserEntity user);
	
	public boolean login(String email,String password);
	
	public boolean createInvoice(InvoiceDetails details,Integer userId);
	
	public List<InvoiceDetails> getAllInvoices(Integer userId);
	
	public boolean editInvoice(InvoiceDetails invoiceDetails, Integer userId);
	
	public boolean deleteInvoice(Integer invoiceId);
	
	
}
