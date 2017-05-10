package it.polito.tdp.metrodeparis.model;

import org.jgrapht.graph.DefaultWeightedEdge;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Collegamento extends DefaultWeightedEdge{
	
	private int id;
	private Linea linea;
	private Fermata partenza;
	private Fermata arrivo;
	private double peso;
	
	
	/**
	 * @param linea
	 * @param partenza
	 * @param arrivo
	 * @param peso
	 */
	public Collegamento(int id, Linea linea, Fermata partenza, Fermata arrivo) {
		super();
		this.id = id;
		this.linea = linea;
		this.partenza = partenza;
		this.arrivo = arrivo;
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Collegamento [linea=" + linea + ", partenza=" + partenza + ", arrivo=" + arrivo + ", peso=" + peso
				+ "]";
	}


	/**
	 * @return the linea
	 */
	public Linea getLinea() {
		return linea;
	}


	/**
	 * @param linea the linea to set
	 */
	public void setLinea(Linea linea) {
		this.linea = linea;
	}


	/**
	 * @return the partenza
	 */
	public Fermata getPartenza() {
		return partenza;
	}


	/**
	 * @param partenza the partenza to set
	 */
	public void setPartenza(Fermata partenza) {
		this.partenza = partenza;
	}


	/**
	 * @return the arrivo
	 */
	public Fermata getArrivo() {
		return arrivo;
	}


	/**
	 * @param arrivo the arrivo to set
	 */
	public void setArrivo(Fermata arrivo) {
		this.arrivo = arrivo;
	}


	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivo == null) ? 0 : arrivo.hashCode());
		result = prime * result + ((linea == null) ? 0 : linea.hashCode());
		result = prime * result + ((partenza == null) ? 0 : partenza.hashCode());
		long temp;
		temp = Double.doubleToLongBits(peso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Collegamento other = (Collegamento) obj;
		if (arrivo == null) {
			if (other.arrivo != null)
				return false;
		} else if (!arrivo.equals(other.arrivo))
			return false;
		if (linea == null) {
			if (other.linea != null)
				return false;
		} else if (!linea.equals(other.linea))
			return false;
		if (partenza == null) {
			if (other.partenza != null)
				return false;
		} else if (!partenza.equals(other.partenza))
			return false;
		if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
			return false;
		return true;
	}


	//calcolare il peso dell'arco
	public void setPeso(){
		
		double velocita = linea.getVelocita();
		
		//calcolo la distanza tra le due stazioni
		double distanza = LatLngTool.distance(partenza.getCoords(), arrivo.getCoords(), LengthUnit.KILOMETER);
		
		//calcolo peso
		 peso = distanza/ velocita;
		 peso = peso*(3600);
	
	}
	

	
}
