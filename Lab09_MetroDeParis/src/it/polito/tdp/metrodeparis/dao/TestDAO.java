package it.polito.tdp.metrodeparis.dao;

import java.util.List;

import it.polito.tdp.metrodeparis.model.Collegamento;
import it.polito.tdp.metrodeparis.model.Fermata;

public class TestDAO {

	public static void main(String[] args) {
		
		MetroDAO metroDAO = new MetroDAO();
		
		System.out.println("Lista fermate");
		List<Fermata> fermate = metroDAO.getAllFermate();
		System.out.println(fermate);
		
		System.out.println("Lista collegamenti");
		List<Collegamento> collegamenti = metroDAO.getTuttiCollegamenti();
		System.out.println(collegamenti);
		
	}

}
