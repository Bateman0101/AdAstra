package entity;

public class StellaDistanza {
    private int id;
    private String nome;
    private String tipo;
    private double flusso;
    private String strumento;
    private String satellite;
    private double latitudine;
    private double longitudine;
    private double distanza;

    public StellaDistanza(int id, String nome, String tipo, double flusso, String strumento, String satellite,
                          double latitudine, double longitudine, double distanza) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.flusso = flusso;
        this.strumento = strumento;
        this.satellite = satellite;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.distanza = distanza;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getFlusso() {
        return flusso;
    }

    public void setFlusso(double flusso) {
        this.flusso = flusso;
    }

    public String getStrumento() {
        return strumento;
    }

    public void setStrumento(String strumento) {
        this.strumento = strumento;
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite;
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

    public double getDistanza() {
        return distanza;
    }

    public void setDistanza(double distanza) {
        this.distanza = distanza;
    }
}
