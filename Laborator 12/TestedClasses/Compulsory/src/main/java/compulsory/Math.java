package compulsory;

import org.testng.annotations.Test;

public class Math {

    private int number = 6;

    public Math(){

    }

    public int add(int number1, int number2){
        return number1 + number2;
    }

    public int multiply(int number1, int number2){
        return number1 * number2;
    }

    @Test
    public static void helloworld(){
        System.out.println("hello");
    }

    @Test
    public static void printNumberString(int number1, String name1){
        System.out.println(number1 + " and " + name1);
    }
}
