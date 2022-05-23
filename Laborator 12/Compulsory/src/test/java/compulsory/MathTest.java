package compulsory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathTest {

    static Math math=new Math();

    @Test
    public static void add() {
        Assert.assertEquals(15,math.add(5,10));
    }

    @Test
    public static void multiply() {
        Assert.assertEquals(50,math.multiply(5,10));
    }
}