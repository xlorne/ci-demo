package com.codingapi.cidemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReq extends BaseVO {

    @NotNull
    private String name;


}
