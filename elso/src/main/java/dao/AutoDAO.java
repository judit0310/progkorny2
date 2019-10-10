package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Auto;
import model.Uzemanyag;
import model.exceptions.AutoNotFound;
import model.exceptions.DuplikaltAuto;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class AutoDAO {

    File file;
    ObjectMapper mapper;
    private Logger logger = Logger.getLogger(AutoDAO.class);

    public AutoDAO(String filepath) throws IOException {
        file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
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
        return result;
    }

    public Auto readAutoByRenszam(String rendszam)
            throws IOException, AutoNotFound {
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

        }

    }
}
