package entity;

public class PuntoSegmento {

    private double latitudine;
    private double longitudine;
    private int idSeg;
    private int num;
    private String flusso;
    private String tipo;
    private String satellite;


    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public int getIdSeg() {
        return idSeg;
    }

    public void setIdSeg(int idSeg) {
        this.idSeg = idSeg;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getFlusso() {
        return flusso;
    }

    public void setFlusso(String flusso) {
        this.flusso = flusso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public PuntoSegmento(double latitudine, double longitudine, int idSeg,
                         int num, String flusso, String tipo, String satellite) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.idSeg = idSeg;
        this.num = num;
        this.flusso = flusso;
        this.tipo = tipo;
        this.satellite = satellite;
    }
}
