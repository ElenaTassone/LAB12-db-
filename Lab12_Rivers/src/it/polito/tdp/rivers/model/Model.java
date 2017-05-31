package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.*;

import it.polito.tdp.rivers.DB.RiverDAO;

public class Model {

	private List<River> rivers ; 
	private List<Flow> flowsDatoRiver;
	private RiverDAO dao ;



	public Model (){
		
		this.dao = new RiverDAO() ;
	}
	
	public List<River> getAllRivers (){
		if(this.rivers== null)
			return dao.getAllRivers() ;
		else{
			this.rivers = new ArrayList<River> () ;
			return this.rivers ;
		}
			
	}
	
	public void infoRiver (River river){
		this.flowsDatoRiver= new ArrayList<Flow> () ;
		flowsDatoRiver = dao.getInfoRiver(river) ;
		
	}
	
	public LocalDate getPrimaData(){
		return flowsDatoRiver.get(0).getDay() ;
	}
	
	public LocalDate getUltimaData(){
		return flowsDatoRiver.get(flowsDatoRiver.size()-1).getDay() ;
	}
	
	public int getMisurazioni(){
		return this.flowsDatoRiver.size() ;
	}
	
	public double getMedia(){
		double tot = 0 ;
		for(Flow f : flowsDatoRiver){
			tot+=f.getFlow() ;
		}
		return tot/this.flowsDatoRiver.size() ;
	}
	
	public List<Flow> getInfoRiver(){
		
		return this.flowsDatoRiver ;
	}
	
	
	
	
}