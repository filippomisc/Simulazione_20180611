package it.polito.tdp.ufo.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import org.jgrapht.graph.*;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.ufo.db.AnnoEAvvistamento;
import it.polito.tdp.ufo.db.SightingsDAO;

public class Model {
	
	private List<AnnoEAvvistamento> anniAvvistamenti;
	private SightingsDAO dao;
	
	private Graph<String, DefaultEdge> graph;
	private List<String> stati;
	
	public Model() {
		
		dao = new SightingsDAO();
		
		this.graph = new SimpleDirectedGraph<>(DefaultEdge.class);
		
//		this.stati = new ArrayList<>();
		
		
		}
	
	
	
	public List<String> getStati() {
		return stati;
	}



	public List<AnnoEAvvistamento> getAnniAvvistamenti(){
		
		this.anniAvvistamenti = dao.getAnni();
		
		return this.anniAvvistamenti;
	}
	
	public Graph<String, DefaultEdge> getGraph() {
		return graph;
	}



	public void createGraph(Year anno) {
		
		stati = dao.getStati(anno);
		
		
		Graphs.addAllVertices(graph, stati);
		
		
//		for (String state1 : graph.vertexSet()) {
//			for (String state2 : graph.vertexSet()) {
//				if(!state1.equals(state2)){ // -------> fatto nella query seguente
//					
////					boolean esisteArco = dao.esisteArco(anno, state1, state2);
//					
//					if(dao.esisteArco(anno, state1, state2));
//						graph.addEdge(state1, state2);
//				}
//			}
//		}
		
		
		List<StringPair>archi = dao.getEdges(anno) ;
		for(StringPair sp : archi) {
			graph.addEdge(sp.getStr1(), sp.getStr2()) ;
			}
		System.out.println("vertici: " + graph.vertexSet().size() + " archi: " + graph.edgeSet().size());
	}


	public List<String> getPredecessori(String stato){
//		List<String> result = new ArrayList<>();
		return Graphs.predecessorListOf(this.graph, stato);
		}
	
	public List<String> getSuccessori(String stato){
//		List<String> result = new ArrayList<>(); 
		return Graphs.successorListOf(this.graph, stato);
		}

	public List<String> getStatiRaggiungibili(String stato){
		
		GraphIterator<String, DefaultEdge> bfv = new BreadthFirstIterator<>(this.graph, stato);
		
		List<String> raggiungibili = new ArrayList<>();
		
		while(bfv.hasNext()) {
			raggiungibili.add(bfv.next());
		}
		
		raggiungibili.remove(stato);//rimuoviamo dalla lista il vertice da analizzare (primo della lista)
		return raggiungibili;
	}
//	public List<String> getVertex() {
//
//
//		List<String> vertici = new ArrayList<>();
//		
//		for (String s : this.graph.vertexSet()) {
//			vertici.add(s);
//			
//		}
//		return vertici;
//	}
}
