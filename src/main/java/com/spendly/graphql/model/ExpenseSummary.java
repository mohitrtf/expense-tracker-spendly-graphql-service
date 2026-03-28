package com.spendly.graphql.model;

import java.math.BigDecimal;
import java.util.Map;

public class ExpenseSummary {
    private BigDecimal totalAmount;
    private Integer totalCount;
    private BigDecimal thisMonthAmount;
    private BigDecimal lastMonthAmount;
    private java.util.Map<String, BigDecimal> byCategory;
    private java.util.Map<String, BigDecimal> byMonth;

    // getters/setters


    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getThisMonthAmount() {
        return thisMonthAmount;
    }

    public void setThisMonthAmount(BigDecimal thisMonthAmount) {
        this.thisMonthAmount = thisMonthAmount;
    }

    public BigDecimal getLastMonthAmount() {
        return lastMonthAmount;
    }

    public void setLastMonthAmount(BigDecimal lastMonthAmount) {
        this.lastMonthAmount = lastMonthAmount;
    }

    public Map<String, BigDecimal> getByCategory() {
        return byCategory;
    }

    public void setByCategory(Map<String, BigDecimal> byCategory) {
        this.byCategory = byCategory;
    }

    public Map<String, BigDecimal> getByMonth() {
        return byMonth;
    }

    public void setByMonth(Map<String, BigDecimal> byMonth) {
        this.byMonth = byMonth;
    }
}
