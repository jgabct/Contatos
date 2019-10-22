package app;

public class Contato {
	private String name;
	private String endereco;
	private String email;
	private String instagram;
	private String local_de_trabalho;
	private int celular;
	private int telefone;

	Contato() {

	}

	Contato(String nome, int celular, int telefone, String endereço, String email, String instagram,
			String local_de_trabalho) {
		this.name = nome;
		this.endereco = endereço;
		this.email = email;
		this.instagram = instagram;
		this.local_de_trabalho = local_de_trabalho;
		this.celular = celular;
		this.telefone = telefone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEndereco(String enderaco) {
		this.endereco = enderaco;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public void setLocalDeTrabalho(String local_de_trabalho) {
		this.local_de_trabalho = local_de_trabalho;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getName() {
		return this.name;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public String getEmail() {
		return this.email;
	}

	public String getInstagram() {
		return this.instagram;
	}

	public String getLocalDeTrabalho() {
		return this.local_de_trabalho;
	}

	public int getCelular() {
		return this.celular;
	}

	public int getTelefone() {
		return this.telefone;
	}

}
