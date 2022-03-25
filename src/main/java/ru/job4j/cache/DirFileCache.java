package ru.job4j.cache;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String value = null;
        try {
            value = Files.readString(Path.of(cachingDir, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
