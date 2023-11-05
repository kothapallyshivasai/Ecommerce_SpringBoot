package com.example.demo.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Invoices;
import com.example.demo.repository.InvoicesRepository;
import com.example.demo.service.InvoicesService;

@Service
public class InvoicesServiceImplementation implements InvoicesService {
	
	@Autowired
	InvoicesRepository invoicesRepository;

	@Override
	public boolean invoiceIsExist(long invoiceId) {
		Optional<Invoices> invoice = invoicesRepository.findById(invoiceId);
		if(invoice.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void addInvoice(Invoices invoice) {
		if(!invoiceIsExist(invoice.getInvoiceId())) {
			invoicesRepository.save(invoice);
		}
	}

	@Override
	public List<Invoices> getInvoices() {
		return invoicesRepository.findAll();
	}

	@Override
	public void deleteInvoice(long invoiceId) {
		if(invoiceIsExist(invoiceId)) {
			invoicesRepository.deleteById(invoiceId);
		}
	}

	@Override
	public Object getInvoiceById(long invoiceId) {
		if(invoiceIsExist(invoiceId)) {
			return invoicesRepository.findById(invoiceId).get();
		}
		else {
			return new Exception();
		}
	}

	@Override
	public void updateInvoice(Invoices invoice) {
		if(invoiceIsExist(invoice.getInvoiceId())) {
			invoicesRepository.save(invoice);
		}
	}
	
}
