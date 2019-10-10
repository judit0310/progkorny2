package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Auto;
import model.Uzemanyag;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class AutoTest {
    @Ignore
    @Test
    public void testToString() {
        Auto auto = new Auto();
        System.out.println(auto);
    }
    @Ignore
    @Test
    public void testConstructor() {
        Auto auto = new Auto("Kia", "Ceed", "ABC-123", "fehér", Uzemanyag.BENZIN, LocalDate.now());
        System.out.println(auto);
    }

    @Ignore
    @Test
    public void playWithLocalDate(){
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.of(2011, 02, 25));
        System.out.println(LocalDate.parse("2010-04-04"));
        System.out.println(LocalDate.MIN);
        System.out.println(LocalDate.MAX);
        LocalDate datum = LocalDate.parse("2009-09-26");
        System.out.println(datum.isBefore(LocalDate.now()));
        System.out.println(datum.isAfter(LocalDate.now()));
        System.out.println(datum.plusMonths(3));
        System.out.println(datum.minusYears(2));
        LocalDate jovo = LocalDate.parse("2092-12-26");
        System.out.println(datum.compareTo(jovo));
        System.out.println(jovo.compareTo(datum));
        System.out.println(datum.getYear());
        System.out.println(jovo.getDayOfWeek());
        System.out.println(jovo.getDayOfMonth());
        System.out.println(jovo.getDayOfYear());
        System.out.println(jovo.isLeapYear());
        System.out.println(ZoneId.SHORT_IDS.values());
        LocalDate cairo = LocalDate.now(ZoneId.of("America/St_Johns"));
        LocalDateTime cairoTime = LocalDateTime.now(ZoneId.of("America/St_Johns"));
        System.out.println(cairoTime);
        System.out.println(LocalDate.now(ZoneId.of("Africa/Cairo")));
        System.out.println(datum.until(jovo));
        LocalDateTime time = datum.atTime(8, 23);
        System.out.println(time);
    }

    @Ignore
    @Test
    public void testMap() {
        System.out.println(Auto.hengerurtartalom.isEmpty());
        System.out.println(Auto.hengerurtartalom.values());
        System.out.println(Auto.hengerurtartalom.keySet());
        System.out.println(Auto.hengerurtartalom.containsKey("CICA"));
        System.out.println(Auto.hengerurtartalom.containsValue(1560));
        Set<String> keys = Auto.hengerurtartalom.keySet();
        for (String k : keys) {
            System.out.println(k + ":" + Auto.hengerurtartalom.get(k));
        }

        List<String> collection = new ArrayList();
        LinkedList list = new LinkedList();
        collection.add("Kicsi");
        collection.add("CICA");
        collection.add("VAGY");
        System.out.println(collection);
        for (int i = collection.size() - 1; i >= 0; i--) {
            System.out.println(collection.get(i));
        }

    }


    @Test
    public void testSet() {
        Set<String> halmaz = new HashSet<>();
        for(int i = 0; i<10;i++){
            halmaz.add(i+". cica");
        }
        System.out.println(halmaz);
        halmaz.add("8. cica");
        System.out.println(halmaz);
        for(String s:halmaz){
            System.out.println(s);
        }
    }


    @Test
    public void testFile() throws IOException {
        File file = new File(".idea");
        if(!file.exists()){
            file.createNewFile();

        }
        System.out.println(file.exists());
        System.out.println(file.isHidden());
        System.out.println(file.canExecute());
        //file.setExecutable(true);
        System.out.println(file.canExecute());
        //file.delete();

        System.out.println(file.isDirectory());
        List<File> fileok =Arrays.asList(file.listFiles());
        System.out.println(fileok);

        File[] szurtfileok = file.listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File file, String s) {
                return s.endsWith(".xml");
            }
        });
        System.out.println(szurtfileok.length);
        for(File f :szurtfileok){
            System.out.println(f.getName());
        }

    }

    @Test
    public void testJSON() throws IOException {
        File file = new File("kiscica.json");
        if(!file.exists()){
            file.createNewFile();
        }

        ObjectMapper mapper = new ObjectMapper();
        Auto auto = new Auto("Ford", "Focus", "ABC-123", "feher", Uzemanyag.BENZIN,LocalDate.of(2014,02,15) );
        System.out.println(auto);
        Collection<Auto> autok = new ArrayList<>();
        autok.add(auto);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.writeValue(file, autok);
        Collection<Auto> result = mapper.readValue(file
                , new TypeReference<ArrayList<Auto>>(){

                });
        for (Auto a: result){
            System.out.println(a.getGyartasi_ido().minusYears(1));
        }
        System.out.println(result);
    }

}