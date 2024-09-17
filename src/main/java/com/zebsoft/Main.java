package com.zebsoft;

import java.io.IOException;
import java.util.Map;

import com.zebsoft.command.AddExpenseCommand;
import com.zebsoft.command.CommandRegistry;
import com.zebsoft.db.DBManager;
import com.zebsoft.parser.ArgParser;

public class Main {

  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      throw new IllegalArgumentException("usage: expense-tracker <command> [options]");
    }
    DBManager dbManager = new DBManager("expense-tracker.json");
    CommandRegistry commandRegistry = new CommandRegistry();
    commandRegistry.registerCommand("add", new AddExpenseCommand());

    String command = args[0];
    Map<String, String> options = ArgParser.parse(args);
    
    String response = commandRegistry.executeCommand(command, options, dbManager);
    System.out.println(response);
  }

}