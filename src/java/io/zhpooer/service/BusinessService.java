package io.zhpooer.service;

import io.zhpooer.domain.Customer;
import io.zhpooer.exception.CustomerIdConnotBeEmpty;

import java.util.List;

public interface BusinessService {
	List<Customer> findAll();

	void addCustomer(Customer c);

	void delCustomer(String customerId);

	Customer findCustomerById(String customerId);

	// 如果传入 id 为 null, 抛出此异常
	void updateCustomer(Customer c) throws CustomerIdConnotBeEmpty;

}
