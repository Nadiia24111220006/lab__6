package com.mobilecompany.model;

import java.io.Serializable;

public class BaseTariff extends Tariff implements Serializable {
    private boolean roamingIncluded;
    private boolean socialNetworksFree;

    public BaseTariff(String id, String name, double monthlyPrice, int includedMinutes,
                      int includedSms, double includedInternetGB,
                      boolean roamingIncluded, boolean socialNetworksFree) {
        super(id, name, monthlyPrice, includedMinutes, includedSms, includedInternetGB);
        this.roamingIncluded = roamingIncluded;
        this.socialNetworksFree = socialNetworksFree;
    }

    // Гетери та сетери
    public boolean isRoamingIncluded() { return roamingIncluded; }
    public boolean isSocialNetworksFree() { return socialNetworksFree; }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Базовий: роумінг=%s, соцмережі=%s]",
                roamingIncluded, socialNetworksFree);
    }
}
