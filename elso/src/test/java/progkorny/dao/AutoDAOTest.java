package progkorny.dao;

import model.exceptions.RosszRendszam;
import org.junit.*;
import progkorny.model.Auto;
import progkorny.model.Uzemanyag;
import progkorny.model.exceptions.AutoNotFound;
import progkorny.model.exceptions.DuplikaltAuto;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

public class AutoDAOTest {
    IAutoDAO dao;
    static String filepath = "test.json";

    @BeforeClass
    public static void db() {
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Before
    public void setUp() throws IOException, DuplikaltAuto, RosszRendszam {
        dao = new AutoDAO(filepath);
        Auto a = new Auto();
        a.setGyartasi_ido(LocalDate.of(2015, 04, 25));
        a.setMarka("Opel");
        a.setModel("Astra");
        a.setRendszam("ABC-258");
        a.setSzin("kék");
        a.setUzemanyag(Uzemanyag.BENZIN);
        dao.addAuto(a);
        Auto b = new Auto();
        b.setGyartasi_ido(LocalDate.of(2013, 12, 4));
        b.setMarka("Kia");
        b.setModel("Rio");
        b.setRendszam("EEE-111");
        b.setSzin("fekete");
        b.setUzemanyag(Uzemanyag.DIESEL);
        dao.addAuto(b);
    }
    @After
    public void tearDown(){
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
    }


    @Test
    public void test() throws IOException, DuplikaltAuto, RosszRendszam {
        Auto a = new Auto();
        a.setGyartasi_ido(LocalDate.of(2015, 04, 25));
        a.setMarka("Opel");
        a.setModel("Astra");
        a.setRendszam("AAA-123");
        a.setSzin("kék");
        a.setUzemanyag(Uzemanyag.BENZIN);
        dao.addAuto(a);
    }

    @Test(expected = DuplikaltAuto.class)
    public void testDuplikalt() throws IOException, DuplikaltAuto, RosszRendszam {
        Auto b = new Auto();
        b.setGyartasi_ido(LocalDate.of(2013, 12, 4));
        b.setMarka("fds");
        b.setModel("asdf");
        b.setRendszam("EEE-111");
        b.setSzin("rózsaszín");
        b.setUzemanyag(Uzemanyag.DIESEL);
        dao.addAuto(b);
    }

    @Test
    public void testBaseDataSize() throws IOException {
        Assert.assertEquals(2, dao.readAllAuto().size(),0.0005);
    }

    @Ignore
    @Test
    public void fail(){
        Assert.fail();
    }

    @Test
    public void rendszamnotnull() throws IOException {
        Collection<Auto> autok = dao.readAllAuto();
        for (Auto auto: autok){
            Assert.assertNotNull(auto.getRendszam());
        }
    }

    @Test(expected = IOException.class)
    public void testIfPermissionDenied() throws IOException {
        File fileobj = new File(filepath);
        fileobj.delete();
        System.out.println(dao.readAllAuto());
    }

    @Test(expected = RosszRendszam.class)
    public void testRosszRendszamInReadAutoById() throws RosszRendszam, AutoNotFound, IOException {
        String rossz_rendszam="ABB-AAA";
        dao.readAutoByRenszam(rossz_rendszam);
    }

}