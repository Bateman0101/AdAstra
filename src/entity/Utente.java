package entity;

public class Utente {

    public Utente(String id, String nome, String cognome, String email, String password, String tipo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }

    public Utente(){

    }

    private String id;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTipo() {
        return tipo;
    }

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String tipo;
}
