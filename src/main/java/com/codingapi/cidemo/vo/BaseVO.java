package com.codingapi.cidemo.vo;

import com.codingapi.cidemo.utils.BeanUtils;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
public class BaseVO {

    public <T> T toBean(T target) {
        return BeanUtils.toBean(this, target);
    }
}
