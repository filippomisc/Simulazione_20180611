package it.polito.tdp.ufo.db;

import java.time.Year;

public class AnnoEAvvistamento {
	
	private Year anno;
	private int numAvvistamenti;
	
	
	public AnnoEAvvistamento(Year anno, int numAvvistamenti) {
		this.anno = anno;
		this.numAvvistamenti = numAvvistamenti;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anno == null) ? 0 : anno.hashCode());
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
		AnnoEAvvistamento other = (AnnoEAvvistamento) obj;
		if (anno == null) {
			if (other.anno != null)
				return false;
		} else if (!anno.equals(other.anno))
			return false;
		return true;
	}


//useremo questo toString per stampare sul menu a tendina;
	@Override
	public String toString() {
		return anno + " (" + numAvvistamenti + ")";
	}



	public Year getAnno() {
		return anno;
	}


	public void setAnno(Year anno) {
		this.anno = anno;
	}


	public int getNumAvvistamenti() {
		return numAvvistamenti;
	}


	public void setNumAvvistamenti(int numAvvistamenti) {
		this.numAvvistamenti = numAvvistamenti;
	}

	
}
