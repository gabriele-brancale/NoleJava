package entity;

import java.sql.Date;

import java.util.ArrayList;

public class EntityNoleggio {
    
    public Date dataInizio;
    public Date dataFine;

    public int idCliente;
    public EntityImbarcazione imbarcazione;

    public EntityAccessorio accessorioObbligatorio;
    public ArrayList<EntityAccessorio> accessoriOptional;

    public boolean skipper;

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
