package entity;

public class EntityAccessorio {
    
    private boolean obbligatorio;

    private int id;

    private String nome;
    private String descrizione;

    private float prezzo;

    public boolean getObbligatorio(){return obbligatorio; }
    public int getId(){return id; }
    public String getNome(){return nome; }
    public String getDescrizione(){return descrizione; }
    public float getPrezzo(){return prezzo; }

    public void setObbligatorio(boolean obbligatorio){this.obbligatorio = obbligatorio; }
    public void setId(int id){this.id = id; }
    public void setNome(String nome){this.nome = nome; }
    public void setDescrizione(String descrizione){this.descrizione = descrizione; }
    public void setPrezzo(float prezzo){this.prezzo = prezzo; }

    public EntityAccessorio(int id, String nome, String descrizione, float prezzo, boolean obbligatorio){

        this.id = id;
        this.nome = nome;
        this.descrizione =descrizione;
        this.prezzo = prezzo;
        this.obbligatorio = obbligatorio;

    }

}