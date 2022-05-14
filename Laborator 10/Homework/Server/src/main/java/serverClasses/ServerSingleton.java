package serverClasses;

import java.io.Serializable;
public class ServerSingleton implements Serializable {

    private static final long ServerSingleton = 0;

    private ServerSingleton(){}

    private static class SingletonHelper{
        private static final ServerSingleton instance = new ServerSingleton();
    }

    public static ServerSingleton getInstance(){
        return SingletonHelper.instance;
    }

}