package it.polito.tdp.lab04.DAO;

import java.util.*;

import it.polito.tdp.lab04.model.Corso;

public class TestCorsoDAO {

	public static void main(String[] args) {
		
		List<Corso>corsi=new LinkedList<Corso>();
		CorsoDAO cdao=new CorsoDAO();
		corsi.addAll(cdao.getTuttiICorsi());
		for (Corso c: corsi){
			System.out.println(c.descriviCorso());
		}

	}

}
