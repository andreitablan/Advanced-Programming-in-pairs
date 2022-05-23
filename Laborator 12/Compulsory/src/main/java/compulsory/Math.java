package compulsory;

import org.testng.annotations.Test;

public class Math {

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
}
