package it.polito.tdp.rivers.DB;
import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RiverDAO {
	
	private Map <Integer, River> idRivers = new HashMap <Integer, River> () ;

	public List<River> getAllRivers() {
	final String sql = "SELECT id, name FROM river";

	List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				River temp = new River(res.getInt("id"), res.getString("name"));
				rivers.add(temp) ;
				idRivers.put(temp.getId(), temp) ;
			}

			conn.close();

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException();
			}

			return rivers;
		}

		public List<Flow> getAllFlows(List<River> rivers) {
		final String sql = "SELECT id, day, flow, river FROM flow";

		List<Flow> flows = new LinkedList<Flow>();

			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet res = st.executeQuery();

				while (res.next()) {
					flows.add(new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"),
							rivers.get(rivers.indexOf(new River(res.getInt("river"))))));
				}

				conn.close();

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException();
			}

			return flows;
		}
		
		public List<Flow> getInfoRiver (River river){
			final String sql = "SELECT day, flow, river FROM flow WHERE river=? ORDER BY day ASC ";
					
			List<Flow> flows = new LinkedList<Flow>();
			
			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, river.getId());
				ResultSet res = st.executeQuery();

				while (res.next()) {
					flows.add(new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"),
							idRivers.get(res.getInt("river"))));
					}
					
			}catch (SQLException e) {
				 e.printStackTrace();
				throw new RuntimeException();
			}
			
			return flows;
			
		}
}