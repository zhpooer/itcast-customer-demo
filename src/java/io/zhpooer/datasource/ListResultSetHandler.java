package io.zhpooer.datasource;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ListResultSetHandler <E> implements ResultSetHandler<E, List<E>> {

	@Override
    public List<E> handler(ResultSet rs, Class<E> clazz) {
		try {
			List<E> t = new ArrayList<E>();
	        while(rs.next()){
	        	ResultSetMetaData rmd = rs.getMetaData();
	        	int columnCount = rmd.getColumnCount();
	        	E obj = clazz.newInstance();
	        	for (int i = 0; i < columnCount; i++) {
	                String name = rmd.getColumnName(i+1);
	                Field field = clazz.getDeclaredField(name.toLowerCase());
	                field.setAccessible(true);
	                field.set(obj, rs.getObject(name));
                }
	        	t.add(obj);
	        }
	        return t;
        } catch (Exception e) {
        	throw new RuntimeException(e);

        }
    }


}
