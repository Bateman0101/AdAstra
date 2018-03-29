package entity;

public class Strumento {

    private String nome;
    private String satellite;
    private double[] bande;


    public Strumento(String nome, String satellite, double[] bande) {
        this.nome = nome;
        this.satellite = satellite;
        this.bande = bande;
    }
}
