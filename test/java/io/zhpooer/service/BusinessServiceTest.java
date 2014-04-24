package io.zhpooer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.zhpooer.domain.Customer;
import io.zhpooer.exception.CustomerIdConnotBeEmpty;
import io.zhpooer.service.impl.BusinessServiceImpl;
import io.zhpooer.util.ConnManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BusinessServiceTest{

	public BusinessService s = new BusinessServiceImpl();
	private Customer c0;
	private Customer c1;

	@Before
	public void setup() {
		c0 = new Customer();
		c0.setName("c0");
		c0.setGender("0");
		c0.setBirthday(new Date());
		c0.setEmail("test@r.com");
		c0.setHobby("玩游戏");
		c0.setType("普通用户");
		c0.setDescription("我是c0");

		c1 = new Customer();
		c1.setName("c1");
		c1.setGender("1");
		c1.setBirthday(new Date());
		c1.setEmail("t@t.com");
		c1.setHobby("看小说");
		c1.setType("vip");
		c1.setDescription("我不是c0");
	}
	
	@After
	public void teardown() throws SQLException{
		Connection conn = ConnManager.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement("truncate table customer");
		stmt.executeUpdate();		
	}

	@Test
	public void testAddCustomer() {
		s.addCustomer(c0);
	}

	@Test
	public void testFindAll() {
		s.addCustomer(c0);
		s.addCustomer(c1);
		List<Customer> cs = s.findAll();
		assertEquals(2, cs.size());
	}

	@Test
	public void testFindCustomerById() {
		s.addCustomer(c0);
		List<Customer> cs = s.findAll();
		cs.get(0);
		Customer c = s.findCustomerById(cs.get(0).getId());
		assertNotNull(c);
	}

	@Test(expected = CustomerIdConnotBeEmpty.class)
	public void testUpdateCustomer() throws CustomerIdConnotBeEmpty {
		Customer c = new Customer();
		s.updateCustomer(c);
	}

	@Test
	public void testUpdateCustomer1() throws CustomerIdConnotBeEmpty {
		s.addCustomer(c0);
		List<Customer> cs = s.findAll();
 		Customer c = cs.get(0);
		
		c.setName("c3");
		s.updateCustomer(c);
	}

	@Test
	public void testDelCustomer() {
		s.addCustomer(c0);
		List<Customer> cs = s.findAll();
 		Customer c = cs.get(0);

		s.delCustomer(c.getId());
		cs = s.findAll();
		assertEquals(0, cs.size());
	}
}
