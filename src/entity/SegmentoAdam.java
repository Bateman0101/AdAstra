package entity;

public class SegmentoAdam {

    private int id;
    private Character tipo;


    public SegmentoAdam(int id, Character tipo) {
        this.id = id;
        this.tipo = tipo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }
}
