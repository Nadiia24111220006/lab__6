package com.mobilecompany.command.impl;

import com.mobilecompany.command.Command;
import com.mobilecompany.util.FileManager;
import com.mobilecompany.repository.TariffRepository;
import com.mobilecompany.repository.ClientRepository;

import java.util.Scanner;

public class SaveDataCommand implements Command {
    private final FileManager fileManager;
    private final TariffRepository tariffRepository;
    private final ClientRepository clientRepository;
    private final Scanner scanner;

    public SaveDataCommand(FileManager fileManager, TariffRepository tariffRepository,
                           ClientRepository clientRepository, Scanner scanner) {
        this.fileManager = fileManager;
        this.tariffRepository = tariffRepository;
        this.clientRepository = clientRepository;
        this.scanner = scanner;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("\n=== Збереження даних ===");

        try {
            String filename;
            if (args.length > 0) {
                filename = args[0];
            } else {
                System.out.print("Введіть ім'я файлу: ");
                filename = scanner.nextLine();
            }

            fileManager.saveData(tariffRepository, clientRepository, filename);

        } catch (Exception e) {
            System.out.println("Помилка при збереженні даних: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Зберегти дані у файл";
    }
}