package io.zhpooer.datasource;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ObjectResultSetHandler<T> implements ResultSetHandler<T, T> {

	@Override
    public T handler(ResultSet rs, Class<T> clazz) {
		try {
	        if(rs.next()){
	        	ResultSetMetaData rmd = rs.getMetaData();
	        	int columnCount = rmd.getColumnCount();
	        	T obj = clazz.newInstance();
	        	for (int i = 0; i < columnCount; i++) {
	                String name = rmd.getColumnName(i+1);
	                Field field = clazz.getDeclaredField(name.toLowerCase());
	                field.setAccessible(true);
	                field.set(obj, rs.getObject(name));
                }
	        	return obj;
	        }
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
	    return null;
    }

}
