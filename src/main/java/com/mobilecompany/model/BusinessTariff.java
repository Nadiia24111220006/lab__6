package com.mobilecompany.model;

import java.io.Serializable;

public class BusinessTariff extends Tariff implements Serializable {
    private boolean corporateServices;
    private boolean conferenceCalls;
    private boolean dedicatedManager;

    public BusinessTariff(String id, String name, double monthlyPrice, int includedMinutes,
                          int includedSms, double includedInternetGB,
                          boolean corporateServices, boolean conferenceCalls, boolean dedicatedManager) {
        super(id, name, monthlyPrice, includedMinutes, includedSms, includedInternetGB);
        this.corporateServices = corporateServices;
        this.conferenceCalls = conferenceCalls;
        this.dedicatedManager = dedicatedManager;
    }

    public boolean hasCorporateServices() { return corporateServices; }
    public boolean hasConferenceCalls() { return conferenceCalls; }
    public boolean hasDedicatedManager() { return dedicatedManager; }

    @Override
    public String toString() {
        return super.toString() + " [Бізнес]";
    }
}