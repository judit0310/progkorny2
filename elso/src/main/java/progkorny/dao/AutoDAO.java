package progkorny.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.exceptions.RosszRendszam;
import progkorny.model.Auto;
import progkorny.model.exceptions.AutoNotFound;
import progkorny.model.exceptions.DuplikaltAuto;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class AutoDAO implements IAutoDAO {

    File file;
    ObjectMapper mapper;
    private Logger logger = Logger.getLogger(AutoDAO.class);

    public AutoDAO(String filepath) throws IOException {
        file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
            FileWriter writer = new FileWriter(filepath);
            writer.write("[]");
            writer.close();
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        logger.debug("DAO inicializálva");
        logger.log(Priority.DEBUG, "NAEZ");


    }

    public Collection<Auto> readAllAuto() throws IOException {
        Collection<Auto> result = mapper.readValue(file
                , new TypeReference<ArrayList<Auto>>() {

                });
        logger.info("Az adatbazisban levo adatok szama:"+result.size());
        return result;
    }

    public Auto readAutoByRenszam(String rendszam)
            throws IOException, AutoNotFound, RosszRendszam {
        if(!Auto.checkRendszam(rendszam)){
            throw new RosszRendszam(rendszam);
        }
        Collection<Auto> autok = readAllAuto();
        for (Auto auto : autok) {
            if (auto.getRendszam().equalsIgnoreCase(rendszam)) {
                return auto;
            }
        }
        throw new AutoNotFound(rendszam);
    }

    public void addAuto(Auto auto) throws IOException, DuplikaltAuto {
        try {
            readAutoByRenszam(auto.getRendszam());
            throw new DuplikaltAuto();
        } catch (AutoNotFound autoNotFound) {
                Collection<Auto> autok = readAllAuto();
                autok.add(auto);
                mapper.writeValue(file, autok);
                logger.info("Uj auto hozzaadva, az autok szama innentől: "+autok.size());

        } catch (RosszRendszam rosszRendszam) {
            rosszRendszam.printStackTrace();
        }

    }
}
