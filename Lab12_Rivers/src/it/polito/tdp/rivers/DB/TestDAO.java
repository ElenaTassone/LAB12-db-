package it.polito.tdp.rivers.DB;

import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

public class TestDAO {


	public static void main(String[] args) {
		RiverDAO dao = new RiverDAO();

		List<River> rivers = dao.getAllRivers();
		System.out.println(rivers);

		List<Flow> flows = dao.getAllFlows(rivers);
		System.out.format("Loaded %d flows\n", flows.size());
		// System.out.println(flows) ;
		River r =  new River (2, "Vatnsdalsa River");
		List<Flow> flow = dao.getInfoRiver(r);
		System.out.println(flow);
	}

}
