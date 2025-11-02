package com.mobilecompany.model;

import java.io.Serializable;

public class PremiumTariff extends Tariff implements Serializable {
    private boolean internationalCalls;
    private boolean entertainmentPack;
    private boolean prioritySupport;

    public PremiumTariff(String id, String name, double monthlyPrice, int includedMinutes,
                         int includedSms, double includedInternetGB,
                         boolean internationalCalls, boolean entertainmentPack, boolean prioritySupport) {
        super(id, name, monthlyPrice, includedMinutes, includedSms, includedInternetGB);
        this.internationalCalls = internationalCalls;
        this.entertainmentPack = entertainmentPack;
        this.prioritySupport = prioritySupport;
    }

    public boolean hasInternationalCalls() { return internationalCalls; }
    public boolean hasEntertainmentPack() { return entertainmentPack; }
    public boolean hasPrioritySupport() { return prioritySupport; }

    @Override
    public String toString() {
        return super.toString() + " [Преміум]";
    }
}
