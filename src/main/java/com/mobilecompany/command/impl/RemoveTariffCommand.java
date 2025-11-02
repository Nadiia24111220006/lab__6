package com.mobilecompany.command.impl;

import com.mobilecompany.command.Command;
import com.mobilecompany.model.Tariff;
import com.mobilecompany.repository.TariffRepository;
import java.util.List;
import java.util.Scanner;

public class RemoveTariffCommand implements Command {
    private final TariffRepository tariffRepository;
    private final Scanner scanner;

    public RemoveTariffCommand(TariffRepository tariffRepository, Scanner scanner) {
        this.tariffRepository = tariffRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("\n=== ВИДАЛЕННЯ ТАРИФУ ===");

        try {
            List<Tariff> tariffs = tariffRepository.findAll();
            if (tariffs.isEmpty()) {
                System.out.println("Тарифи відсутні");
                return;
            }

            System.out.println("СПИСОК ТАРИФІВ:");
            for (int i = 0; i < tariffs.size(); i++) {
                Tariff tariff = tariffs.get(i);
                System.out.printf("%d. %s - %.2f грн/міс, %d клієнтів\n",
                        i + 1, tariff.getName(), tariff.getMonthlyPrice(), tariff.getClientCount());
            }

            System.out.print("Введіть номер тарифу для видалення: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice > 0 && choice <= tariffs.size()) {
                Tariff tariffToRemove = tariffs.get(choice - 1);
                boolean removed = tariffRepository.delete(tariffToRemove.getId());
                if (removed) {
                    System.out.println("Тариф успішно видалено: " + tariffToRemove.getName());
                }
            } else {
                System.out.println("Невірний номер тарифу!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть коректний номер!");
        } catch (Exception e) {
            System.out.println("Помилка при видаленні тарифу: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Видалити тариф";
    }
}