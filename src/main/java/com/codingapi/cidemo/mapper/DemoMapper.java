package com.codingapi.cidemo.mapper;

import com.codingapi.cidemo.domain.Demo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lorne
 * @date 2019/7/27
 * @description
 */
@Mapper
public interface DemoMapper {

    @Insert("insert into t_demo(name) values(#{name})")
    int save(Demo demo);

    @Select("select * from t_demo")
    List<Demo> list();

    @Select("select * from t_demo where name = #{name}")
    Demo getByName(@Param("name") String name);
}
