package io.zhpooer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.zhpooer.domain.Customer;
import io.zhpooer.exception.CustomerIdConnotBeEmpty;
import io.zhpooer.service.impl.BusinessServiceImpl;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class BusinessServiceTest {

    public BusinessService s = new BusinessServiceImpl();
    @Test public void testAddCustomer() {
        Customer c = new Customer();
        c.setId("aaa");
        c.setName("xxx");
        c.setGender("1");
        c.setBirthday(new Date());
        c.setEmail("zhpo@gmai.com");
        c.setHobby("aa bb");
        c.setType("vip");
        c.setDescription("xxx");
        s.addCustomer(c);
    }
    @Test public void testFindAll() {
        List<Customer> cs = s.findAll();
        assertEquals(2, cs.size());
    }
    @Test public void testFindCustomerById() {
       Customer c = s.findCustomerById("203a1381-ba77-4633-bf3b-64b1edd23a52");
       assertNotNull(c);
    }
    @Test(expected=CustomerIdConnotBeEmpty.class)
    public void testUpdateCustomer() throws CustomerIdConnotBeEmpty {
        Customer c = new Customer();
        s.updateCustomer(c);
    }
    @Test public void testUpdateCustomer1() throws CustomerIdConnotBeEmpty {
        Customer c = s.findCustomerById("203a1381-ba77-4633-bf3b-64b1edd23a52");
        c.setName("xxx1");
        c.setGender("0");
        s.updateCustomer(c);
    }
    @Test public void testDelCustomer() {
        s.delCustomer("xx");
    }
}
