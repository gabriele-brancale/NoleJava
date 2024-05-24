package entity;

public class EntityImbarcazione {

    public String targa;
    public String nome;
    public String tipologia;
    public String stato;

    public int capienza;
    public float costo;

    public EntityImbarcazione(String targa, String nome, String tipologia, String stato, int capienza, float costo){

        this.nome = nome;
        this.targa = targa;
        this.tipologia = tipologia;
        this.stato = stato;
        this.capienza = capienza;
        this.costo = costo;

    }

}
