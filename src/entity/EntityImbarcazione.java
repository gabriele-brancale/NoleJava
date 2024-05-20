package entity;

public class EntityImbarcazione {

    public int id;

    String nome;
    String targa;
    String tipologia;
    String stato;

    int capienza;
    float costo;

    public EntityImbarcazione(int id, String nome, String targa, String tipologia, String stato, int capienza, float costo){

        this.nome = nome;
        this.targa = targa;
        this.tipologia = tipologia;
        this.stato = stato;
        this.capienza = capienza;
        this.costo = costo;

    }

}
