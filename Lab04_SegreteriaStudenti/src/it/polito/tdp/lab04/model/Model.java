package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	StudenteDAO sdao=new StudenteDAO();
	CorsoDAO cdao=new CorsoDAO();
	
	public Collection <Corso> getListaCorsi(){
		
		List <Corso> tuttiICorsi=new LinkedList<Corso>();
		tuttiICorsi.addAll(cdao.getTuttiICorsi());
		
		return tuttiICorsi;
	}
	
	public Studente trovaStudente(int matricola){		
		return sdao.getNome(matricola);
	}
	
	public Collection <Studente> cercaIscritti(Corso corso){
		
		List<Studente> studentiIscritti=new LinkedList<Studente>();
		studentiIscritti.addAll(cdao.getStudentiIscrittiAlCorso(corso));
		
		return studentiIscritti;		
	}
	
	public Collection<Corso> corsiStudente(int matricola){
		
		List<Corso>corsiStudente=new LinkedList<Corso>();
		corsiStudente.addAll(sdao.getCorsiDelloStudente(matricola));
		
		return corsiStudente;
	}
	
	public boolean iscrivi(Studente s, Corso c){
			return cdao.inscriviStudenteACorso(s, c);
	}
	
	public boolean iscritto(Studente s, Corso c){
		int matricola=s.getMatricola();
		String codins=c.getCodice();
		
		return sdao.isIscritto(matricola, codins);
	}

}
