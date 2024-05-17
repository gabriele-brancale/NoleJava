package entity;

import java.util.Date;

import database.SalaDAO;
import exception.DAOException;
import exception.DBConnectionException;

public class EntityProiezione {
	
	private int idProiezione;
	private Date data;
	private Date orario;
	private int idFilm;
	private int idSala;
	

	public EntityProiezione(int _id) {
		super();
		this.idProiezione = _id;
		// L'oggetto deve essere inizializzato dalla DAO
		//....
	}
	
	public EntityProiezione(int idProiezione, Date data, Date orario, int idFilm, int idSala) {
		super();
		this.idProiezione = idProiezione;
		this.data = data;
		this.orario = orario;
		this.idFilm = idFilm;
		this.idSala = idSala;
	}
	
	public int getIdProiezione() {
		return idProiezione;
	}
	public void setIdProiezione(int idProiezione) {
		this.idProiezione = idProiezione;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getOrario() {
		return orario;
	}
	public void setOrario(Date orario) {
		this.orario = orario;
	}
	public int getIdSala() {
		return idSala;
	}
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	public int getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public int capienzaSala() throws DAOException, DBConnectionException {
		
		return SalaDAO.capienza(this.getIdSala());
	}

}
