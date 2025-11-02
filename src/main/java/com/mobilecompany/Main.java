package com.mobilecompany;

import com.mobilecompany.command.Menu;
import com.mobilecompany.command.impl.*;
import com.mobilecompany.repository.TariffRepository;
import com.mobilecompany.repository.ClientRepository;
import com.mobilecompany.util.FileManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TariffRepository tariffRepository = new TariffRepository();
        ClientRepository clientRepository = new ClientRepository();
        FileManager fileManager = new FileManager();

        Menu menu = new Menu();

        menu.registerCommand("add-tariff", new AddTariffCommand(tariffRepository, scanner));
        menu.registerCommand("remove-tariff", new RemoveTariffCommand(tariffRepository, scanner));
        menu.registerCommand("add-client", new AddClientCommand(clientRepository, tariffRepository, scanner));
        menu.registerCommand("remove-client", new RemoveClientCommand(clientRepository, scanner));
        menu.registerCommand("show-tariffs", new ShowTariffsCommand(tariffRepository));
        menu.registerCommand("sort-tariffs", new SortTariffsCommand(tariffRepository));
        menu.registerCommand("search-tariffs", new SearchTariffsCommand(tariffRepository, scanner));
        menu.registerCommand("total-clients", new TotalClientsCommand(clientRepository));
        menu.registerCommand("save", new SaveDataCommand(fileManager, tariffRepository, clientRepository, scanner));
        menu.registerCommand("load", new LoadDataCommand(fileManager, tariffRepository, clientRepository, scanner));

        menu.run();
        scanner.close();
    }
}