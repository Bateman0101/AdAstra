package entity;

public class PuntoScheletro {

    private double latitudine;
    private double longitudine;
    private int segmento;
    private int numero;
    private String flusso;
    private String tipo;
    private String satellite;
    private int idFil;

    public PuntoScheletro(double latitudine, double longitudine, int segmento,
                          int numero, String flusso, String tipo, String satellite,
                          int idFil) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.segmento = segmento;
        this.numero = numero;
        this.flusso = flusso;
        this.tipo = tipo;
        this.satellite = satellite;
        this.idFil = idFil;
    }

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

    public int getSegmento() {
        return segmento;
    }

    public void setSegmento(int segmento) {
        this.segmento = segmento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public int getIdFil() {
        return idFil;
    }

    public void setIdFil(int idFil) {
        this.idFil = idFil;
    }


}
