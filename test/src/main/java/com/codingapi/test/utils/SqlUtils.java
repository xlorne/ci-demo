package com.codingapi.test.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUtils {

    public static SqlParam parser(String initCmd, Object object) {
        Map<String,Object> map = (Map) JSONObject.toJSON(object);
        List<Object> params = new ArrayList<>();
        SqlParam sqlParam = new SqlParam();

        String regex = "\\#\\{([^}]*)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(initCmd);
        while (matcher.find()){
            String key = matcher.group(1);
            Object val = map.get(key);
            params.add(val);
            initCmd = initCmd.replace("#{" + key + "}", "?");
        }
        sqlParam.setSql(initCmd);
        sqlParam.setParams(params.toArray());
        return sqlParam;
    }

    @Data
    public static class SqlParam{
        String sql;
        Object[] params;
    }

}
