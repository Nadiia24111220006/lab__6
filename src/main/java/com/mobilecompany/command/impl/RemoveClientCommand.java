package com.mobilecompany.command.impl;

import com.mobilecompany.command.Command;
import com.mobilecompany.model.Client;
import com.mobilecompany.repository.ClientRepository;
import java.util.List;
import java.util.Scanner;

public class RemoveClientCommand implements Command {
    private final ClientRepository clientRepository;
    private final Scanner scanner;

    public RemoveClientCommand(ClientRepository clientRepository, Scanner scanner) {
        this.clientRepository = clientRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("\n=== ВИДАЛЕННЯ КЛІЄНТА ===");

        try {
            List<Client> clients = clientRepository.findAll();
            if (clients.isEmpty()) {
                System.out.println("Клієнти відсутні");
                return;
            }

            System.out.println("СПИСОК КЛІЄНТІВ:");
            for (int i = 0; i < clients.size(); i++) {
                Client client = clients.get(i);
                System.out.printf("%d. %s - Телефон: %s\n",
                        i + 1, client.getName(), client.getPhoneNumber());
            }

            System.out.print("Введіть номер клієнта для видалення: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice > 0 && choice <= clients.size()) {
                Client clientToRemove = clients.get(choice - 1);
                boolean removed = clientRepository.delete(clientToRemove.getId());
                if (removed) {
                    System.out.println("Клієнта успішно видалено: " + clientToRemove.getName());
                }
            } else {
                System.out.println("Невірний номер клієнта!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть коректний номер!");
        } catch (Exception e) {
            System.out.println("Помилка при видаленні клієнта: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Видалити клієнта";
    }
}