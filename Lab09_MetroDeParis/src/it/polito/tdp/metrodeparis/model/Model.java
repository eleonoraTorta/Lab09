package it.polito.tdp.metrodeparis.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

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
	double shortestPathTempoTotale = -1;
	private List <Collegamento> shortestPathEdgeList = null;
	
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

	
	public List <Fermata> calcolaPercorsoMinimo(Fermata partenza, Fermata arrivo) {
		
		DijkstraShortestPath<Fermata, Collegamento> dijkstra = new DijkstraShortestPath<Fermata, Collegamento>(grafo, partenza, arrivo);

		shortestPathEdgeList = dijkstra.getPathEdgeList();
		shortestPathTempoTotale = dijkstra.getPathLength();					//ritorna la somma dei pesi degli archi

		if (shortestPathEdgeList == null)
			throw new RuntimeException("Non è stato possiible crare un percorso.");

		// Nel calcolo del tempo non tengo conto della prima e dell'ultima fermata
		if (shortestPathEdgeList.size() - 1 > 0) {									//se il percorso e` formato da piu` di 1 fermata
			shortestPathTempoTotale = shortestPathTempoTotale + (shortestPathEdgeList.size() - 1) * 30;
		}
		
		// dagli archi ottengo le fermate (in ordine)
		List <Fermata> percorso = new LinkedList <Fermata>();
		for (Collegamento c : shortestPathEdgeList) {
			percorso.add(grafo.getEdgeTarget(c));
		}
		return percorso;
	}

	
	public double getTempoInSecondi(){
		if (shortestPathEdgeList == null)
			throw new RuntimeException("Non è stato creato alcun percorso.");
		return shortestPathTempoTotale;
	}
	
	// Converto il tempo da double a formato ora
	
	public String getTempoTotale(double shortestPathTempoTotale) {
		
		if (shortestPathEdgeList == null)
			throw new RuntimeException("Non è stato creato alcun percorso.");
		
		int tempoSecondi = (int) shortestPathTempoTotale;
		
		int ore = tempoSecondi / 3600;
		int minuti = (tempoSecondi% 3600) /60;			//% restituisce il RESTO della divisione
		int secondi = tempoSecondi % 60;
		
		return String.format("%02d:%02d:%02d", ore, minuti, secondi);
	}
	
	
	// opzione 2 ?
	/*	
	public Set <Fermata> calcolaCamminoMinimo(Fermata partenza, Fermata arrivo){
		
		DijkstraShortestPath<Fermata,Collegamento> dsp = new DijkstraShortestPath<Fermata,Collegamento>(this.getGrafo(), partenza, arrivo);
		ArrayList <Collegamento> archi = (ArrayList<Collegamento>) dsp.findPathBetween(grafo, partenza, arrivo);
		
		mappa = new LinkedHashMap <Fermata, Fermata>(); 
		
		mappa.put(archi.get(0).getPartenza(),null);
		for(Collegamento c : archi){
			mappa.put(c.getArrivo(), c.getPartenza());
		}
	
		return mappa.keySet();
	}
	*/
	
}


