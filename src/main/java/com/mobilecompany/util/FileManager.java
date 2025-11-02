package com.mobilecompany.util;

import com.mobilecompany.model.Tariff;
import com.mobilecompany.model.Client;
import com.mobilecompany.repository.TariffRepository;
import com.mobilecompany.repository.ClientRepository;

import java.io.*;
import java.util.List;

public class FileManager {

    public void saveData(TariffRepository tariffRepo, ClientRepository clientRepo, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Зберігаємо тарифи
            oos.writeObject(tariffRepo.findAll());
            // Зберігаємо клієнтів
            oos.writeObject(clientRepo.findAll());
            System.out.println("Дані успішно збережено у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка збереження даних: " + e.getMessage());
        }
    }

    public void loadData(TariffRepository tariffRepo, ClientRepository clientRepo, String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // Завантажуємо тарифи
            @SuppressWarnings("unchecked")
            List<Tariff> tariffs = (List<Tariff>) ois.readObject();
            // Завантажуємо клієнтів
            @SuppressWarnings("unchecked")
            List<Client> clients = (List<Client>) ois.readObject();

            int addedTariffs = 0;
            int addedClients = 0;

            for (Tariff tariff : tariffs) {
                // Перевіряємо, чи такий тариф вже існує
                if (!tariffRepo.exists(tariff.getId())) {
                    tariffRepo.save(tariff);
                    addedTariffs++;
                }
            }
            for (Client client : clients) {
                // Перевіряємо, чи такий клієнт вже існує
                if (!clientRepo.exists(client.getId())) {
                    clientRepo.save(client);
                    addedClients++;
                }
            }

            System.out.println("Дані успішно завантажено з файлу: " + filename);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка завантаження даних: " + e.getMessage());
        }
    }

    public void exportToCsv(TariffRepository tariffRepo, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Назва;Ціна;Клієнти;Хвилини;SMS;Інтернет");
            for (Tariff tariff : tariffRepo.findAll()) {
                writer.printf("%s;%.2f;%d;%d;%d;%.2f\n",
                        tariff.getName(),
                        tariff.getMonthlyPrice(),
                        tariff.getClientCount(),
                        tariff.getIncludedMinutes(),
                        tariff.getIncludedSms(),
                        tariff.getIncludedInternetGB());
            }
            System.out.println("Дані експортовано у CSV файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка експорту даних: " + e.getMessage());
        }
    }
}