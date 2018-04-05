package entity;

public class Stella {

    private int id;
    private String nome;
    private String tipo;
    private double flusso;
    private String strumento;
    private String satellite;
    private double latitudine;
    private double longitudine;


    public Stella(int id, String nome, String tipo, double flusso, String strumento,
                  String satellite,double latitudine, double longitudine) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.flusso = flusso;
        this.strumento = strumento;
        this.satellite = satellite;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }
}
