package entity;

public class EntityAccessorio {
    
    public boolean obbligatiorio;

    public int id;

    public String nome;
    public String descrizione;

    public float prezzo;

    public EntityAccessorio(int id, String nome, String descrizione, float prezzo, boolean obbligatiorio){

        this.id = id;
        this.nome = nome;
        this.descrizione =descrizione;
        this.prezzo = prezzo;
        this.obbligatiorio = obbligatiorio;

    }

}