package com.invoice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.entity.InvoiceDetails;

public interface InvoiceRepository extends JpaRepository<InvoiceDetails,Integer>{

}
