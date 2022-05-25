package main;


import org.testng.annotations.Test;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


public class ClassHandler {

    public void run() {
        try {
            File file = new File("D:\\git\\FII_PA_LeaganDan_TablanAndrei\\Laborator 12\\TestedClasses\\Compulsory\\target\\classes");
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader classLoader = new URLClassLoader(urls);

            Class aClass = classLoader.loadClass("compulsory.Math");
            System.out.println("The class package is: " + aClass.getPackage());

            System.out.println();
            for (Method method : aClass.getMethods()) {
                System.out.println("Method name is " + method.getName() + "\n");
            }

            for (Method method : aClass.getMethods()) {
                if (Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0 && method.isAnnotationPresent(Test.class)) {
                    Method methodToCall = aClass.getMethod(method.getName(), null);
                    Object object = methodToCall.invoke(null);
                }
            }
            System.out.println("\n");
        } catch (MalformedURLException | ClassNotFoundException ignored) {
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

