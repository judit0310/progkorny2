import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AutoTest {
    @Test
    public void testToString(){
        Auto auto = new Auto();
        System.out.println(auto);
    }
    @Test
    public void testConstructor(){
        Auto auto = new Auto("Kia","Ceed","ABC-123","fehér",Uzemanyag.BENZIN, LocalDate.now());
        System.out.println(auto);
    }
}