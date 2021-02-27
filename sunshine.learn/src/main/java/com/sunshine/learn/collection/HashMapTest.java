package com.sunshine.learn.collection;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class HashMapTest extends T4  implements t1 {

    static {
        name = "hello";
    }

    private static final String lastName = "world";
    private static String name = "Hello";
    private  String instStr;

    public HashMapTest(){
        name = "world";
        instStr = "instTest";
    }

    public static void main(String[] args) {
        // HashMap<Integer, Person> map = new HashMap<>(64, 3);
        HashMap<Integer, Person> map = new HashMap<>();

        Person p1 = new Person(1, "tom", 23);
        map.put(p1.getId(), p1);

        synchronized (HashMapTest.class) {
            Person p2 = new Person(17, "tom", 23);
            map.put(p2.getId(), p2);
        }

        Person p2 = new Person(16, "cat", 24);

//        System.out.println(p1 > p2);

//        Person p3 = new Person(15, "cat", 24);
//        map.put(p3.getId(), p3);
//
//        Person p4 = new Person(17, "cat", 24);
//        map.put(p4.getId(), p4);
//
//        p4.setId(18);
//        System.out.println(map.get(p4.getId()));

        for (int i = 0; i < 60; i++) {
            Person p = new Person(i * 16, "cat" + i, 20 + i);
            map.put(p.getId(), p);
        }
        System.out.println(map);

        Set<Integer> keyset = map.keySet();
        for (Integer integer : keyset) {

        }
        System.out.println(HashMapTest.class.getClassLoader());

        t1 t11 = new HashMapTest();
        t11.f1();

        Parent parent = new Parent();
        parent.f1();

        P1 pp1 = new P1();
        pp1.f1();
        pp1.f1();

        t2 tt2 = new HashMapTest();
        t3 tt3 = new HashMapTest();
        t1 tt1 = new HashMapTest();

    }

//    @Override
    public void run() {

    }

    @Override
    public void f1() {
    }

    @Override
    public void f1(String a) {

    }

    @Override
    public void f2() {

    }

    @Override
    public String f3() {
        return null;
    }
}

@Data
class Person {
    private Integer id;
    private String name;
    private Integer age;

    public Person(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

class Parent{
    public void f1(){
        System.out.println("Parent: f1");
    }
}

class P1 extends Parent implements t2{

    public void f1(){
        System.out.println("P1: f1");
    }

}

interface t1 extends t2, t3{

    void f1();

    void f1(String a);

    void f2();

    String f3();

}

interface t2{

    String name = "null";

    void f1();

    static void ff1(){

    }

}

interface t3{

}

abstract class T4{

}