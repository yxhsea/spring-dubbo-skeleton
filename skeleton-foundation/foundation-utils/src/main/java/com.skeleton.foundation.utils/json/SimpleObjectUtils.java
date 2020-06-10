package com.skeleton.foundation.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.math.NumberUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 简单对象工具
 */
public class SimpleObjectUtils {
    private static ObjectMapper objectMapper = null;
    static {

        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
//                false);
    }

    public static String objectMap2json(Map<String,Object> map){
        try {
            String json = objectMapper.writeValueAsString(map);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace(); throw new RuntimeException("json convert exception.");
        }
    }

    public static Map<String, String> json2map(String json) {
        try {
            Map<String, String> map = objectMapper.readValue(json, Map.class);
            return map;
        } catch (Exception e) {
            e.printStackTrace(); throw new RuntimeException("json convert exception.");
        }
    }

    public static Map<String,Object> json2ObjectMap(String json){
        try {
            Map<String,Object> map = objectMapper.readValue(json, Map.class);
            return map;
        }catch (Exception e){
            e.printStackTrace(); throw new RuntimeException("json convert exception.",e);
        }
    }

    public static String objectData2json(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String json = objectMapper.writeValueAsString(obj);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("json convert exception.",e);
        }
    }

    public static <T> T json2ObjectData(String json, Class<T> type) {
        T obj = null;
        try {
            obj = objectMapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace(); throw new RuntimeException("json convert exception.");
        }
        return obj;
    }

    public static <T> T json2GenericObject(String json, TypeReference<T> tr) {
        try {
            return objectMapper.readValue(json, tr);
        } catch (Exception e) {
            e.printStackTrace(); throw new RuntimeException("json convert exception.");
        }
    }


    public static String convertSimpleObject2String(Object val) {
        if (val instanceof String) {
            return (String) val;
        } else if (val instanceof Integer) {
            Integer intVal = (Integer) val;
            return String.valueOf(intVal);
        } else if (val instanceof Long) {
            Long lngVal = (Long) val;
            return String.valueOf(lngVal);
        } else if (val instanceof Float) {
            Float fltVal = (Float) val;
            return String.valueOf(fltVal);
        } else if (val instanceof Date) {
            Date dateVal = (Date) val;
            return String.valueOf(dateVal.getTime());
        }
        return val.toString();
    }

    public static <T> T convertString2SimpleObject(String strVal, T defaultValue) {
        if (strVal == null) {
            return defaultValue;
        }
        if (defaultValue instanceof String) {
            return (T) strVal;
        } else if (defaultValue instanceof Integer) {
            return (T) Integer.valueOf(NumberUtils.toInt(strVal, (Integer) defaultValue));
        } else if (defaultValue instanceof Long) {
            return (T) Long.valueOf(NumberUtils.toLong(strVal, (Long) defaultValue));
        } else if (defaultValue instanceof Float) {
            return (T) Float.valueOf(NumberUtils.toFloat(strVal, (Float) defaultValue));
        } else if (defaultValue instanceof Date) {
            long time = NumberUtils.toLong(strVal, -1);
            if (time == -1) {
                return defaultValue;
            }
            return (T) new Date(time);
        } else {
            return (T) strVal;
        }
    }
    
    public static <T> T mapToBean(Map<String, Object> map, Class<T> obj) throws Exception {  
        if (map == null) {  
            return null;  
        }  
        Set<Entry<String, Object>> sets = map.entrySet();  
        T t = obj.newInstance();  
        Method[] methods = obj.getMethods();
        for (Entry<String, Object> entry : sets) {  
            String str = entry.getKey();  
            String setMethod = "set" + str.substring(0, 1).toUpperCase() + str.substring(1);  
            for (Method method : methods) {  
            	try {
            		if (method.getName().equals(setMethod)) {  
            			method.invoke(t, entry.getValue());  
            		}  
				} catch (Exception e) {
					e.printStackTrace(); throw new RuntimeException("json convert exception.");
				}
            }  
        }  
        return t;  
    }
    
    public static Map<String, Object> transBean2Map(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!"class".equals(key)) {
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
        return map;  
    } 
    
    public static Map<String, String> transBean2MapString(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        Map<String, String> map = new HashMap<String, String>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!"class".equals(key)) {
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, String.valueOf(value));  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
        return map;  
    }
}