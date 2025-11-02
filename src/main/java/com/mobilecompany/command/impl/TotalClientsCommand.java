package com.mobilecompany.command.impl;

import com.mobilecompany.command.Command;
import com.mobilecompany.repository.ClientRepository;

public class TotalClientsCommand implements Command {
    private final ClientRepository clientRepository;

    public TotalClientsCommand(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void execute(String[] args) {
        int totalClients = clientRepository.findAll().size();
        System.out.println("Загальна кількість клієнтів: " + totalClients);
    }

    @Override
    public String getDescription() {
        return "Показати загальну кількість клієнтів";
    }
}