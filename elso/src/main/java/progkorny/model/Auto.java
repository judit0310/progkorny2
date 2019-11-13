package progkorny.model;

import model.exceptions.RosszRendszam;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Auto {
    private Logger logger = Logger.getLogger(Auto.class);
    protected static Map<String, Integer> hengerurtartalom;

    static {
        hengerurtartalom = new HashMap<>();
        hengerurtartalom.put("1.0", 998);
        hengerurtartalom.put("1.4", 1390);
        hengerurtartalom.put("1.6", 1560);
    }

    private String marka;
    private String model;
    private String rendszam;
    private String szin;
    private Uzemanyag uzemanyag;
    private LocalDate gyartasi_ido;


    public Auto() {

    }

    public Auto(String marka, String model, String rendszam, String szin, Uzemanyag uzemanyag, LocalDate gyartasi_ido) {
        this.marka = marka;
        this.model = model;
        this.rendszam = rendszam;
        this.szin = szin;
        this.uzemanyag = uzemanyag;
        this.gyartasi_ido = gyartasi_ido;
        logger.info("Uj Auto hozzaadva:" + this);
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRendszam() {
        return rendszam;
    }

    public void setRendszam(String rendszam) throws RosszRendszam {
        if (checkRendszam(rendszam)) {
            this.rendszam = rendszam;
        }else{
        throw new RosszRendszam(rendszam);}
    }

    public String getSzin() {
        return szin;
    }

    public void setSzin(String szin) {
        this.szin = szin;
    }

    public Uzemanyag getUzemanyag() {
        return uzemanyag;
    }

    public void setUzemanyag(Uzemanyag uzemanyag) {
        this.uzemanyag = uzemanyag;
    }

    public LocalDate getGyartasi_ido() {
        return gyartasi_ido;
    }

    public void setGyartasi_ido(LocalDate gyartasi_ido) {
        this.gyartasi_ido = gyartasi_ido;
    }


    @Override
    public String toString() {
        return "progkorny.model.Auto{" +
                "marka='" + marka + '\'' +
                ", progkorny.model='" + model + '\'' +
                ", rendszam='" + rendszam + '\'' +
                ", szin='" + szin + '\'' +
                ", uzemanyag=" + uzemanyag +
                ", gyartasi_ido=" + gyartasi_ido +
                '}';
    }

    public static boolean checkRendszam(String rendszam){
        return rendszam.matches("[A-z]{3}-\\d{3}");
    }
}