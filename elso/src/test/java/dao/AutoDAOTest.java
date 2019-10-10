package dao;

import model.Auto;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AutoDAOTest {
    @Test
    public void testLOG() throws IOException {
        AutoDAO dao = new AutoDAO("kiscica.json");
    }

}