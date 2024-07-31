package com.springmvc.springmvchibernatesample.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "transaction_details", schema = "monthly-budget")
public class TransactionDetail {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

    @Column(name = "projected_amount")
    private Double projectedAmount;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "difference")
    private Double difference;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getProjectedAmount() {
        return projectedAmount;
    }

    public void setProjectedAmount(Double projectedAmount) {
        this.projectedAmount = projectedAmount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "id=" + id +
                ", month=" + month +
                ", year=" + year +
                ", date=" + transactionDate +
                ", projectedAmount=" + projectedAmount +
                ", amount=" + amount +
                ", difference=" + difference +
                ", category=" + category +
                ", transaction=" + transaction +
                '}';
    }
}