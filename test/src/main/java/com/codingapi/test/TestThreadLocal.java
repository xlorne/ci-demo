package com.codingapi.test;

import com.codingapi.test.xml.XmlInfo;

import java.util.ArrayList;
import java.util.List;

public class TestThreadLocal {

    private static ThreadLocal<TestThreadLocal> threadLocal = new ThreadLocal<>();

    public static TestThreadLocal get(){
        return threadLocal.get();
    }
    public static void set(TestThreadLocal testThreadLocal){
        threadLocal.set(testThreadLocal);
    }
    public static void clear(){
        threadLocal.set(null);
    }

    public TestThreadLocal() {
        list = new ArrayList<>();
        threadLocal.set(this);
    }

    private List<XmlInfo> list;

    public List<XmlInfo> getList() {
        return list;
    }
}
