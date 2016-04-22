package com.lance.dubbo.provider.model;

import java.io.Serializable;

/**
 * Created by perdonare on 2016/4/22.
 */
public class ProviderRequest implements Serializable{
    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProviderRequest{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
