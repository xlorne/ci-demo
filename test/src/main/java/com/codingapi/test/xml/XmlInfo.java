package com.codingapi.test.xml;

import com.codingapi.test.annotation.DBType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class XmlInfo<T> {

    private String name;
    private String path;
    private String initCmd;
    private DBType dbType;

    private List<T> list;

    public XmlInfo() {
        list = new ArrayList<>();
    }
}
