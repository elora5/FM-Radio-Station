package com.oop.fmradiostation.Elora.finance_officer;

public class FinancialRecord {
    private final String date;
    private final String description;
    private final String incomeOrExpense;
    private final double amount;

    public FinancialRecord(String date, String description, String incomeOrExpense, double amount) {
        this.date = date;
        this.description = description;
        this.incomeOrExpense = incomeOrExpense;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getIncomeOrExpense() {
        return incomeOrExpense;
    }

    public double getAmount() {
        return amount;
    }
}

