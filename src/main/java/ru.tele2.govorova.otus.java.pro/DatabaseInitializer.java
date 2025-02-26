package ru.tele2.govorova.otus.java.pro;

import jakarta.transaction.Transactional;
import ru.tele2.govorova.otus.java.pro.entity.Address;
import ru.tele2.govorova.otus.java.pro.entity.Client;
import ru.tele2.govorova.otus.java.pro.entity.Phone;

public class DatabaseInitializer {
    @Transactional
    public static Client createClient() {
        Address address = new Address();
        address.setStreet("ул. Ленина, дом 1");

        Client client = new Client();
        client.setName("Петр Петров");
        client.setAddress(address);

        Phone phone1 = new Phone();
        phone1.setNumber("+79801232822");
        phone1.setClient(client);

        Phone phone2 = new Phone();
        phone2.setNumber("+76428887492");
        phone2.setClient(client);

        client.getPhones().add(phone1);
        client.getPhones().add(phone2);

        return client;
    }
}
