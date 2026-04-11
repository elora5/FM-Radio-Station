package com.oop.fmradiostation.Elora.finance_officer;

import java.time.LocalDate;

public class ExportFinancialRecord {
    private final String date;
    private final String recordId;
    private final String category;
    private final String source;
    private final double amount;
    private final String recordType;
    private final String status;
    private final String remarks;

    public ExportFinancialRecord(String date,
                                 String recordId,
                                 String category,
                                 String source,
                                 double amount,
                                 String recordType,
                                 String status,
                                 String remarks) {
        this.date = date;
        this.recordId = recordId;
        this.category = category;
        this.source = source;
        this.amount = amount;
        this.recordType = recordType;
        this.status = status;
        this.remarks = remarks;
    }

    public String getDate() {
        return date;
    }

    public LocalDate getRecordDate() {
        return LocalDate.parse(date);
    }

    public String getRecordId() {
        return recordId;
    }

    public String getCategory() {
        return category;
    }

    public String getSource() {
        return source;
    }

    public double getAmount() {
        return amount;
    }

    public String getRecordType() {
        return recordType;
    }

    public String getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }
}

