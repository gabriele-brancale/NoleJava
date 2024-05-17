package entity;

import java.util.Date;

public class EntityClienteRegistrato {
	
	int idClienteRegistrato;

	String nome;
	String cognome;
	String email;
	String password;
	
	Date dataDiNascita;

	String datiPatentente;
	
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
