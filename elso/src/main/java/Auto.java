import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Auto {
    protected static Map<String, Integer> hengerurtartalom;

    static{
        hengerurtartalom= new HashMap<>();
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


    public Auto(){

    }

    public Auto(String marka, String model, String rendszam, String szin, Uzemanyag uzemanyag, LocalDate gyartasi_ido) {
        this.marka = marka;
        this.model = model;
        this.rendszam = rendszam;
        this.szin = szin;
        this.uzemanyag = uzemanyag;
        this.gyartasi_ido = gyartasi_ido;
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

    public void setRendszam(String rendszam) {
        this.rendszam = rendszam;
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
        return "Auto{" +
                "marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rendszam='" + rendszam + '\'' +
                ", szin='" + szin + '\'' +
                ", uzemanyag=" + uzemanyag +
                ", gyartasi_ido=" + gyartasi_ido +
                '}';
    }
}
