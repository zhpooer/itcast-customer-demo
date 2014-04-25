package io.zhpooer.dao.impl;

import io.zhpooer.dao.CustomerDao;
import io.zhpooer.datasource.ListResultSetHandler;
import io.zhpooer.datasource.ObjectResultSetHandler;
import io.zhpooer.domain.Customer;
import io.zhpooer.util.ConnManager;
import io.zhpooer.util.DBAssist;

import java.sql.Date;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

	ConnManager manager = ConnManager.getInstance();
	DBAssist da = new DBAssist(manager);

	@Override
	public List<Customer> findAll() {
		String sql = "select id, name, gender, birthday, cellphone"
		        + ", email, hobby, type, description from customer";
		return da.query(sql, null, new ListResultSetHandler<Customer>(),
		        Customer.class);

	}

	@Override
	public void save(Customer c) {
		String sql = "insert into customer(id, name, gender, "
		        + "birthday, cellphone, email, hobby, type, description) "
		        + "values(?,?,?,?,?,?,?,?,?)";
		Object[] param = { c.getId(), c.getName(), c.getGender(),
		        new Date(c.getBirthday().getTime()), c.getCellphone(),
		        c.getEmail(), c.getHobby(), c.getType(), c.getDescription() };
		da.update(sql, param);
	}

	@Override
	public Customer findById(String customerId) {
		String sql = "select id, name, gender, birthday, cellphone"
		        + ", email, hobby, type, description from customer where id=?";
		Object[] param = { customerId };
		return da.query(sql, param, new ObjectResultSetHandler<Customer>(),
		        Customer.class);
	}

	@Override
	public void delete(String customerId) {
		String sql = "delete from customer where id = ?";
		Object[] param = { customerId };
		da.update(sql, param);
	}

	@Override
	public void update(Customer c) {
		String sql = "update customer set name=?, gender=?, "
		        + "birthday=?, cellphone=?, "
		        + "email=?, hobby=?, type=?, description=? where id=?";
		Object[] param = { c.getName(), c.getGender(),
		        new Date(c.getBirthday().getTime()), c.getCellphone(),
		        c.getEmail(), c.getHobby(), c.getType(), c.getDescription(),
		        c.getId() };
		da.update(sql, param);

	}

	@Override
	public List<Customer> findPageCustomers(int offset, int size) {
		String sql = "select id, name, gender, birthday, cellphone"
		        + ", email, hobby, type, description from customer limit ?,?";
		Object[] param = { offset, size };
		return da.query(sql, param, new ListResultSetHandler<Customer>(),
		        Customer.class);
	}

	@Override
	public int getTotalRecordsNum() {
		String sql = "select count(*) from customer ";

		Object num = da.query(sql, null);
		if (num == null)
			return 0;
		else
			return Integer.parseInt(num.toString());

	}
}
