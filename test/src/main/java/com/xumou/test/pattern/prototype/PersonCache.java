package com.xumou.test.pattern.prototype;

import java.util.HashMap;

/**
 *
 */
public class PersonCache {

    private PersonCache(){}

    public static void initialize(){
        Student student = new Student();
        PersonMap.map.put(student.getClass(),student);
        Teacher teacher = new Teacher();
        PersonMap.map.put(teacher.getClass(),teacher);
    }

    private static class PersonMap{
        static HashMap<Class,Person> map = new HashMap<>();
    }

    public static Person getPerson(Class clazz){
        Person person = PersonMap.map.get(clazz);
        return person.clone();
    }

}