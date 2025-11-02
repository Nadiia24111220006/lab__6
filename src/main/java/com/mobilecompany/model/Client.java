package com.mobilecompany.model;

import java.time.LocalDate;
import java.util.UUID;

import java.io.Serializable;

public class Client implements Serializable {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private Tariff tariff;
    private LocalDate registrationDate;

    public Client(String name, String phoneNumber, String email, Tariff tariff) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.tariff = tariff;
        this.registrationDate = LocalDate.now();

        // Додаємо клієнта до тарифу
        if (tariff != null) {
            tariff.addClient();
        }
    }

    public void changeTariff(Tariff newTariff) {
        if (this.tariff != null) {
            this.tariff.removeClient();
        }
        this.tariff = newTariff;
        if (newTariff != null) {
            newTariff.addClient();
        }
    }

    // Гетери
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public Tariff getTariff() { return tariff; }
    public LocalDate getRegistrationDate() { return registrationDate; }

    @Override
    public String toString() {
        return String.format("Клієнт: %s, Телефон: %s, Тариф: %s",
                name, phoneNumber,
                tariff != null ? tariff.getName() : "Не обрано");
    }
}
