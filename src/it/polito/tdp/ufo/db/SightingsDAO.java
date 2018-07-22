package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ufo.model.AnnoEAvvistamento;
import it.polito.tdp.ufo.model.Neighbor;
import it.polito.tdp.ufo.model.Sighting;
import it.polito.tdp.ufo.model.State;
import it.polito.tdp.ufo.model.StringPair;

public class SightingsDAO {
	
	public List<Sighting> getAllSightings() {
		String sql = "SELECT * FROM sighting" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Sighting> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				list.add(new Sighting(res.getInt("id"),
						res.getTimestamp("datetime").toLocalDateTime(),
						res.getString("city"), 
						res.getString("state"), 
						res.getString("country"),
						res.getString("shape"),
						res.getInt("duration"),
						res.getString("duration_hm"),
						res.getString("comments"),
						res.getDate("date_posted").toLocalDate(),
						res.getDouble("latitude"), 
						res.getDouble("longitude"))) ;
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	public List<State> getAllState(){
		
		String sql = "SELECT * FROM state" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<State> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				list.add(new State(res.getString("id"), res.getString("Name"), res.getString("Capital"), res.getDouble("Lat"), res.getDouble("Lng"), res.getInt("Area"), res.getInt("Population"), res.getString("Neighbors"))) ;
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public List<Neighbor> getAllNeighbor(){
		
		String sql = "SELECT * FROM neighbor" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Neighbor> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				list.add(new Neighbor(res.getString("state1"), res.getString("state2"))) ;
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
//public int getAllNeighbor(){
//		
//		String sql = "SELECT * FROM neighbor" ;
//		try {
//			Connection conn = DBConnect.getConnection() ;
//
//			PreparedStatement st = conn.prepareStatement(sql) ;
//			
//			List<Neighbor> list = new ArrayList<>() ;
//			
//			ResultSet res = st.executeQuery() ;
//			
//			while(res.next()) {
//				list.add(new Neighbor(res.getString("state1"), res.getString("state2"))) ;
//			}
//			
//			conn.close();
//			return list ;
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null ;
//		}
//	}
	
	public List<AnnoEAvvistamento> getAnni() {
		String sql = "select distinct year(datetime) as anno, count(id) as cnt\r\n" + 
				"from sighting\r\n" + 
				"where country='us'\r\n" + 
				"group by anno\r\n" + 
				"order by anno asc" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<AnnoEAvvistamento> anni = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				
				
				AnnoEAvvistamento aEa = new AnnoEAvvistamento(Year.of(res.getInt("anno")), res.getInt("cnt"));
				
				anni.add(aEa);
			}
				
			
			conn.close();
			return anni ;

		} catch (SQLException e) {
			e.printStackTrace();		
			return null;
			}
}
	
public boolean esisteArco (Year anno, String s1, String s2){
		
		String sql = "select distinct count(*) as c " + 
				"from sighting s1, sighting s2 " + 
				"where s1.country='us' " + 
				"and s2.country='us' " + 
//				"and s1.state<>s2.state " +
				"and s1.state=? " + 
				"and s2.state=? " + 
				"and year(s1.datetime)=? " + 
				"and year(s1.datetime)=year(s2.datetime) " + 
				"and s2.datetime>s1.datetime" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1, s1);
			st.setString(2, s2);
			st.setInt(3, anno.getValue());
					
			ResultSet res = st.executeQuery();
			res.first();
			
//			while(res.next()) {
				
				int risultato = res.getInt("c");
				
				conn.close();

				
				if(risultato==0) 
					return false;
				else 
					return true;
					
//			}
				
//			conn.close();
//			return false;


		} catch (SQLException e) {
			e.printStackTrace();
		return false;
		}
}




public List<StringPair> getEdges(Year anno) {
	String sql = "select s1.* as state1 , s2.state as state2, count(*) as cnt" + 
			"from sighting s1, sighting s2 " + 
			"where year(s1.datetime)=year(s2.datetime) " + 
			"and year(s1.datetime)=? " + 
			"and s1.country='us' " + 
			"and s2.country='us' " + 
			"and s2.datetime>s1.datetime " + 
			"and s1.state<>s2.state " + 
			"group by s1.state, s2.state " ;
	
	try {
		Connection conn = DBConnect.getConnection() ;
		PreparedStatement st = conn.prepareStatement(sql) ;
		
		st.setInt(1, anno.getValue());
		
		ResultSet res = st.executeQuery() ;
		
		List<StringPair>list = new ArrayList<>() ;
		while(res.next()) {
			list.add(new StringPair(res.getString("state1"), res.getString("state2"), res.getInt("cnt"))) ;
		}
		conn.close();
		return list ;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null ;
	}
	
}


//numero di avvistamenti di uno stato in un certo anno
public int getNumAvvistamenti(Year anno, String idStato) {
	
	String sql = "select count(sighting.id) as cnt\r\n" + 
			"from sighting , state\r\n" + 
			"where sighting.state=state.id \r\n" + 
			"and sighting.state=?\r\n" + 
			"and year(sighting.datetime)=?\r\n" + 
			"group by sighting.state";

	try {
		Connection conn = DBConnect.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		st.setString(1, idStato);
		st.setInt(2, anno.getValue());
				
		ResultSet res = st.executeQuery();
		res.first();
		
//		while(res.next()) {
			
			int risultato = res.getInt("cnt");
			
			conn.close();

			return risultato;
//			if(risultato==0) 
//				return false;
//			else 
//				return true;
				
//		}
			
//		conn.close();
//		return false;


	} catch (SQLException e) {
		e.printStackTrace();
	return 0;
	}
}
	
//numero di stati vicini di uno stato
public int getNumVicini (String idStato) {
	String sql = "select count(*) as cnt\r\n" + 
			"from neighbor\r\n" + 
			"where neighbor.state1=?\r\n" + 
			"group by neighbor.state1";

	try {
		Connection conn = DBConnect.getConnection() ;

		PreparedStatement st = conn.prepareStatement(sql) ;
		st.setString(1, idStato);
				
		ResultSet res = st.executeQuery();
		res.first();
		
//		while(res.next()) {
			
			int risultato = res.getInt("cnt");
			
			conn.close();

			return risultato;
//			if(risultato==0) 
//				return false;
//			else 
//				return true;
				
//		}
			
//		conn.close();
//		return false;


	} catch (SQLException e) {
		e.printStackTrace();
	return 0;
	}
}

}
