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
        testThreadLocal.set(testThreadLocal);
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
