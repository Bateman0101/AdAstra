package entity;


public class PuntoPerimetro {

    private double latitudine;
    private double longitudine;
    private String satellite;
    private int filamento;


    public PuntoPerimetro(double latitudine, double longitudine, String satellite, int filamento) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.satellite = satellite;
        this.filamento = filamento;
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

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
    }

    public int getFilamento() {
        return filamento;
    }

    public void setFilamento(int filamento) {
        this.filamento = filamento;
    }
}
