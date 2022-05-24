package main;

import com.beust.ah.A;
import com.google.common.io.Files;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DirectoryHandler {
    private File directory;
    private String[] packageName = new String[100];
    private int packageCounter;

    public DirectoryHandler(File directory) {
        this.directory = directory;
        this.packageName[0] = "compulsory";
        this.packageCounter = 0;
    }

    public void loadClass(File directory) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.packageCounter++;
        this.directory = directory;
        File[] files = directory.listFiles();
        for (File file1 : files) {
            if (file1.isFile()) {
                if (Files.getFileExtension(file1.getName()).equals("class") || Files.getFileExtension(file1.getName()).equals("jar")) {
                    System.out.println("\n" + file1.getName());

                    String pathname = file1.getParent();
                    for (int index1 = 0; index1 < packageCounter; index1++) {
                        pathname = pathname + "\\..\\";
                    }
                    File file = new File(pathname);
                    URL url = file.toURI().toURL();
                    URL[] urls = new URL[]{url};
                    ClassLoader classLoader = new URLClassLoader(urls);

                    String path = packageName[0] + ".";
                    for (int index = 1; index < packageCounter; index++) {
                        path = path + packageName[index];
                        path = path + ".";
                    }
                    System.out.println("Package path: " + path);
                    Class aClass = classLoader.loadClass(path + file1.getName().substring(0, file1.getName().indexOf(".")));
                    this.generateInformation(aClass);
                }
            } else {
                this.packageName[packageCounter] = file1.getName();
                this.loadClass(file1);
                this.packageCounter--;
            }
        }
    }

    public void generateInformation(Class aClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field: " + field);
        }

        Constructor[] constructors = aClass.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("Constructor: " + constructor);
        }

        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method);
            if (method.isAnnotationPresent(Test.class)) {
                Class[] parameters = method.getParameterTypes();
                invokeMethod(aClass, method, parameters);
            }
        }
    }

    private void invokeMethod(Class aClass, Method method, Class[] parameters) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Random random = new Random();
        List<Object> values = new ArrayList<>();
        for (Class parameter : parameters) {
            if (parameter.getName().equals("int")) {
                values.add(random.nextInt(1000));
            }
            if (parameter.getName().equals("java.lang.String")) {
                values.add(generateString());
            }
        }

        method.invoke(aClass.getConstructor().newInstance(), values.toArray());
    }

    public String generateString() {
        Random random1 = new Random();
        int length = random1.nextInt(1,10);
        int leftLimit = 97;
        int rightLimit = 122;
        String randomString = random1.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return randomString;
    }
}


