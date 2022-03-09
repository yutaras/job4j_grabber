package ru.job4j.gc;

import org.openjdk.jol.info.ClassLayout;

public class Test {
    public static void main(String[] args) {
        User a = new User(18, "Коля");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
