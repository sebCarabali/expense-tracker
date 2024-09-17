package com.zebsoft;

import java.util.HashMap;
import java.util.Map;

import com.zebsoft.db.DBManager;
import com.zebsoft.model.Expense;
import com.zebsoft.parser.ArgParser;

public class Main {

  public static void main(String[] args) {
    if (args.length < 1) {
      throw new IllegalArgumentException("usage: expense-tracker <command> [options]");
    }
    String command = args[0];
    Map<String, String> options = ArgParser.parse(args);
    DBManager dbManager = new DBManager<>("expense-tracker.json", Expense.class);
  }

}