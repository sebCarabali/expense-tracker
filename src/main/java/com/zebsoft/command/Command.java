package com.zebsoft.command;

import java.io.IOException;
import java.util.Map;

import com.zebsoft.db.DBManager;

@FunctionalInterface
public interface Command {

    String execute(Map<String, String> optiions, DBManager dbManager) throws IOException;
}
