package com.mobilecompany.command.impl;

import com.mobilecompany.command.Command;
import com.mobilecompany.model.Tariff;
import com.mobilecompany.repository.TariffRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortTariffsCommand implements Command {
    private final TariffRepository tariffRepository;

    public SortTariffsCommand(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("\n=== СОРТУВАННЯ ТАРИФІВ ЗА ЦІНОЮ ===");

        List<Tariff> sortedTariffs = tariffRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Tariff::getMonthlyPrice))
                .collect(Collectors.toList());

        if (sortedTariffs.isEmpty()) {
            System.out.println("Тарифи відсутні");
            return;
        }

        System.out.println("ТАРИФИ ВІДСОРТОВАНІ ЗА ЦІНОЮ:");
        for (int i = 0; i < sortedTariffs.size(); i++) {
            Tariff tariff = sortedTariffs.get(i);
            System.out.printf("%d. %s - %.2f грн/міс\n",
                    i + 1, tariff.getName(), tariff.getMonthlyPrice());
        }
    }

    @Override
    public String getDescription() {
        return "Відсортувати тарифи за ціною";
    }
}