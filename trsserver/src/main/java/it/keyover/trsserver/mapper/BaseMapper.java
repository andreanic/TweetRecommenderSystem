package it.keyover.trsserver.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseMapper {
	
	public static <T> Object map(T source, T mapper) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
				
		Class clazz = Class.forName(mapper.getClass().getName());		
		
		Method method = clazz.getMethod("map", source.getClass());
		
		return method.invoke(null,source);
	}
}
