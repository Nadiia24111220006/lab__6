package com.mobilecompany.repository;

import com.mobilecompany.model.Client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRepository {
    private final Map<String, Client> clients = new HashMap<>();

    public void save(Client client) {
        clients.put(client.getId(), client);
    }

    public Client findById(String id) {
        return clients.get(id);
    }

    public List<Client> findAll() {
        return new ArrayList<>(clients.values());
    }

    public boolean delete(String id) {
        return clients.remove(id) != null;
    }

    public List<Client> findByTariff(String tariffId) {
        List<Client> result = new ArrayList<>();
        for (Client client : clients.values()) {
            if (client.getTariff() != null && client.getTariff().getId().equals(tariffId)) {
                result.add(client);
            }
        }
        return result;
    }

    public int count() {
        return clients.size();
    }

    public boolean exists(String id) {
        return clients.containsKey(id);
    }
}