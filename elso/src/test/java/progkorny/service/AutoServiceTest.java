package progkorny.service;

import model.exceptions.RosszRendszam;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import progkorny.dao.IAutoDAO;
import progkorny.model.Auto;
import progkorny.model.Uzemanyag;
import progkorny.model.exceptions.AutoNotFound;
import progkorny.model.exceptions.DuplikaltAuto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class AutoServiceTest {

    AutoService service;


    @Before
    public void setUp() throws Exception, DuplikaltAuto, AutoNotFound, RosszRendszam {
        IAutoDAO mock= Mockito.mock(IAutoDAO.class);
        service= new AutoService(mock);
        Auto auto = new Auto("Ford", "Focus", "ABC-123", "feher", Uzemanyag.BENZIN, LocalDate.of(2014,02,15) );
        Auto auto2 = new Auto("Opel", "Corsa", "AAA-111", "fekete", Uzemanyag.BENZIN, LocalDate.of(2011,12,19) );

        Collection<Auto> autok = new ArrayList<>();
        autok.add(auto);
        autok.add(auto2);
        Mockito.when(mock.readAllAuto()).thenReturn(autok);
        Mockito.doThrow(DuplikaltAuto.class).when(mock).addAuto(auto);


        Mockito.doThrow(RosszRendszam.class).when(mock).readAutoByRenszam(
                AdditionalMatchers.not
                        (Mockito.matches("\\w\\w\\w-\\d\\d\\d")));
        Mockito.doThrow(AutoNotFound.class).when(mock).readAutoByRenszam(Mockito.matches("\\w\\w\\w-\\d\\d\\d"));
        Mockito.doReturn(auto).when(mock).readAutoByRenszam("ABC-123");


    }


    @Test
    public void name() throws IOException {
        System.out.println(service.readAllAuto());
    }

    @Test(expected = DuplikaltAuto.class)
    public void testDuplikalt() throws IOException, DuplikaltAuto {
        Auto auto = new Auto("Ford", "Focus", "ABC-123", "feher", Uzemanyag.BENZIN, LocalDate.of(2014,02,15) );
        service.addAuto(auto);
    }

    @Test
    public void testReadByRendszamWorking() throws RosszRendszam, AutoNotFound, IOException {
        System.out.println(service.readAutoByRenszam("ABC-123"));
    }

    @Test(expected = AutoNotFound.class)
    public void testReadByRendszamException() throws RosszRendszam, AutoNotFound, IOException {
        service.readAutoByRenszam("AAA-111");
    }

    @Test(expected = RosszRendszam.class)
    public void testReadByRendszamWrongRendszam() throws RosszRendszam, AutoNotFound, IOException {
        service.readAutoByRenszam("AA-111");
    }

    @Test
    public void getCorrectFuelSize() throws IOException {
        assertEquals(2, service.getAutoByFuel(Uzemanyag.BENZIN).size());
        assertEquals(0, service.getAutoByFuel(Uzemanyag.DIESEL).size());
        assertEquals(service.readAllAuto().size(), service.getAutoByFuel(Uzemanyag.BENZIN).size()+service.getAutoByFuel(Uzemanyag.DIESEL).size());
        Collection<Auto> autok = service.readAllAuto();
        for (Auto a: service.getAutoByFuel(Uzemanyag.BENZIN)){
            assertThat(autok, Matchers.hasItem(a));
        }
        assertThat(autok, Matchers.contains(service.getAutoByFuel(Uzemanyag.BENZIN).toArray()));
    }
}