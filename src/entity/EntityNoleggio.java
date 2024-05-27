package entity;

import java.sql.Date;
import java.util.ArrayList;

public class EntityNoleggio {
    
    private Date dataInizio;
    private Date dataFine;

    private int idCliente;
    private EntityImbarcazione imbarcazione;

    private EntityAccessorio accessorioObbligatorio;
    private ArrayList<EntityAccessorio> accessoriOptional;

    private boolean skipper;

    public Date getDataInizio(){return dataInizio; }
    public Date getDataFine(){return dataFine; }
    public int getIdCliente(){return idCliente; }
    public EntityImbarcazione getImbarcazione(){return imbarcazione; }
    public EntityAccessorio getAccessorioObbligatorio(){return accessorioObbligatorio; }
    public ArrayList<EntityAccessorio> getAccessoriOptional(){return accessoriOptional; }
    public boolean getSkipper(){return skipper; }

    public void setDataInizio(Date dataInizio){this.dataInizio = dataInizio; }
    public void setDataFine(Date dataFine){this.dataFine = dataFine; }
    public void setIdCliente(int idCliente){this.idCliente = idCliente; }
    public void setImbarcazione(EntityImbarcazione imbarcazione){this.imbarcazione = imbarcazione; }
    public void setAccessorioObbligatorio(EntityAccessorio accessorioObbligatorio){this.accessorioObbligatorio = accessorioObbligatorio; }
    public void setAccessoriOptional(ArrayList<EntityAccessorio> accessorioOptional){this.accessoriOptional = accessorioOptional; }
    public void setSkipper(boolean skipper){this.skipper = skipper; }

    public EntityNoleggio(Date dataInizio, Date dataFine, int idCliente, EntityImbarcazione imbarcazione, EntityAccessorio accessorioObbligatorio, ArrayList<EntityAccessorio> accessoriOptional, boolean skipper){

        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.idCliente = idCliente;
        this.imbarcazione = imbarcazione;
        this.accessorioObbligatorio = accessorioObbligatorio;
        this.accessoriOptional = accessoriOptional;
        this.skipper = skipper;

    }

}
