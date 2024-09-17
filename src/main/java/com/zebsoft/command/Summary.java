package com.zebsoft.command;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import com.zebsoft.db.DBManager;
import com.zebsoft.model.Expense;

public class Summary implements Command {

    @Override
    public String execute(Map<String, String> optiions, DBManager dbManager) throws IOException {

        if (optiions.containsKey("month")) {
            int month = Integer.valueOf(optiions.get("month"));
            if (month >= 1 && month <= 12) {
                return "$" + dbManager.findAllBy((e) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(e.getDate());
                    int dateMonth = calendar.get(Calendar.MONTH) + 1;
                    return month == dateMonth;
                }).stream().mapToDouble(Expense::getAmount).sum();
            }
            throw new IllegalArgumentException("The given month is not valid: " + month);
        } else {
            return "$" + dbManager.readAll().stream().mapToDouble(Expense::getAmount).sum();
        }
    }

}
