package it.polito.tdp.lab04.model;

public class Corso {
	
	private String codice;
	private int crediti;
	private String nome;
	private int semestre;
	
	public Corso(String codice, int crediti, String nome, int semestre) {
		this.codice = codice;
		this.crediti = crediti;
		this.nome = nome;
		this.semestre = semestre;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	public String descriviCorso() {
		return codice + " "+crediti + " "+ nome+" "+semestre;
	}
	
	public String toString(){
		return nome;
	}
	
	
	

}
