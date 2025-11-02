package com.mobilecompany.command.impl;

import com.mobilecompany.command.Command;
import com.mobilecompany.model.Tariff;
import com.mobilecompany.repository.TariffRepository;
import java.util.List;

public class ShowTariffsCommand implements Command {
    private final TariffRepository tariffRepository;

    public ShowTariffsCommand(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public void execute(String[] args) {
        List<Tariff> tariffs = tariffRepository.findAll();

        if (tariffs.isEmpty()) {
            System.out.println("Тарифи відсутні");
            return;
        }

        System.out.println("СПИСОК ТАРИФІВ:");
        for (int i = 0; i < tariffs.size(); i++) {
            Tariff tariff = tariffs.get(i);
            System.out.printf("%d. %s - %.2f грн/міс, %d клієнтів, %d хв, %d SMS, %.1f ГБ\n",
                    i + 1, tariff.getName(), tariff.getMonthlyPrice(),
                    tariff.getClientCount(), tariff.getIncludedMinutes(),
                    tariff.getIncludedSms(), tariff.getIncludedInternetGB());
        }

        int totalClients = tariffs.stream()
                .mapToInt(Tariff::getClientCount)
                .sum();
        System.out.printf("Загальна кількість клієнтів: %d\n", totalClients);
    }

    @Override
    public String getDescription() {
        return "Показати всі тарифи";
    }
}