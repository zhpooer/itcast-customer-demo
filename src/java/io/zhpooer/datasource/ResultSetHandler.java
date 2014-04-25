package io.zhpooer.datasource;

import java.sql.ResultSet;

public interface ResultSetHandler<E, T> {
	T handler(ResultSet rs, Class<E> clazz);
}
