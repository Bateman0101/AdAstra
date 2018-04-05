package entity;

import java.util.Date;

public class Satellite {

    private String nome;
    private Date dataInizio;
    private Date dataFine;
    private String agenzia;

    public Satellite(String nome, Date dataInizio, Date dataFine, String agenzia) {
        this.nome = nome;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.agenzia = agenzia;
    }

}
