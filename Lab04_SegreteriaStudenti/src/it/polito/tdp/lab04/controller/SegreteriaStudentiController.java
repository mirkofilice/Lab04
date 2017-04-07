package it.polito.tdp.lab04.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	List<Corso> listaCorsi = new LinkedList<Corso>();

	@FXML
	private ComboBox<Corso> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
		this.model=model;
		listaCorsi.addAll(model.getListaCorsi());
		listaCorsi.add(new Corso("",0,"",0));
		comboCorso.getItems().addAll(listaCorsi);
	}

	@FXML
	void doReset(ActionEvent event) {
		comboCorso.getSelectionModel().clearSelection();
		txtMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
		txtResult.clear();
	}

	@FXML
	void doCercaNome(ActionEvent event) {
		
		String mat=txtMatricola.getText();
		if(mat.matches("[0-9]*") && mat.trim().length()==6){
			
			int matricola=Integer.parseInt(mat);
			Studente s=model.trovaStudente(matricola);
			if (s!=null ){
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
			}
			else {
				txtResult.setText("Errore! La matricola inserita non è presente nel DB!");
				return;
			}
		}
		else {
			txtResult.setText("Errore! Il campo matricola non può contenere caratteri, e la dim deve essere 6!");
			return;
		}
		
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {

		Corso corso=comboCorso.getValue();
		List<Studente>iscritti=new LinkedList<Studente>(model.cercaIscritti(corso));
		if (iscritti.isEmpty()){
			txtResult.setText("Questo corso non ha iscritti");
		}
		else{
			String s="";
			for (Studente st:iscritti){
				s+=st+"\n";
			}
			txtResult.setText(s);
		}
		
		
	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		
		String mat=txtMatricola.getText();
		if(mat.matches("[0-9]*") && mat.trim().length()==6){
			
			int matricola=Integer.parseInt(mat);
			List <Corso> corsi= new LinkedList<Corso>(model.corsiStudente(matricola));
			if(corsi.isEmpty()){
				txtResult.setText("Questo studente non è iscritto ad alcun corso");
			}
			else{
				String s="";
				for(Corso co:corsi){
					s+=co.descriviCorso()+"\n";	
				}
				txtResult.setText(s);
			}					
		}
		else {
			txtResult.setText("Errore! Il campo matricola non può contenere caratteri, e la dim deve essere 6!");
			return;
		}
		
	}
		
	@FXML
	void doIscrivi(ActionEvent event) {

		Corso c=comboCorso.getValue();
		Studente s=new Studente(Integer.parseInt(txtMatricola.getText()), txtNome.getText(), txtCognome.getText(), "");
		if(model.iscritto(s, c)==true){
			txtResult.setText("Studente gia' iscritto a questo corso");
		}
		else{
			boolean flag=model.iscrivi(s, c);
			if (flag==true){
				txtResult.setText("Studente iscritto correttamente a questo corso");
			}
			else{
				txtResult.setText("Iscrizione non andata a buon fine");
				return;
			}
		}
		
	}

	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	}

}
