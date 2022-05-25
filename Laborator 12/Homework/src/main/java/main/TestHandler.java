package main;

import com.google.common.io.Files;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestHandler {

    private File directory;

    public TestHandler(File directory){
        this.directory=directory;
    }

    public void loadTest(File directory) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        File[] files=directory.listFiles();

        System.out.println("\n" + "Tests");
        for(File file1 : files){
            if(file1.isFile()) {
                if (Files.getFileExtension(file1.getName()).equals("class")) {
                    String pathname = file1.getParent();
                    pathname=pathname+"\\..\\";
                    File file = new File(pathname);
                    URL url = file.toURI().toURL();
                    URL[] urls = new URL[]{url};
                    ClassLoader classLoader = new URLClassLoader(urls);

                    Class aTestClass = classLoader.loadClass("compulsory." + file1.getName().substring(0, file1.getName().indexOf(".")));
                    Method[] methods=aTestClass.getDeclaredMethods();
                    for(Method method:methods){
                        System.out.println("\nName: " + method.getName());
                        System.out.println("Declaring Class: " + method.getDeclaringClass());
                        System.out.println("Return Type: " + method.getAnnotatedReturnType());
                    }
                }
            }
        }
    }
}
