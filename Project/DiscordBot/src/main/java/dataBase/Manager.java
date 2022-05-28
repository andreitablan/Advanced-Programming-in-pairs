package dataBase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager {
    private static Manager managerInstance = null;
    private final EntityManagerFactory factory;
    private final EntityManager manager;

    private Manager() {
        this.factory = Persistence.createEntityManagerFactory("DiscordBotDatabase");
        this.manager = factory.createEntityManager();
    }

    public static Manager getInstance() {
        if (managerInstance == null) {
            managerInstance = new Manager();
            managerInstance.getManager().getTransaction().begin();
        }

        return managerInstance;
    }

    public EntityManager getManager() {
        return manager;
    }
}
