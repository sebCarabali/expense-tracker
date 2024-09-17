package com.zebsoft.command;

import java.io.IOException;
import java.util.Map;

import com.zebsoft.db.DBManager;

public class DeleteExpenseCommand implements Command{

    @Override
    public String execute(Map<String, String> optiions, DBManager dbManager) throws IOException {
        if (optiions.containsKey("id")) {
            int id = Integer.valueOf(optiions.get("id"));
            boolean removed = dbManager.delete(e -> e.getId() == id);
            return removed ? "expense deleted" : "expense with the given id not found";
        }
        throw new IllegalArgumentException("--id option is required");
    }

}
