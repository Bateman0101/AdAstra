package entity;

public class Segmento {

    private int id;
    private String tipo;
    private String satellite;

    public Segmento(int id, String tipo, String satellite) {
        this.id = id;
        this.tipo = tipo;
        this.satellite = satellite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setSatelliteFilamento(String satelliteFilamento) {
        this.satellite = satelliteFilamento;
    }

}
