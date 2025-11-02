package com.mobilecompany.command;

/**
 * Інтерфейс команди для реалізації патерну "Команда"
 */
public interface Command {

    /**
     * Виконує основну логіку команди
     * @param params параметри команди (можуть бути порожніми)
     */
    void execute(String[] params);

    /**
     * Повертає опис команди для довідки
     * @return рядок з описом команди
     */
    String getDescription();
}
