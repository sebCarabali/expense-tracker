package com.zebsoft.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.zebsoft.db.DBManager;
import com.zebsoft.model.Expense;

public class ListExpenseCommand implements Command {

    @Override
    public String execute(Map<String, String> optiions, DBManager dbManager) throws IOException {
        printHeader();
        dbManager.readAll().forEach(this::printRecord);
        return "";
    }

    private void printHeader() {
        System.out.printf("%-4s %-12s %-12s %-7s%n", "ID", "Date", "Description", "Amount");;
    }

    private void printRecord(Expense record) {
        System.out.printf("%-4d %-12s %-12.12s $%-7s%n", record.getId(), formatDate(record.getDate()), record.getDescription(), record.getAmount());
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
