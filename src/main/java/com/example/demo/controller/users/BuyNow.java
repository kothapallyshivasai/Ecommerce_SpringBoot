package com.example.demo.controller.users;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.entity.BankAccounts;
import com.example.demo.entity.BankTransactions;
import com.example.demo.entity.Buynow;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CustomerOrders;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Invoices;
import com.example.demo.entity.Products;
import com.example.demo.entity.Shipment;
import com.example.demo.repository.BankAccountsRepository;
import com.example.demo.repository.BankTransactionsRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerOrdersRepository;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.repository.InvoicesRepository;
import com.example.demo.service.BankAccountsService;
import com.example.demo.service.CustomerOrdersService;
import com.example.demo.service.InvoicesService;
import com.example.demo.service.ProductsService;
import com.example.demo.service.ShipmentService;

import jakarta.servlet.http.HttpSession;

@RestController
public class BuyNow {
	
	@Autowired
	CustomersRepository customersRepository;
	
	@Autowired
	ProductsService productsService; 
	
	@Autowired
	BankAccountsRepository bankAccountsRepository;
	
	@Autowired
	BankAccountsService bankAccountsService;
	
	@Autowired
	CustomerOrdersService customerOrdersService;
	
	@Autowired
	CustomerOrdersRepository customerOrdersRepository;
	
	@Autowired
	ShipmentService shipmentService;
	
	@Autowired
	InvoicesService invoicesService;
	
	@Autowired
	InvoicesRepository invoicesRepository;
	
	@Autowired
	BankTransactionsRepository bankTransactionsRepository;
	
	@Autowired
	CartRepository cartRepository;

	@GetMapping("/customer/buynow/{productCode}")
	public ModelAndView buyNow(Model model, @PathVariable("productCode") int productCode, HttpSession session) {
		Customers customer = customersRepository.findByEmailId(session.getAttribute("email_id").toString());
		Buynow buy = new Buynow();
		buy.setCustomer(customer);
		buy.setProduct((Products) productsService.getProductById(productCode));
		buy.setCustomerOrder(new CustomerOrders());
		buy.setInvoice(new Invoices());
		buy.setShipment(new Shipment());
		buy.setAccounts(bankAccountsRepository.findByEmailId(customer.getEmailId()));
		
		model.addAttribute("buy", buy);
		return new ModelAndView("users/buynow");
	}
	
	@PostMapping("/customer/placeorder/{productCode}")
	public ModelAndView placeOrder(@ModelAttribute("buy") Buynow buy, Model model,@PathVariable("productCode") int productCode, HttpSession session) {
		
		Customers customer = customersRepository.findByEmailId(session.getAttribute("email_id").toString());
		Shipment shipment = buy.getShipment();
		Invoices invoice = buy.getInvoice();
		Products product = (Products) productsService.getProductById(productCode);
		CustomerOrders order = new CustomerOrders();
		BankAccounts account = (BankAccounts) bankAccountsService.getBankAccountById(Long.parseLong(invoice.getCardNumber()));
	
		if(account.getBalanceAmount() > (product.getCost() - (product.getCost() * (product.getDiscount() / 100)))) {
			order.setCost(product.getCost());
			order.setDiscountAmount(product.getCost() * (product.getDiscount() / 100));
			order.setBillAmount(product.getCost() - (product.getCost() * (product.getDiscount() / 100)));
			order.setCustomer(customer);
			order.setDateTime(new Date(new java.util.Date().getTime()));
			order.setOrderStatus("SHIPPING");
			order.setProducts(product);
			customerOrdersRepository.save(order);
			CustomerOrders cus = (CustomerOrders) customerOrdersService.getCustomerOrderById(order.getOrderId());
			
			
			Shipment ship = new Shipment();
			ship.setCustomerOrders(cus);
			ship.setCustomers(customer);
			ship.setDeliveryAddress(shipment.getDeliveryAddress());
			ship.setMobileNumber(shipment.getMobileNumber());
			ship.setZipCode(shipment.getZipCode());
			shipmentService.addShipment(ship);

			
			Invoices i = new Invoices();
			i.setCardNumber(account.getCardNumber());
			i.setCardType(account.getCardType());
			i.setCustomerOrders(cus);
			i.setInvoiceAmount(cus.getBillAmount());
			i.setInvoiceDate(cus.getDateTime());
			invoicesRepository.save(i);
			
			float balance = account.getBalanceAmount();
			account.setBalanceAmount(balance - cus.getBillAmount());
			bankAccountsService.updateBankAccount(account);
			
			BankAccounts adminAccount = (BankAccounts) bankAccountsService.getBankAccountById((long) 123456);
			float adminBalance = adminAccount.getBalanceAmount();
			adminAccount.setBalanceAmount(adminBalance + cus.getBillAmount());
			bankAccountsService.updateBankAccount(adminAccount);
			
			BankTransactions transaction = new BankTransactions();
			transaction.setToAccount(adminAccount.getAccountNumber());
			transaction.setFromAccount(account.getAccountNumber());
			transaction.setAmount(cus.getBillAmount());
			transaction.setTransactionDate(new Date(new java.util.Date().getTime()));
			bankTransactionsRepository.save(transaction);
			try {
				Cart cart = cartRepository.findFirstByCustomersAndProductCode(customer, productCode);
				cartRepository.delete(cart);
		}
		catch(Exception e) {}
			
		}
		else {
			return new ModelAndView("redirect:/customer/bankaccounts");
		}
		
		return new ModelAndView("redirect:/customer/yourorders");
	}
	
}
