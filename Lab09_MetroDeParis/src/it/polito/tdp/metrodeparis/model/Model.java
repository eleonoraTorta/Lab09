package it.polito.tdp.metrodeparis.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.WeightedMultigraph;


import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	private MetroDAO mDao;
	private WeightedGraph <Fermata,Collegamento> grafo;
	private List <Fermata> fermate;
	private Map <Fermata, Fermata> mappa;
	private double durata;
	
	public Model(){
		mDao = new MetroDAO();
	}
	
	public List<Fermata> getFermate(){
		
		if(this.fermate==null){
			this.fermate = mDao.getAllFermate();
		}
		return this.fermate;
	}
	
	public WeightedGraph <Fermata, Collegamento> getGrafo(){
		if(this.grafo==null){
			this.creaGrafo();
		}
		return this.grafo;
	}
	
	public void creaGrafo(){
		
		grafo = new  WeightedMultigraph  <Fermata, Collegamento>(Collegamento.class);
	
		// crea i vertici del grafo
			Graphs.addAllVertices(grafo, this.getFermate()) ;
			System.out.println(grafo.vertexSet());
				
		// crea gli archi del grafo e ci assegna il peso
			for(Collegamento c : mDao.getTuttiCollegamenti()) {
	
				grafo.addEdge(c.getPartenza(), c.getArrivo(), c) ;
				grafo.setEdgeWeight(c,c.getPeso());
			}
	
	}
	
	public Set <Fermata> calcolaCamminoMinimo(Fermata partenza, Fermata arrivo){
		
		DijkstraShortestPath<Fermata,Collegamento> dsp = new DijkstraShortestPath<Fermata,Collegamento>(this.getGrafo(), partenza, arrivo);
		ArrayList <Collegamento> archi = (ArrayList<Collegamento>) dsp.findPathBetween(grafo, partenza, arrivo);
		
		mappa = new HashMap <Fermata, Fermata>(); 
		
		mappa.put(archi.get(0).getPartenza(),null);
		for(Collegamento c : archi){
			mappa.put(c.getArrivo(), c.getPartenza());
		}
		
		durata = dsp.getPathLength();
		return mappa.keySet();
	}
	
	public double getDurata(){
		return durata;
	}
	

}
