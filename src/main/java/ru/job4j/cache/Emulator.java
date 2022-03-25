package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Emulator {
    public static void main(String[] args) throws IOException {
        System.out.println("Write cache directory: ");
        var sc = new Scanner(System.in);
        var directory = sc.next();
        Path path = Paths.get(directory);
        DirFileCache dirFileCache = new DirFileCache(directory);
        System.out.println("Write name of file for cash");
        var nameOfFile = sc.next();
        File file = new File(directory + "\\" + nameOfFile);
        if (Files.exists(path) && file.exists()) {
            dirFileCache.load(nameOfFile);
            System.out.println("the file cashed");
            System.out.println(dirFileCache.get(nameOfFile));
        } else {
            System.out.println("directory or file not found");
        }
    }
}
