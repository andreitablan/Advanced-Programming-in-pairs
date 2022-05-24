package main;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class Main {
        public static void main (String args[]) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            ClassHandler classHandler = new ClassHandler();
            classHandler.run();

            File directory=new File("D:\\git\\FII_PA_LeaganDan_TablanAndrei\\Laborator 12\\Compulsory\\target\\classes\\compulsory");
            DirectoryHandler directoryHandler = new DirectoryHandler(directory);
            directoryHandler.loadClass(directory);

        }
}
