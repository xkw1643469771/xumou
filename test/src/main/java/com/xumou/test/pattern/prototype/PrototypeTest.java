package com.xumou.test.pattern.prototype;

import org.junit.Test;

import java.util.HashMap;

/**
 *
 */
public class PrototypeTest {

    @Test
    public void test1(){
        PersonCache.initialize();
        Person p1 = PersonCache.getPerson(Student.class);
        Person p2 = PersonCache.getPerson(Student.class);
        Person p3 = PersonCache.getPerson(Teacher.class);
        Person p4 = PersonCache.getPerson(Teacher.class);
        print(p1,p2,p3,p4);
    }

    public void print(Object ... obj){
        for (Object o : obj) {
            System.out.print(o);
            System.out.print(" ");
        }
        System.out.println();
    }

}