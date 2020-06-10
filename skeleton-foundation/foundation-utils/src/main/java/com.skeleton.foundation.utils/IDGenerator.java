package com.skeleton.foundation.utils;

import java.util.UUID;

/**
 * id 生成器
 **/
public class IDGenerator {
    public static String uuid(){
       return UUID.randomUUID().toString();
    }
}
