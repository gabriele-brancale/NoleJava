package entity;

public class EntityAccessorio {
    
    boolean obbligatiorio;

    String nome;
    String descrizione;

    float prezzo;

    public EntityAccessorio(String nome, String descrizione, float prezzo, boolean obbligatiorio){

        this.nome = nome;
        this.descrizione =descrizione;
        this.prezzo = prezzo;
        this.obbligatiorio = obbligatiorio;

    }

}
