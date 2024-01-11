package com.project;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        String basePath = System.getProperty("user.dir") + "/data/";

        // Crear la carpeta 'data' si no existe
        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Error en la creación de la carpeta 'data'");
            }
        }

        Manager.createSessionFactory();

        Ciutat ciutat1 = Manager.addCiutat("Barcelona", "Spain", 12020);
        Ciutat ciutat2 = Manager.addCiutat("Paris", "France", 75001);
        Ciutat ciutat3 = Manager.addCiutat("New York", "USA", 10001);

        Ciutada ciutada1 = Manager.addCiutada("Alice", "Smith", 25, ciutat1.getCiutatId());
        Ciutada ciutada2 = Manager.addCiutada("Bob", "Johnson", 30, ciutat1.getCiutatId());
        Ciutada ciutada3 = Manager.addCiutada("Charlie", "Brown", 28, ciutat2.getCiutatId());
        Ciutada ciutada4 = Manager.addCiutada("David", "Williams", 35, ciutat2.getCiutatId());
        Ciutada ciutada5 = Manager.addCiutada("Emma", "Davis", 22, ciutat3.getCiutatId());
        Ciutada ciutada6 = Manager.addCiutada("Frank", "Miller", 40, ciutat3.getCiutatId());

        Manager.deleteCiutada(ciutada1.getId());
        Manager.updateCiutada(ciutada2.getId(), "UpdatedName", "UpdatedLastName", 32);

        System.out.println(Manager.collectionToString(Ciutat.class, Manager.listCollection(Ciutat.class)));
        System.out.println("--------------");
        System.out.println(Manager.collectionToString(Ciutada.class, Manager.listCollection(Ciutada.class)));

        Manager.close();
    }
}
