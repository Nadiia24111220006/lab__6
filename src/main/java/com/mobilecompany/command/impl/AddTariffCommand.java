package com.mobilecompany.command.impl;

import com.mobilecompany.command.Command;
import com.mobilecompany.model.*;
import com.mobilecompany.repository.TariffRepository;
import java.util.Scanner;

public class AddTariffCommand implements Command {
    private final TariffRepository tariffRepository;
    private final Scanner scanner;

    public AddTariffCommand(TariffRepository tariffRepository, Scanner scanner) {
        this.tariffRepository = tariffRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("\n=== ДОДАВАННЯ НОВОГО ТАРИФУ ===");

        try {
            System.out.print("Назва тарифу: ");
            String name = scanner.nextLine();

            System.out.print("Місячна плата (грн): ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Хвилини (кількість): ");
            int minutes = Integer.parseInt(scanner.nextLine());

            System.out.print("SMS (кількість): ");
            int sms = Integer.parseInt(scanner.nextLine());

            System.out.print("Інтернет (ГБ): ");
            double internet = Double.parseDouble(scanner.nextLine());

            System.out.println("Тип тарифу:");
            System.out.println("1. Базовий");
            System.out.println("2. Преміум");
            System.out.println("3. Бізнес");
            System.out.print("Оберіть тип (1-3): ");
            int type = Integer.parseInt(scanner.nextLine());

            String id = "T" + System.currentTimeMillis();
            Tariff tariff;

            switch (type) {
                case 1:
                    System.out.print("Роумінг включено (так/ні): ");
                    boolean roaming = scanner.nextLine().equalsIgnoreCase("так");
                    System.out.print("Безкоштовні соцмережі (так/ні): ");
                    boolean social = scanner.nextLine().equalsIgnoreCase("так");
                    tariff = new BaseTariff(id, name, price, minutes, sms, internet, roaming, social);
                    break;

                case 2:
                    System.out.print("Міжнародні дзвінки (так/ні): ");
                    boolean international = scanner.nextLine().equalsIgnoreCase("так");
                    System.out.print("Розважальний пакет (так/ні): ");
                    boolean entertainment = scanner.nextLine().equalsIgnoreCase("так");
                    tariff = new PremiumTariff(id, name, price, minutes, sms, internet, international, entertainment, true);
                    break;

                case 3:
                    System.out.print("Корпоративні послуги (так/ні): ");
                    boolean corporate = scanner.nextLine().equalsIgnoreCase("так");
                    System.out.print("Конференц-дзвінки (так/ні): ");
                    boolean conference = scanner.nextLine().equalsIgnoreCase("так");
                    tariff = new BusinessTariff(id, name, price, minutes, sms, internet, corporate, conference, true);
                    break;

                default:
                    System.out.println("Невірний тип тарифу!");
                    return;
            }

            tariffRepository.save(tariff);
            System.out.println("Тариф успішно додано: " + name);

        } catch (Exception e) {
            System.out.println("Помилка при додаванні тарифу: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Додати новий тариф";
    }
}