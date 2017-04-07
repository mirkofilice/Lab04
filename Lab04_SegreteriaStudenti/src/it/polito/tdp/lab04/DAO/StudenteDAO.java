package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getNome(int matricola){
		
		final String sql= "SELECT * "+
					"FROM studente "+
					"WHERE matricola= ?";
		
		Studente result=null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Studente stemp=new Studente(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getString(4));
				result=stemp;
			}
			
			//conn.close();
			return result;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	public Collection <Corso> getCorsiDelloStudente(/*Studente studente*/int matricola){
		
		//int matricola=studente.getMatricola();
		
		final String sql="SELECT corso.codins, crediti, nome, pd "+
						 "FROM iscrizione, corso "+
						 "WHERE iscrizione.codins=corso.codins AND matricola=?";
		
		List<Corso> corsi= new LinkedList<Corso>();
		Corso corso=null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Corso ctemp=new Corso(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				corso=ctemp;
				corsi.add(corso);
				
			}
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	public boolean isIscritto(int matricola, String codins){
		
		final String sql= "SELECT studente.matricola, nome, cognome, CDS "+
				"FROM iscrizione, studente "+
				"WHERE iscrizione.matricola=studente.matricola AND iscrizione.matricola=? AND codins=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola);
			st.setString(2, codins);
			ResultSet rs = st.executeQuery();

			if (rs.next())
				return true;
			else 
				return false;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
	}


}
