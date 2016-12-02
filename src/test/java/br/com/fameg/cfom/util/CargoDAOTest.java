package br.com.fameg.cfom.util;

import org.junit.Test;

import br.com.fameg.cfom.dao.AgendaDAO;
import br.com.fameg.cfom.dao.CargoDAO;
import br.com.fameg.cfom.domain.Agenda;
import br.com.fameg.cfom.domain.Cargo;

public class CargoDAOTest {

	@Test
	public void salvar(){
		
		AgendaDAO agendaDAO = new AgendaDAO();
		Agenda agenda = new Agenda();
		agenda.setId(1L);
		agendaDAO.delete(agenda);
		
	}
	
}
