package entity;

public class EntityImbarcazione {

    private String targa;
    private String nome;
    private String tipologia;
    private String stato;

    private int capienza;
    private float costo;

    public String getTarga(){return targa; }
    public String getNome(){return nome; }
    public String getTipologia(){return tipologia; }
    public String getStato(){return stato; }
    public int getCapienza(){return capienza; }
    public float getCosto(){return costo; }

    public void setTarga(String targa){this.targa = targa; }
    public void setNome(String nome){this.nome = targa; }
    public void setTipologia(String tipologia){this.tipologia = tipologia; }
    public void setStato(String stato){ this.stato = stato; }
    public void setCapienza(int capienza){this.capienza = capienza; }
    public void setCosto(float costo){this.costo = costo; }

    public EntityImbarcazione(String targa, String nome, String tipologia, String stato, int capienza, float costo){

        this.nome = nome;
        this.targa = targa;
        this.tipologia = tipologia;
        this.stato = stato;
        this.capienza = capienza;
        this.costo = costo;

    }

}