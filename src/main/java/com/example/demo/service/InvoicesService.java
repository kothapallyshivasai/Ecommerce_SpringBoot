package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Invoices;

@Service
public interface InvoicesService {
	boolean invoiceIsExist(long invoiceId);
	void addInvoice(Invoices invoice);
	List<Invoices> getInvoices();
	void deleteInvoice(long invoiceId);
	Object getInvoiceById(long invoiceId);
	void updateInvoice(Invoices invoice);
}
