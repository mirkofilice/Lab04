package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Studente;

public class TestStudenteDAO {

	public static void main(String[] args) {
		
		StudenteDAO sdao=new StudenteDAO();
		Studente s=sdao.getNome(146101);
		System.out.println(s);

	}

}
