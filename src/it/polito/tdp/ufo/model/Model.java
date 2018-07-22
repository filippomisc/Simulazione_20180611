package it.polito.tdp.ufo.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.ufo.db.SightingsDAO;

public class Model {

//	private List<AnnoEAvvistamento> anniAvvistamenti;
	SightingsDAO dao = null;
	
	List<Sighting> sightings;
	List<State> states;
	List<Neighbor> neighbors;
	
	SightingIdMap sightingIdMap;
	StateIdMap stateIdMap;
	NeighborIdMap neighborIdMap;
	
	
//	private Graph<String, DefaultEdge> graph;
//	private List<String> stati;
	
	
	
	public Model() {
		
		dao = new SightingsDAO();
		
//		this.graph = new SimpleDirectedGraph<>(DefaultEdge.class);
		
//		this.stati = new ArrayList<>();
		
		sightingIdMap = new SightingIdMap();
		stateIdMap = new StateIdMap();
		neighborIdMap = new NeighborIdMap();
		
		sightings = dao.getAllSightings(sightingIdMap, stateIdMap);
		System.out.println(sightings.size());
		
		states = dao.getAllState(stateIdMap);
		System.out.println(states.size());
		
		neighbors = dao.getAllNeighbor(neighborIdMap, stateIdMap);
		System.out.println(neighbors.size());
		
		
}
	
	
	
	
	
	
	
	
	
	
}
