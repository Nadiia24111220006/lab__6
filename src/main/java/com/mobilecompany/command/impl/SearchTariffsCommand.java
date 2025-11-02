package com.mobilecompany.command.impl;

import com.mobilecompany.command.Command;
import com.mobilecompany.model.Tariff;
import com.mobilecompany.repository.TariffRepository;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchTariffsCommand implements Command {
    private final TariffRepository tariffRepository;
    private final Scanner scanner;

    public SearchTariffsCommand(TariffRepository tariffRepository, Scanner scanner) {
        this.tariffRepository = tariffRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("\n=== ПОШУК ТАРИФІВ ЗА ЦІНОЮ ===");

        try {
            System.out.print("Мінімальна ціна (грн): ");
            double minPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Максимальна ціна (грн): ");
            double maxPrice = Double.parseDouble(scanner.nextLine());

            List<Tariff> foundTariffs = tariffRepository.findAll().stream()
                    .filter(tariff -> tariff.getMonthlyPrice() >= minPrice &&
                            tariff.getMonthlyPrice() <= maxPrice)
                    .collect(Collectors.toList());

            if (foundTariffs.isEmpty()) {
                System.out.println("Тарифи не знайдено у вказаному діапазоні цін");
            } else {
                System.out.println("ЗНАЙДЕНІ ТАРИФИ:");
                for (int i = 0; i < foundTariffs.size(); i++) {
                    Tariff tariff = foundTariffs.get(i);
                    System.out.printf("%d. %s - %.2f грн/міс, %d клієнтів\n",
                            i + 1, tariff.getName(), tariff.getMonthlyPrice(), tariff.getClientCount());
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть коректні числа для цін!");
        } catch (Exception e) {
            System.out.println("Помилка при пошуку: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Знайти тариф за діапазоном цін";
    }
}