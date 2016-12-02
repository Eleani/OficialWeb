package br.com.fameg.cfom.util;

import org.junit.Test;

import br.com.fameg.cfom.dao.EspecieDAO;
import br.com.fameg.cfom.domain.Especie;

public class EspecieDAOTest {

	@Test
	public void salvar(){
		Especie especie = new Especie();
		especie.setNome("Teste");
		
		EspecieDAO estadoDAO = new EspecieDAO();
		estadoDAO.save(especie);
	}
	
}
