package com.mobilecompany.model;

import java.io.Serializable;

public abstract class Tariff implements Serializable {
    private String id;
    private String name;
    private double monthlyPrice;
    private int clientCount;
    private int includedMinutes;
    private int includedSms;
    private double includedInternetGB;

    public Tariff(String id, String name, double monthlyPrice, int includedMinutes,
                  int includedSms, double includedInternetGB) {
        this.id = id;
        this.name = name;
        this.monthlyPrice = monthlyPrice;
        this.includedMinutes = includedMinutes;
        this.includedSms = includedSms;
        this.includedInternetGB = includedInternetGB;
        this.clientCount = 0;
    }

    public void addClient() {
        this.clientCount++;
    }

    public void removeClient() {
        if (this.clientCount > 0) {
            this.clientCount--;
        }
    }

    // Гетери та сетери
    public String getId() { return id; }
    public String getName() { return name; }
    public double getMonthlyPrice() { return monthlyPrice; }
    public int getClientCount() { return clientCount; }
    public int getIncludedMinutes() { return includedMinutes; }
    public int getIncludedSms() { return includedSms; }
    public double getIncludedInternetGB() { return includedInternetGB; }


    public void setName(String name) { this.name = name; }
    public void setMonthlyPrice(double monthlyPrice) { this.monthlyPrice = monthlyPrice; }

    @Override
    public String toString() {
        return String.format("Тариф '%s' (%.2f грн/міс) - %d клієнтів",
                name, monthlyPrice, clientCount);
    }
}
