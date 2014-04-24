package io.zhpooer.dao;

import io.zhpooer.domain.Customer;

import java.util.List;

public interface CustomerDao {
	@Deprecated
	List<Customer> findAll();
    void save(Customer c);
    Customer findById(String customerId);
    void delete(String customerId);
    void update(Customer c);
    List<Customer> findPageCustomers(int offset, int size);
	int getTotalRecordsNum();
}
