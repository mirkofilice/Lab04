package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				Corso ctemp=new Corso (rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				// Aggiungi il nuovo Corso alla lista
				corsi.add(ctemp);
			}

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public Collection<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		final String sql="SELECT iscrizione.matricola, nome, cognome, CDS "+
						 "FROM iscrizione, studente "+
						 "WHERE iscrizione.matricola=studente.matricola AND codins=?";
		
		List<Studente> studenti= new LinkedList<Studente>();
		Studente studente=null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso.getCodice());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Studente stemp=new Studente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				studente=stemp;
				studenti.add(studente);
				
			}
			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		
		final String sql="INSERT INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) "+
						 "VALUES (?, ?)";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodice());
			
			int result = st.executeUpdate() ;

			if(result==1) {
				return true ;
			} else {
				return false ;
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		//return false;
	}
}
