package com.xumou.test.pattern.prototype;

/**
 *
 */
public abstract class Person implements Cloneable {

    public abstract void active();

    @Override
    public Person clone() {
        try {
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}