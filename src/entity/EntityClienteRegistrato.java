package entity;

import java.sql.Date;

public class EntityClienteRegistrato {
	
	public int idClienteRegistrato;

	public String nome;
	public String cognome;
	public String email;
	public String password;
	
	public Date dataDiNascita;

	public String datiPatentente;
	
	public EntityClienteRegistrato(int idClienteRegistrato, String nome, String cognome, String email, String password, Date dataDiNascita, 
									String datiPatente) {

		this.idClienteRegistrato = idClienteRegistrato;
		this.nome = nome;
		this.password = password;
		this.email = email;
		this.dataDiNascita = dataDiNascita;
		this.datiPatentente = datiPatente;

	}

}
