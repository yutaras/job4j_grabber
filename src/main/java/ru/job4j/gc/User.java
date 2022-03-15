package ru.job4j.gc;

public class User {
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public static void main(String[] args) {
        GCDemo.info();
        for (int i = 0; i < 10000; i++) {
            new User(i, "N" + i);
        }
        GCDemo.info();
    }
}
