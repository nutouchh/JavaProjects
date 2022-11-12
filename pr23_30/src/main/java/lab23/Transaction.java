package lab23;
import java.io.Serializable;

public class Transaction implements Serializable {
    double id;
    String name;
    double start;
    double income;
    double expenses;
    double percents;
    double cashback;
    double result;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getPercents() {
        return percents;
    }

    public void setPercents(double percents) {
        this.percents = percents;
    }

    public double getCashback() {
        return cashback;
    }

    public void setCashback(double cashback) {
        this.cashback = cashback;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", income=" + income +
                ", expenses=" + expenses +
                ", percents=" + percents +
                ", cashback=" + cashback +
                ", result=" + result +
                '}';
    }

}