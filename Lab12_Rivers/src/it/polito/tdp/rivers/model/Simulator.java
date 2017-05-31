package it.polito.tdp.rivers.model;

import java.util.PriorityQueue;
import java.util.Queue;


public class Simulator {
	private Model model;
	private Queue <Flow> coda;
	private int giorniNonSoddisfatti;
	private double cTot ; 
	private double cMedia ;
	private int totGiorni ;
	private double k ; 
	
	public Simulator(Model m){
		this.model = m ;
		this.setCoda();
		this.giorniNonSoddisfatti=0;
		this.cMedia = 0 ;
		this.cTot = 0;
		 
		
	}
	
	public void setCoda(){
		this.coda= new PriorityQueue <Flow> () ;
		for(Flow f : model.getInfoRiver()){
			coda.add(f) ;
		}
		this.totGiorni = coda.size() ;
	}
	
	public Queue<Flow> getCoda(){
		return this.coda ;
	}
	
	public void run(double k ){
		this.k = k  ;
		double fmed = model.getMedia()*3600*24 ;
		double q = k*fmed*30;
		double c = q/2 ;
		double fin = 0.0 ;
		double fout = 0.0 ;
		double diff = 0.0 ;
				
		
		double prob = Math.random() ; 
		
		while(!coda.isEmpty()) {
			Flow f = coda.remove() ;
	
			fin = f.getFlow() ;
		
			if(prob>0.95){
				fout= 8*fmed ;
			}
			else{
				fout=0.8*fmed; //foutmin
			}
			
			diff = fin - fout ; 
			if(c+diff<0){
				c = 0 ;
				this.giorniNonSoddisfatti++;
			}
			else if (c+diff>q){
				c=q ; //tracimazione
			}
			else{
				c+=diff ; 
			}
				cTot+=c;
	
		}
	}	
	
	public double getMedia(){
		return cTot/totGiorni ;
	}
	
	
	public int getInsoddisfatti(){
		return this.giorniNonSoddisfatti ;
	}
	
	
}
