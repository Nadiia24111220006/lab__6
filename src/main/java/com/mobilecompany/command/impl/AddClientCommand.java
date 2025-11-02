package com.mobilecompany.command.impl;

import com.mobilecompany.command.Command;
import com.mobilecompany.model.Client;
import com.mobilecompany.model.Tariff;
import com.mobilecompany.repository.ClientRepository;
import com.mobilecompany.repository.TariffRepository;
import java.util.List;
import java.util.Scanner;

public class AddClientCommand implements Command {
    private final ClientRepository clientRepository;
    private final TariffRepository tariffRepository;
    private final Scanner scanner;

    public AddClientCommand(ClientRepository clientRepository, TariffRepository tariffRepository, Scanner scanner) {
        this.clientRepository = clientRepository;
        this.tariffRepository = tariffRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("\n=== ДОДАВАННЯ НОВОГО КЛІЄНТА ===");

        try {
            System.out.print("Ім'я клієнта: ");
            String name = scanner.nextLine();

            System.out.print("Номер телефону: ");
            String phone = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            List<Tariff> tariffs = tariffRepository.findAll();
            if (tariffs.isEmpty()) {
                System.out.println("Немає доступних тарифів. Спочатку додайте тарифи.");
                return;
            }

            System.out.println("Доступні тарифи:");
            for (int i = 0; i < tariffs.size(); i++) {
                Tariff tariff = tariffs.get(i);
                System.out.printf("%d. %s - %.2f грн/міс\n",
                        i + 1, tariff.getName(), tariff.getMonthlyPrice());
            }

            System.out.print("Оберіть номер тарифу (0 - без тарифу): ");
            int choice = Integer.parseInt(scanner.nextLine());

            Tariff selectedTariff = null;
            if (choice > 0 && choice <= tariffs.size()) {
                selectedTariff = tariffs.get(choice - 1);
            }

            Client client = new Client(name, phone, email, selectedTariff);
            clientRepository.save(client);
            System.out.println("Клієнта успішно додано: " + name);

        } catch (Exception e) {
            System.out.println("Помилка при додаванні клієнта: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Додати нового клієнта";
    }
}