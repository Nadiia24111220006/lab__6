package com.mobilecompany.command;

import com.mobilecompany.command.impl.*;
import com.mobilecompany.repository.TariffRepository;
import com.mobilecompany.repository.ClientRepository;
import com.mobilecompany.util.FileManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<String, Command> commands = new HashMap<>();
    private final Scanner scanner;
    private boolean isRunning;

    private final TariffRepository tariffRepository;
    private final ClientRepository clientRepository;
    private final FileManager fileManager;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.isRunning = false;

        this.tariffRepository = new TariffRepository();
        this.clientRepository = new ClientRepository();
        this.fileManager = new FileManager();

        initializeCommands();
    }

    private void initializeCommands() {
        commands.put("add-tariff", new AddTariffCommand(tariffRepository, scanner));
        commands.put("remove-tariff", new RemoveTariffCommand(tariffRepository, scanner));
        commands.put("add-client", new AddClientCommand(clientRepository, tariffRepository, scanner));
        commands.put("remove-client", new RemoveClientCommand(clientRepository, scanner));
        commands.put("show-tariffs", new ShowTariffsCommand(tariffRepository));
        commands.put("sort-tariffs", new SortTariffsCommand(tariffRepository));
        commands.put("search-tariffs", new SearchTariffsCommand(tariffRepository, scanner));
        commands.put("total-clients", new TotalClientsCommand(clientRepository));
        commands.put("save", new SaveDataCommand(fileManager, tariffRepository, clientRepository, scanner));
        commands.put("load", new LoadDataCommand(fileManager, tariffRepository, clientRepository, scanner));
    }

    public void run() {
        isRunning = true;
        System.out.println("=== Система управління мобільною компанією ===");

        while (isRunning) {
            System.out.print("\nВведіть команду (help - довідка): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+", 2);
            String commandKey = parts[0].toLowerCase();
            String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

            if (commandKey.equals("help")) {
                showHelp();
                continue;
            }

            if (commandKey.equals("exit")) {
                exit();
                continue;
            }

            Command command = commands.get(commandKey);
            if (command != null) {
                command.execute(args);
            } else {
                System.out.println("Невідома команда: " + commandKey);
            }
        }
        scanner.close();
    }

    public void registerCommand(String commandKey, Command command) {
        commands.put(commandKey.toLowerCase(), command);
    }

    private void showHelp() {
        System.out.println("\n=== Довідка по командам ===");
        System.out.println("help - показати довідку");
        System.out.println("exit - вийти з програми");

        commands.forEach((key, command) -> {
            System.out.printf("%-15s - %s%n", key, command.getDescription());
        });
    }

    private void exit() {
        System.out.println("Завершення роботи...");
        isRunning = false;
    }
}