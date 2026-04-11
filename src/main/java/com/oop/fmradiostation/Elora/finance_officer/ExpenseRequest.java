package com.oop.fmradiostation.Elora.finance_officer;

public class ExpenseRequest {
    private final String requestId;
    private final String department;
    private final String amount;
    private final String reason;
    private String status;

    public ExpenseRequest(String requestId, String department, String amount, String reason, String status) {
        this.requestId = requestId;
        this.department = department;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getDepartment() {
        return department;
    }

    public String getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

