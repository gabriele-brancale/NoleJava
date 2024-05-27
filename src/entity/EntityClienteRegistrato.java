package entity;

import java.sql.Date;

public class EntityClienteRegistrato {
	
	private int idClienteRegistrato;

	private String nome;
	private String cognome;
	private String email;
	private String password;
	
	private Date dataDiNascita;

	private String numeroPatente;

	public int getIdClienteRegistrato(){return idClienteRegistrato; }
	public String getNome(){return nome; }
	public String getCognome(){return cognome; }
	public String getEmail(){return email; }
	public String getPassword(){return password; }
	public Date getDataDiNascita(){return dataDiNascita; }
	public String getNumeroPatante(){return numeroPatente; }

	public void setIdClienteRegistrato(int idClienteRegistrato){this.idClienteRegistrato = idClienteRegistrato; }
	public void setNome(String nome){this.nome = nome; }
	public void setCognome(String cognome){this.cognome = cognome; }
	public void setEmail(String email){this.email = email; }
	public void setPassword(String password){this.password = password; }
	public void setDataDiNascita(Date dataDiNascita){this.dataDiNascita = dataDiNascita; }
	public void setNumeroPatante(String numeroPatente){this.numeroPatente = numeroPatente; }



	public EntityClienteRegistrato(int idClienteRegistrato, String nome, String cognome, String email, String password, Date dataDiNascita, 
									String numeroPatente) {

		this.idClienteRegistrato = idClienteRegistrato;
		this.nome = nome;
		this.password = password;
		this.email = email;
		this.dataDiNascita = dataDiNascita;
		this.numeroPatente = numeroPatente;

	}

}
