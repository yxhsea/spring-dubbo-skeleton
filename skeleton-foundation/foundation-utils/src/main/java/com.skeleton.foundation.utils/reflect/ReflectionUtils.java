package com.skeleton.foundation.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类
 */
public class ReflectionUtils {
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 * 
	 * @param object
	 *            读取对象
	 * @param fieldName
	 *            属性名称
	 * @return 属性值
	 */
	@SuppressWarnings("rawtypes")
	public static Object getFieldValue(final Object object, final String fieldName) {
		if (object == null) {
            return null;
        }

		if (Map.class.isAssignableFrom(object.getClass())) {
			return ((Map) object).get(fieldName);
		}

		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:", e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 * 
	 * @param object
	 *            设置对象
	 * @param fieldName
	 *            属性名称
	 * @param value
	 *            属性值
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:", e.getMessage());
		}
	}

	/**
	 * 如果属性存在，直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 * 
	 * @param object
	 *            设置对象
	 * @param fieldName
	 *            属性名称
	 * @param value
	 *            属性值
	 */
	public static void setExistFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
            return;
        }

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:", e.getMessage());
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 */
	private static Field getDeclaredField(final Object object, final String fieldName) {
		if (object == null || fieldName == null) {
            return null;
        }
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	public static Field getDeclaredField(final Class<?> clazz, final String fieldName) {
		if (clazz == null || fieldName == null) {
            return null;
        }
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 强制转换fileld可访问.
	 */
	private static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型,不适用接口泛型. 如public UserDao extends HibernateDao<User>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be determined
	 */
	public static Class<?> getSuperClassGenricType(final Class<?> clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型,不适用接口泛型. 如public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be determined
	 */

	public static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
			return Object.class;
		}

		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class<?>) params[index];
	}

	/**
	 * 获取复合对象内字段值
	 * 
	 * @param object
	 *            原始对象
	 * @param fieldNames
	 *            字段层级
	 * @return 最后一层字段值
	 */
	public static Object getFieldValue(final Object object, final String[] fieldNames) {
		if (object == null || fieldNames == null || fieldNames.length == 0) {
            return null;
        }

		Object obj = object;
		for (int i = 1; i < fieldNames.length; i++) {
			obj = getFieldValue(obj, fieldNames[i]);
		}

		return obj;
	}

}
