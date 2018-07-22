package it.polito.tdp.ufo.db;

import java.time.Year;
import java.util.List;

import it.polito.tdp.ufo.model.AnnoEAvvistamento;
import it.polito.tdp.ufo.model.Neighbor;
import it.polito.tdp.ufo.model.Sighting;
import it.polito.tdp.ufo.model.State;

public class TestDAO extends SightingsDAO {

	public static void main(String[] args) {
		SightingsDAO dao = new SightingsDAO() ;
		
//		List<Sighting> list = dao.getAllSightings();
//		
//		for(Sighting s: list)
//			System.out.println(s);
//		
//		List<State> list1 = dao.getAllState() ;
//		
//		for(State s: list1)
//			System.out.println(s);
//		
//		List<Neighbor> list2 = dao.getAllNeighbor() ;
//		
//		for(Neighbor s: list2)
//			System.out.println(s);
		
		List<AnnoEAvvistamento> list3 = dao.getAnni() ;
		
		for(AnnoEAvvistamento a: list3)
			System.out.println(a);
		
		
		System.out.println(dao.esisteArco(Year.of(1966), "AK", "dc"));
		
		System.out.println(dao.getNumAvvistamenti(Year.of(1966), "ca"));
		
		System.out.println(dao.getNumVicini("ca"));
		

	}

}
