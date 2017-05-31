package it.polito.tdp.rivers.model;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		Model m = new Model() ;
		List <River> rivers = m.getAllRivers();
		m.infoRiver(rivers.get(0));
		Simulator s = new Simulator(m);
		System.out.println(s.getCoda().toString()) ;

	}

}
