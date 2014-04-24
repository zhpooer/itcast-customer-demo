package io.zhpooer.service.impl;

import io.zhpooer.bean.Page;
import io.zhpooer.dao.CustomerDao;
import io.zhpooer.dao.impl.CustomerDaoImpl;
import io.zhpooer.domain.Customer;
import io.zhpooer.exception.CustomerIdConnotBeEmpty;
import io.zhpooer.service.BusinessService;

import java.util.List;
import java.util.UUID;

public class BusinessServiceImpl implements BusinessService {
	CustomerDao dao = new CustomerDaoImpl();

	@SuppressWarnings("deprecation")
    @Override
	public List<Customer> findAll() {
		return dao.findAll();
	}

	@Override
	public void addCustomer(Customer c) {
		c.setId(UUID.randomUUID().toString());
		dao.save(c);
	}

	@Override
	public void delCustomer(String customerId) {
		dao.delete(customerId);
	}

	@Override
	public Customer findCustomerById(String customerId) {
		return dao.findById(customerId);
	}

	@Override
	public void updateCustomer(Customer c) throws CustomerIdConnotBeEmpty {
		if(c.getId() == null) throw new CustomerIdConnotBeEmpty();
		dao.update(c);
	}

	@Override
    public Page findPage(String num) {
		int pageNum = 1;
        if(num!=null){
            pageNum = Integer.parseInt(num);
        }
        int totalRecords = dao.getTotalRecordsNum();
        Page page = new Page(pageNum, totalRecords);
        page.setRecorder(dao.findPageCustomers(page.getStartIndex(), page.getPageSize()));
	    return page;
    }

}
