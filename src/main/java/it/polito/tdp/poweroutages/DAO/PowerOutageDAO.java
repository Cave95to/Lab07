package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<PowerOutages> getPowerOutages(Nerc nerc) {
		
		List<PowerOutages> blackout = new ArrayList<>();
		
		String sql = "SELECT id, date_event_began, date_event_finished, "
				+ "nerc_id, customers_affected "
				+ "FROM poweroutages "
				+ "WHERE nerc_id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
		
			st.setInt(1, nerc.getId());
			
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				
				PowerOutages p = new PowerOutages(res.getInt("id"),
						res.getTimestamp("date_event_began").toLocalDateTime(), 
						res.getTimestamp("date_event_finished").toLocalDateTime(), 
						res.getInt("nerc_id"), res.getInt("customers_affected"));
					
						
				blackout.add(p);
	
			}
			
			conn.close();
			return blackout ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	

}
