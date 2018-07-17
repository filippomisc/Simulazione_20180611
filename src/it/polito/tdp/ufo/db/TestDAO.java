package it.polito.tdp.ufo.db;

import java.time.Year;
import java.util.List;

import it.polito.tdp.ufo.model.Sighting;

public class TestDAO extends SightingsDAO {

	public static void main(String[] args) {
		SightingsDAO dao = new SightingsDAO() ;
		
//		List<Sighting> list = dao.getSightings() ;
//		
//		for(Sighting s: list)
//			System.out.println(s);

		
		System.out.println(dao.esisteArco(Year.of(1986), "ny", "ca"));
	}

}
