package com.zebsoft.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.zebsoft.db.DBManager;
import com.zebsoft.model.Expense;

public class AddExpenseCommand implements Command {

    @Override
    public String execute(Map<String, String> optiions, DBManager dbManager) throws IOException {
        String description = optiions.get("description");
        String amount = optiions.get("amount");
        Date localDate =new Date();
        int id = dbManager.readAll().stream().map(Expense::getId).max(Integer::compare).orElse(0) + 1;

        Expense expense = new Expense(id, description, localDate, Double.valueOf(amount));
        dbManager.save(expense);
        return "Expense saved.";
    }
}
