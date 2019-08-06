package com.codingapi.cidemo.utils;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
public class BeanUtils {

    public static <T> T toBean(Object source, T target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
        return target;
    }
}
