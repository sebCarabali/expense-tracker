package com.zebsoft.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.zebsoft.db.DBManager;

public class CommandRegistry {

    private final Map<String, Command> commandHandlers = new HashMap<>();

    public void registerCommand(String commandName, Command command) {
        commandHandlers.put(commandName, command);
    }

    public String executeCommand(String commandName, Map<String, String> options, DBManager dbManager) throws IOException {
        Command command = commandHandlers.get(commandName);
        if (command == null) {
            throw new IllegalArgumentException("Command not found: " + commandName);
        }
        return command.execute(options, dbManager);
    }
}
