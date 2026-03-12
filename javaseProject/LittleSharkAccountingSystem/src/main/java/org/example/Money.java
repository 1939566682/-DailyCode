package org.example;

/**
 * @author Yang QingBo
 * @date 2026-03-11 17:19
 */
public class Money {
    private int income;
    private int expenditure;
    private String incomeDescription;
    private String expenditureDescription;

    public Money() {
    }

    public Money(int income, int expenditure, String incomeDescription, String expenditureDescription) {
        this.income = income;
        this.expenditure = expenditure;
        this.incomeDescription = incomeDescription;
        this.expenditureDescription = expenditureDescription;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        if (income < 0) {
            throw new IllegalArgumentException("收入金额不能为负数！");
        }
        this.income = income;
    }

    public int getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(int expenditure) {
        if (expenditure < 0) {
            throw new IllegalArgumentException("支出金额不能为负数！");
        }
        this.expenditure = expenditure;
    }

    public String getIncomeDescription() {
        return incomeDescription;
    }

    public void setIncomeDescription(String incomeDescription) {
        this.incomeDescription = (incomeDescription == null || incomeDescription.trim().isEmpty()) ? "无说明" : incomeDescription;
    }

    public String getExpenditureDescription() {
        return expenditureDescription;
    }

    public void setExpenditureDescription(String expenditureDescription) {
        this.expenditureDescription = (expenditureDescription == null || expenditureDescription.trim().isEmpty()) ? "无说明" : expenditureDescription;
    }

    @Override
    public String toString() {
        if (income > 0) {
            return "收入：+" + income + " 元 | 说明：" + incomeDescription ;
        } else if (expenditure > 0) {
            return "支出：-" + expenditure + " 元 | 说明：" + expenditureDescription;
        }
        return "无有效收支记录";
    }
}
