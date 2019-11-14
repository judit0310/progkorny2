package progkorny.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import progkorny.dao.IAutoDAO;
import progkorny.model.Auto;
import progkorny.model.Uzemanyag;
import progkorny.model.exceptions.DuplikaltAuto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class AutoServiceTest {

    AutoService service;


    @Before
    public void setUp() throws Exception, DuplikaltAuto {
        IAutoDAO mock= Mockito.mock(IAutoDAO.class);
        service= new AutoService(mock);
        Auto auto = new Auto("Ford", "Focus", "ABC-123", "feher", Uzemanyag.BENZIN, LocalDate.of(2014,02,15) );
        Collection<Auto> autok = new ArrayList<>();
        autok.add(auto);
        Mockito.when(mock.readAllAuto()).thenReturn(autok);
        Mockito.doThrow(DuplikaltAuto.class).when(mock).addAuto(auto);

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
}