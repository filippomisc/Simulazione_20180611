package it.polito.tdp.ufo.model;

public class Neighbor {
	private int id;
	private State state1;
	private State state2;
	
	
	public Neighbor(int id, State state1, State state2) {
		super();
		this.id = id;
		this.state1 = state1;
		this.state2 = state2;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public State getState1() {
		return state1;
	}
	public void setState1(State state1) {
		this.state1 = state1;
	}
	public State getState2() {
		return state2;
	}
	public void setState2(State state2) {
		this.state2 = state2;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Neighbor other = (Neighbor) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return id + " s1: " + state1.getId() + " s2: " + state2.getId();
	}

//*******************servono solo se non usiamo un Id
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((state1 == null) ? 0 : state1.hashCode());
//		result = prime * result + ((state2 == null) ? 0 : state2.hashCode());
//		return result;
//	}
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Neighbor other = (Neighbor) obj;
//		if (state1 == null) {
//			if (other.state1 != null)
//				return false;
//		} else if (!state1.equals(other.state1))
//			return false;
//		if (state2 == null) {
//			if (other.state2 != null)
//				return false;
//		} else if (!state2.equals(other.state2))
//			return false;
//		return true;
//	}
//
//
//	@Override
//	public String toString() {
//		return state1 + "  " + state2;
//	}
	
	

}
