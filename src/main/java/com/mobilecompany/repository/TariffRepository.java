package com.mobilecompany.repository;

import com.mobilecompany.model.Tariff;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TariffRepository {
    private final Map<String, Tariff> tariffs = new HashMap<>();

    public void save(Tariff tariff) {
        tariffs.put(tariff.getId(), tariff);
    }

    public Tariff findById(String id) {
        return tariffs.get(id);
    }

    public List<Tariff> findAll() {
        return new ArrayList<>(tariffs.values());
    }

    public boolean delete(String id) {
        return tariffs.remove(id) != null;
    }

    public boolean exists(String id) {
        return tariffs.containsKey(id);
    }

    public int count() {
        return tariffs.size();
    }
}