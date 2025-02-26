package ru.tele2.govorova.otus.java.pro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.tele2.govorova.otus.java.pro.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        final Logger LOGGER = LoggerFactory.getLogger(Main.class);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        // Создаем и добавляем нового клиента
        LOGGER.info("Клиент создается и добавляется в персист...");
        Client client = DatabaseInitializer.createClient();
        entityManager.persist(client);
        LOGGER.info("Клиент успешно добавлен в персист!");
        entityManager.getTransaction().commit();

        // Найти всех клиентов
        List<Client> clientList = entityManager
                .createQuery("SELECT c FROM Client c", Client.class)
                .getResultList();
        clientList.forEach(allClients -> LOGGER.info("Client: {}", allClients));

        // Найти клиента по имени
        client = entityManager
                .createQuery("SELECT c FROM Client c WHERE c.name = :name", Client.class)
                .setParameter("name", "Петр Петров")
                .getSingleResult();
        LOGGER.info("Клиент с именем 'Петр Петров': {}", client);

        // Найти id клиента по имени
        Long clientId = entityManager
                .createQuery("SELECT c.id FROM Client c WHERE c.name = :name", Long.class)
                .setParameter("name", "Петр Петров")
                .getSingleResult();
        LOGGER.info("ID клиента 'Петр Петров': {}", clientId);

        // Закрываем всё
        entityManager.close();
        emf.close();
    }
}