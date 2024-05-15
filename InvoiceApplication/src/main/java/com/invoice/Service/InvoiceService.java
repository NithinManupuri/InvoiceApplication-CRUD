
package com.invoice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.entity.InvoiceDetails;
import com.invoice.entity.UserEntity;
import com.invoice.repo.InvoiceRepository;
import com.invoice.repo.UserRepository;

@Service
public class InvoiceService implements InvoiceInterface {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private InvoiceRepository invoiceRepo;
 
    @Override
    public UserEntity registration(UserEntity user) {
        UserEntity existingUser = userRepo.findByEmailOrPasswordOrUserName(
            user.getEmail(), user.getPassword(), user.getUserName());
        
        if (existingUser != null) {
          
            return null;
        }
        
        try {
            return userRepo.save(user);
        } 
           catch (Exception ex) {
            ex.printStackTrace();
         
            return null;
        }
    }

    @Override
    public boolean login(String email, String password) {
       
        UserEntity user = userRepo.findByEmailAndPassword(email, password);
   
        return user != null;
    }

    @Override
    public boolean createInvoice(InvoiceDetails details, Integer userId) {
        try {
         
            Optional<UserEntity> optionalUser = userRepo.findById(userId);
            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();
               
                details.setUser(user);
                
                invoiceRepo.save(details);
                return true;
            }
        } catch (Exception ex) {
           
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<InvoiceDetails> getAllInvoices(Integer userId) {
       
        return invoiceRepo.findAllByUserUserId(userId);
    }

    @Override
    public boolean editInvoice(InvoiceDetails invoiceDetails,Integer userId) {
    	  Optional<UserEntity> optionalUser = userRepo.findById(userId);
          if (optionalUser.isPresent()) {
              UserEntity user = optionalUser.get();
              try {
             
                invoiceDetails.setUser(user);
                invoiceRepo.save(invoiceDetails);
              return true;
             } catch (Exception ex) {
           
            ex.printStackTrace();
            return false;
             }
          }else {
        	return false;
        }
    }

    @Override
    public boolean deleteInvoice(Integer invoiceId) {
        try {
          
            invoiceRepo.deleteById(invoiceId);
            return true;
        } catch (Exception ex) {
           
            ex.printStackTrace();
            return false;
        }
    }

	
}
