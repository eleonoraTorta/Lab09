package it.polito.tdp.metrodeparis.model;

public class Linea {
	
	private int id;
	private String nome;
	private double velocita;
	private double intervallo;
	private String colore;
	
	/**
	 * @param id
	 */
	public Linea(int id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the velocita
	 */
	public double getVelocita() {
		return velocita;
	}

	/**
	 * @param velocita the velocita to set
	 */
	public void setVelocita(double velocita) {
		this.velocita = velocita;
	}

	/**
	 * @return the intervallo
	 */
	public double getIntervallo() {
		return intervallo;
	}

	/**
	 * @param intervallo the intervallo to set
	 */
	public void setIntervallo(double intervallo) {
		this.intervallo = intervallo;
	}

	/**
	 * @return the colore
	 */
	public String getColore() {
		return colore;
	}

	/**
	 * @param colore the colore to set
	 */
	public void setColore(String colore) {
		this.colore = colore;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Linea [id=" + id + ", nome=" + nome + ", velocita=" + velocita + ", intervallo=" + intervallo
				+ ", colore=" + colore + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linea other = (Linea) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
