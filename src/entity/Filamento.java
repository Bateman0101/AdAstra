package entity;

public class Filamento {

    private int id;
    private String nome;
    private String strumento;
    private String satellite;
    private String flusso;
    private String densita;
    private double ellitticita;
    private double contrasto;
    private double temperatura;


    public Filamento(int id, String nome, String strumento, String satellite, String flusso, String densita, double ellitticita, double contrasto, double temperatura) {
        this.id = id;
        this.nome = nome;
        this.strumento = strumento;
        this.satellite = satellite;
        this.flusso = flusso;
        this.densita = densita;
        this.ellitticita = ellitticita;
        this.contrasto = contrasto;
        this.temperatura = temperatura;
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

    public String getFlusso() {
        return flusso;
    }

    public void setFlusso(String flusso) {
        this.flusso = flusso;
    }

    public String getDensità() {
        return densita;
    }

    public void setDensità(String densità) {
        this.densita = densita;
    }

    public double getEllitticità() {
        return ellitticita;
    }

    public void setEllitticità(double ellitticità) {
        this.ellitticita = ellitticita;
    }

    public double getContrasto() {
        return contrasto;
    }

    public void setContrasto(double contrasto) {
        this.contrasto = contrasto;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }


}
