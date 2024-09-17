package com.zebsoft.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebsoft.model.Expense;

public class DBManager {
    private final File dbFile;
    private final ObjectMapper objectMapper;

    public DBManager(String filename) {
        this.dbFile = new File(filename);
        this.objectMapper = new ObjectMapper();
        // Initialize the file if it doesn't exist
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
                objectMapper.writeValue(dbFile, new ArrayList<Expense>());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Create or Update an object
    public void save(Expense obj) throws IOException {
        List<Expense> objects = readAll();
        objects.add(obj);
        objectMapper.writeValue(dbFile, objects);
    }

    // Read all objects from file
    public List<Expense> readAll() throws IOException {
        if (!dbFile.exists() || dbFile.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(dbFile, new TypeReference<List<Expense>>() {
        });
    }

    // Read an object by a custom matcher (e.g., id match)
    public Optional<Expense> findBy(Matcher matcher) throws IOException {
        return readAll().stream()
                .filter(matcher::matches)
                .findFirst();
    }

    // Read all object that matches a custom matcher
    public List<Expense> findAllBy(Matcher matcher) throws IOException {
        return readAll().stream().filter(matcher::matches).toList();
    }

    // Delete an object by a custom matcher (e.g., id match)
    public boolean delete(Matcher matcher) throws IOException {
        List<Expense> objects = readAll();
        var removed = objects.removeIf(matcher::matches);
        objectMapper.writeValue(dbFile, objects);
        return removed;        
    }

    // Functional interface for matching objects
    public interface Matcher {
        boolean matches(Expense obj);
    }
}