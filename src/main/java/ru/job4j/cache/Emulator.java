package ru.job4j.cache;

import java.io.IOException;
import java.util.Scanner;


public class Emulator {
    public static void main(String[] args) throws IOException {
        System.out.println("Write cache directory: ");
        var sc = new Scanner(System.in);
        var directory = sc.next();
        DirFileCache dirFileCache = new DirFileCache(directory);
        System.out.println("Write name of file for cash");
        var nameOfFile = sc.next();
        dirFileCache.load(nameOfFile);
        System.out.println("the file cashed");
        System.out.println(dirFileCache.get(nameOfFile));
    }
}
