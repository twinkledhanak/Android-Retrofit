package com.example.twinkledhanak.cfg;

/**
 * Created by twinkle dhanak on 7/24/2017.
 */

public class Person
{
    String name;
    int age;
    public Person()
    {

    }

    public Person(String name,int age)
    {
        this.name = name;
        this.age = age;

    }

    public String getName()
    {
        return this.name;
    }

    public int getAge()
    {
        return this.age;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setAge(int age)
    {
        this.age = age;
    }


}
