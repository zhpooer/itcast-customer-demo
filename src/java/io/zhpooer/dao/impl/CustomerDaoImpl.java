package io.zhpooer.dao.impl;

import io.zhpooer.dao.CustomerDao;
import io.zhpooer.domain.Customer;
import io.zhpooer.util.ConnManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

	ConnManager manager = ConnManager.getInstance();

	@Override
	public List<Customer> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = manager.getConnection();
			stmt = conn
			        .prepareStatement("select id, name, gender, birthday, cellphone"
			                + ", email, hobby, type, description from customer");
			ResultSet rs = stmt.executeQuery();
			List<Customer> l = new ArrayList<Customer>();
			while (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getString(1));
				c.setName(rs.getString(2));
				c.setGender(rs.getString(3));
				c.setBirthday(rs.getDate(4));
				c.setCellphone(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setHobby(rs.getString(7));
				c.setType(rs.getString(8));
				c.setDescription(rs.getString(9));
				l.add(c);
			}
			return l;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			manager.release(conn, stmt, null);
		}
	}

	@Override
	public void save(Customer c) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = manager.getConnection();
			stmt = conn
			        .prepareStatement("insert into customer(id, name, gender, "
			                + "birthday, cellphone, email, hobby, type, description) "
			                + "values(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, c.getId());
			stmt.setString(2, c.getName());
			stmt.setString(3, c.getGender());
			stmt.setDate(4, new Date(c.getBirthday().getTime()));
			stmt.setString(5, c.getCellphone());
			stmt.setString(6, c.getEmail());
			stmt.setString(7, c.getHobby());
			stmt.setString(8, c.getType());
			stmt.setString(9, c.getDescription());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			manager.release(conn, stmt, null);
		}
	}

	@Override
	public Customer findById(String customerId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = manager.getConnection();
			stmt = conn
			        .prepareStatement("select id, name, gender, birthday, cellphone"
			                + ", email, hobby, type, description from customer where id=?");
			stmt.setString(1, customerId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getString(1));
				c.setName(rs.getString(2));
				c.setGender(rs.getString(3));
				c.setBirthday(rs.getDate(4));
				c.setCellphone(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setHobby(rs.getString(7));
				c.setType(rs.getString(8));
				c.setDescription(rs.getString(9));
				return c;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			manager.release(conn, stmt, null);
		}
	}

	@Override
	public void delete(String customerId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = manager.getConnection();
			stmt = conn.prepareStatement("delete from customer where id = ?");
			stmt.setString(1, customerId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			manager.release(conn, stmt, null);
		}
	}

	@Override
	public void update(Customer c) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = manager.getConnection();
			stmt = conn.prepareStatement("update customer set name=?, gender=?, "
			        + "birthday=?, cellphone=?, "
			        + "email=?, hobby=?, type=?, description=? where id=?");
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getGender());
			stmt.setDate(3, new Date(c.getBirthday().getTime()));
			stmt.setString(4, c.getCellphone());
			stmt.setString(5, c.getEmail());
			stmt.setString(6, c.getHobby());
			stmt.setString(7, c.getType());
			stmt.setString(8, c.getDescription());
			stmt.setString(9, c.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			manager.release(conn, stmt, null);
		}
	}

}
