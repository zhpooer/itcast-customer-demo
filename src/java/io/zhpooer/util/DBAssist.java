package io.zhpooer.util;

import io.zhpooer.datasource.ResultSetHandler;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBAssist {

	private ConnManager manager = null;
	
	public DBAssist(ConnManager manager){
		this.manager = manager;
	}
	
	public void update(String sql, Object[] param){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = manager.getConnection();
			stmt = conn.prepareStatement(sql);
			ParameterMetaData pmd = stmt.getParameterMetaData();
			
			int paramSize = pmd.getParameterCount();
			if(paramSize>0){
				if(param==null) throw new RuntimeException("参数错误");
				if(paramSize == param.length){
					for(int i=0;i<paramSize;i++){
						stmt.setObject(i+1, param[i]);
					}
				} else {
					throw new RuntimeException("参数不齐");
				}
			}
			stmt.executeUpdate();
		} catch (Exception e){
			throw new RuntimeException(e);
		} finally {
			manager.release(conn, stmt, null);
		}
	}
	
	public Object query(String sql, Object[] param){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = manager.getConnection();
			stmt = conn.prepareStatement(sql);
			ParameterMetaData pmd = stmt.getParameterMetaData();
			
			int paramSize = pmd.getParameterCount();
			if(paramSize>0){
				if(param==null) throw new RuntimeException("参数错误");
				if(paramSize == param.length){
					for(int i=0;i<paramSize;i++){
						stmt.setObject(i+1, param[i]);
					}
				} else {
					throw new RuntimeException("参数不齐");
				}
			}
			rs = stmt.executeQuery();
			if(rs.next()) return rs.getObject(1);
			return null;
		} catch (Exception e){
			throw new RuntimeException(e);
		} finally {
			manager.release(conn, stmt, rs);
		}
	}
	
	public <E, T> T query(String sql, Object[] param, ResultSetHandler<E, T> rsh, Class<E> clazz){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = manager.getConnection();
			stmt = conn.prepareStatement(sql);
			ParameterMetaData pmd = stmt.getParameterMetaData();
			
			int paramSize = pmd.getParameterCount();
			if(paramSize>0){
				if(param==null) throw new RuntimeException("参数错误");
				if(paramSize == param.length){
					for(int i=0;i<paramSize;i++){
						stmt.setObject(i+1, param[i]);
					}
				} else {
					throw new RuntimeException("参数不齐");
				}
			}
			rs = stmt.executeQuery();
			return rsh.handler(rs, clazz);
		} catch (Exception e){
			throw new RuntimeException(e);
		} finally {
			manager.release(conn, stmt, rs);
		}
	}
}
