package br.com.fameg.cfom.util;

import org.junit.Ignore;
import org.junit.Test;
import java.util.List;

import br.com.fameg.cfom.dao.EspecieDAO;
import br.com.fameg.cfom.dao.RacaDAO;
import br.com.fameg.cfom.domain.Especie;
import br.com.fameg.cfom.domain.Raca;

public class RacaDAOTest {

	@Test
	@Ignore
	public void salvar(){
		EspecieDAO especieDAO = new EspecieDAO();
		
		Especie especie = especieDAO.getOn(2L);
		
		Raca raca = new Raca();
		raca.setNome("Marília");
		raca.setEspecie(especie);
		
		RacaDAO racaDAO = new RacaDAO();
		racaDAO.save(raca);
	}
	
	
	@Test
	public void listar() {
		RacaDAO racaDAO = new RacaDAO();
		List<Raca> resultado = racaDAO.getAll();

		for (Raca especie : resultado) {
			System.out.println("Código da Raca: " + especie.getId());
			System.out.println("Nome da Raca: " + especie.getNome());
			System.out.println("Código da Especie: " + especie.getEspecie().getId());
			System.out.println("Nome da Especie: " + especie.getEspecie().getNome());
			System.out.println();
		}
	}
	
}
