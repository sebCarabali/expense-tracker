package com.zebsoft.model;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {

    private int id;
    private String description;
    private Date date;
    private Double amount;

    public Expense() {
    }

    public Expense(int id, String description, Date date, Double amount) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    private Expense(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.date = builder.date;
        this.amount = builder.amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public static class Builder {
        private int id;
        private String description;
        private Date date;
        private Double amount;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Expense build() {
            return new Expense(this);
        }
    }
}
