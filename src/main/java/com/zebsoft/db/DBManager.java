package com.zebsoft.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DBManager<T> {
    private final File dbFile;
    private final ObjectMapper objectMapper;
    private final Class<T> clazz;

    public DBManager(String filename, Class<T> clazz) {
        this.dbFile = new File(filename);
        this.objectMapper = new ObjectMapper();
        this.clazz = clazz;
        // Initialize the file if it doesn't exist
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
                objectMapper.writeValue(dbFile, new ArrayList<T>());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Create or Update an object
    public void save(T obj) throws IOException {
        List<T> objects = readAll();
        objects.add(obj);
        objectMapper.writeValue(dbFile, objects);
    }

    // Read all objects from file
    public List<T> readAll() throws IOException {
        if (!dbFile.exists() || dbFile.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(dbFile, new TypeReference<List<T>>() {
        });
    }

    // Read an object by a custom matcher (e.g., id match)
    public Optional<T> findBy(Matcher<T> matcher) throws IOException {
        return readAll().stream()
                .filter(matcher::matches)
                .findFirst();
    }

    // Delete an object by a custom matcher (e.g., id match)
    public void delete(Matcher<T> matcher) throws IOException {
        List<T> objects = readAll();
        objects.removeIf(matcher::matches);
        objectMapper.writeValue(dbFile, objects);
    }

    // Functional interface for matching objects
    public interface Matcher<T> {
        boolean matches(T obj);
    }
}