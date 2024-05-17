package entity;

public class EntityClienteRegistrato {
	
	private int idClienteRegistrato;
	private String nomeUtente;
	private String password;
	private String indirizzoEmail;
	private String cartaDiCredito;
	
	public EntityClienteRegistrato() {
		// TO DO
		super();
	}
	
	
	public EntityClienteRegistrato(int idClienteRegistrato, String nomeUtente, String password, String indirizzoEmail,
			String cartaDiCredito) {
		super();
		this.idClienteRegistrato = idClienteRegistrato;
		this.nomeUtente = nomeUtente;
		this.password = password;
		this.indirizzoEmail = indirizzoEmail;
		this.cartaDiCredito = cartaDiCredito;
	}
	public int getIdClienteRegistrato() {
		return idClienteRegistrato;
	}
	public void setIdClienteRegistrato(int idClienteRegistrato) {
		this.idClienteRegistrato = idClienteRegistrato;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIndirizzoEmail() {
		return indirizzoEmail;
	}
	public void setIndirizzoEmail(String indirizzoEmail) {
		this.indirizzoEmail = indirizzoEmail;
	}
	public String getCartaDiCredito() {
		return cartaDiCredito;
	}
	public void setCartaDiCredito(String cartaDiCredito) {
		this.cartaDiCredito = cartaDiCredito;
	}

}
