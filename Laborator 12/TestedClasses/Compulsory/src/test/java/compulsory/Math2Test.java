package compulsory;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Math2Test {

    Math2 math2=new Math2();

    Math2Test(){}

    @Test
    void substract1(){
        Assert.assertEquals(15,math2.substract(20,5));
    }

    @Test
     void substract2(){
        Assert.assertEquals(20,math2.substract(20,5));
    }

}