package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;
import it.polito.tdp.metrodeparis.model.Collegamento;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Linea;

public class MetroDAO {

	public List<Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			//Connection conn = DBConnect.getInstance().getConnection();
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}
	
	public List <Collegamento> getTuttiCollegamenti(){
		
		final String sql = "SELECT partenza.id_fermata as id1, partenza.nome as nome1, partenza.coordX as coordX1, partenza.coordY as coordY1, "+
				"arrivo.id_fermata as id2, arrivo.nome as nome2, arrivo.coordX as coordX2, arrivo.coordY as coordY2, "+
				"linea.id_linea as linea, connessione.id_connessione as connessione, "+
				"linea.nome as nome, linea.velocita as velocita, linea.intervallo as intervallo, linea.colore as colore " +
				"FROM connessione, fermata partenza, fermata arrivo, linea "+
				"WHERE connessione.id_stazP = partenza.id_fermata "+
				"AND connessione.id_stazA = arrivo.id_fermata "+
				"AND connessione.id_linea = linea.id_linea";  
			
		try{
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery();
			
			List <Collegamento> list = new ArrayList <>();
			
			while(res.next()){
				
				int connessione = res.getInt("connessione");
				Linea linea = new Linea(res.getInt("linea"));
				linea.setNome(res.getString("nome"));
				linea.setVelocita(res.getDouble("velocita"));
				linea.setIntervallo(res.getDouble("intervallo"));
				linea.setColore(res.getString("colore"));
				
				LatLng coord1 = new LatLng (res.getDouble("coordX1"), res.getDouble("coordY1"));
				Fermata partenza = new Fermata (res.getInt("id1"), res.getString("nome1"), coord1);
				
				LatLng coord2 = new LatLng (res.getDouble("coordX2"), res.getDouble("coordY2"));
				Fermata arrivo = new Fermata (res.getInt("id2"), res.getString("nome2"), coord2);
				
				Collegamento c = new Collegamento (connessione,linea, partenza, arrivo);
				c.setPeso();
				list.add(c);
				
			}
			
			res.close();
			conn.close();
			
			return list;
		
		} catch (SQLException e){
			//TODO Auto=generated catch block
			e.printStackTrace();
			return null;
			
		}
		
	}
	}
	
	

