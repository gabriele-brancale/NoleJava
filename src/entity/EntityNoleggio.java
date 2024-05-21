package entity;

import java.sql.Date;

import java.util.ArrayList;

public class EntityNoleggio {
    
    public Date dataInizio;
    public Date dataFine;

    public int idCliente;
    public EntityImbarcazione imbarcazione;

    public EntityAccessorio accessorio_obbligatorio;
    public ArrayList<EntityAccessorio> accessori_optional;

    public boolean skipper;

}
