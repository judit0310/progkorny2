package progkorny.dao;

import model.exceptions.RosszRendszam;
import progkorny.model.Auto;
import progkorny.model.exceptions.AutoNotFound;
import progkorny.model.exceptions.DuplikaltAuto;

import java.io.IOException;
import java.util.Collection;

public interface IAutoDAO {
    Collection<Auto> readAllAuto() throws IOException;
    Auto readAutoByRenszam(String rendszam)
            throws IOException, AutoNotFound, RosszRendszam;
    void addAuto(Auto auto) throws IOException, DuplikaltAuto;
}
