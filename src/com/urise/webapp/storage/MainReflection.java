package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, RuntimeException {
        Resume r = new Resume();
        Field declaredField = r.getClass().getDeclaredFields()[0];
        declaredField.setAccessible(true);
        System.out.println(declaredField.getName());
        System.out.println(declaredField.get(r));
        declaredField.set(r, "new uuid");
        System.out.println(r);
    }
}
