package progkorny.service;

import model.exceptions.RosszRendszam;
import progkorny.dao.IAutoDAO;
import progkorny.model.Auto;
import progkorny.model.Uzemanyag;
import progkorny.model.exceptions.AutoNotFound;
import progkorny.model.exceptions.DuplikaltAuto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class AutoService {
    IAutoDAO dao;

    public AutoService(IAutoDAO dao) {
        this.dao = dao;
    }

    Collection<Auto> readAllAuto() throws IOException {
        return dao.readAllAuto();
    }


    Auto readAutoByRenszam(String rendszam)
            throws IOException, AutoNotFound, RosszRendszam {
        return dao.readAutoByRenszam(rendszam);
    }


    void addAuto(Auto auto) throws IOException, DuplikaltAuto {
        dao.addAuto(auto);
    }

    Collection<Auto> getAllAutoFromYear(int year) throws IOException {
        Collection<Auto> result = new ArrayList<>();
        Collection<Auto> autok = readAllAuto();
        for (Auto a : autok
        ) {
            if (a.getGyartasi_ido().getYear() == year) {
                result.add(a);
            }

        }
        return result;

    }

    Collection<Auto> getAutoByFuel(Uzemanyag uzemanyag) throws IOException {
        Collection<Auto> autok = dao.readAllAuto();
        Collection<Auto> result = new ArrayList<>();
        for (Auto a : autok
        ) {
            if(a.getUzemanyag() == uzemanyag){
                result.add(a);
            }

        }

        return result;

    }


}
