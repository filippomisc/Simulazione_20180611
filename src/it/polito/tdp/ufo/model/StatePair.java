package it.polito.tdp.ufo.model;

public class StatePair {

	private State state1;
	private State state2; 
	private int peso;
	
	
	public StatePair(State state1, State state2, int peso) {
		super();
		this.state1 = state1;
		this.state2 = state2;
		this.peso = peso;
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


	public int getPeso() {
		return peso;
	}


	public void setPeso(int peso) {
		this.peso = peso;
	}


	@Override
	public String toString() {
		return state1 + " " + state2 + " " + peso;
	}
	
	
}
