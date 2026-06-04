package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("Привіт! Простий Java + Gradle додаток працює!");

        // Додамо трохи логіки: виведемо поточний час
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Поточна дата та час: " + now.format(formatter));
        System.out.println("=========================================");
    }
}
