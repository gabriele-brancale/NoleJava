package entity;

import database.BigliettoDAO;
import exception.DAOException;
import exception.DBConnectionException;

public class EntityBiglietto {
	private int idBiglietto;
	private float costo;
	private int numPosto;
	private int idProiezione;
	private int idCliente;
	
	private EntityProiezione prz;
	private EntityClienteRegistrato cln;
	
//	public EntityBiglietto(int idBiglietto, float costo, int numPosto, int idProiezione, int idCliente) {
//		super();
//		this.idBiglietto = idBiglietto;
//		this.costo = costo;
//		this.numPosto = numPosto;
//		this.prz = new EntityProiezione(idProiezione);
//		this.cln = new EntityClienteRegistrato(idCliente);
//	}
	
	public EntityBiglietto(int idBiglietto, float costo, int numPosto, int idProiezione, int idCliente) {
		super();
		this.idBiglietto = idBiglietto;
		this.costo = costo;
		this.numPosto = numPosto;
		this.idProiezione = idProiezione;
		this.idCliente = idCliente;
	}

	
	public int getIdProiezione() {
		return idProiezione;
	}

	public void setIdProiezione(int idProiezione) {
		this.idProiezione = idProiezione;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public int getIdBiglietto() {
		return idBiglietto;
	}
	public void setIdBiglietto(int idBiglietto) {
		this.idBiglietto = idBiglietto;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public int getNumPosto() {
		return numPosto;
	}
	public void setNumPosto(int numPosto) {
		this.numPosto = numPosto;
	}

	public void saveBiglietto() throws DAOException, DBConnectionException {
		
		BigliettoDAO.createBiglietto(this);
		
	}
	
	
	
}
