package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Annotation;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import it.polito.tdp.ufo.model.Sighting;

public class SightingsDAO {
	
	public List<Sighting> getSightings() {
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
	
	
	public List<String> getStati(Year anno){
		
		String sql = "select distinct state " + 
				"from sighting " + 
				"where country='us' " + 
				"and year(datetime)=? " +
				"order by state asc";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno.getValue());
			
			List<String> stati = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			
			while(res.next()) {
				
				String stato = new String(res.getString("state"));
				
				stati.add(stato);
			}
				
			
			conn.close();
			return stati ;

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
}
	
	

