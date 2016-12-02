package br.com.fameg.cfom.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Data;

import br.com.fameg.cfom.dao.CargoDAO;
import br.com.fameg.cfom.dao.EnderecoDAO;
import br.com.fameg.cfom.dao.EspecieDAO;
import br.com.fameg.cfom.dao.FuncionarioDAO;
import br.com.fameg.cfom.dao.RacaDAO;
import br.com.fameg.cfom.dao.TelefoneDAO;
import br.com.fameg.cfom.domain.Cargo;
import br.com.fameg.cfom.domain.Endereco;
import br.com.fameg.cfom.domain.Especie;
import br.com.fameg.cfom.domain.Funcionario;
import br.com.fameg.cfom.domain.Raca;
import br.com.fameg.cfom.domain.Telefone;

public class FuncionarioDAOTest {

	@Test
	public void salvar() throws ParseException {

		Session factory = HibernateUtil.getFabricaDeSessoes().openSession();
		
		Cargo cargo = new Cargo();
		cargo.setNome("Teste");
		CargoDAO cargoDAO = new CargoDAO();
		cargoDAO.save(cargo);
		
		Endereco e = new Endereco();
		e.setBairro("X");
		e.setCep("1");
		e.setCidade("x");
		e.setComplemento("ta!");
		e.setEstado("e");
		e.setNomeRua("teste");
		e.setPais("teste");
		
		Telefone t = new Telefone();
		
		/*
		Funcionario f = new Funcionario();
		f.setCargo(cargo);
		f.setCpf("123");
		f.setDataAdmissao(new Date());
		f.setEmail("teste");
		f.set
		
		
		CargoDAO cDAO = new CargoDAO();

		Cargo c1 = new Cargo();
		c1.setNome("Caixa");
		cDAO.save(c1);
		
		Cargo c = cDAO.getOn(1L);

		System.out.println(c.toString());
		
		Funcionario f = new Funcionario();
		EnderecoDAO eDAO = new EnderecoDAO();
		
		Endereco e = new Endereco();
		e.setNomeRua("Teste Rua");
		
		eDAO.save(e);
		TelefoneDAO tDAO = new TelefoneDAO();
		Telefone t = new Telefone();
		t.setResidencial("1234-1234");
		
		tDAO.save(t);
		
		
		
		
		f.setNome("Testando");
		f.setSobrenome("Sobrenome");
		f.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("27/11/2016"));
		f.setCargo(c);
		Telefone t1 = tDAO.getOn(1L);
		Endereco e1 = eDAO.getOn(1L);
		f.setTelefone(t1);
		f.setEndereco(e1);
		f.setEmail("teste@teste.com");
		f.setRg("123456");
		f.setCpf("987654");
		f.setLogin("roor");
		f.setSenha("123");

		
		
		FuncionarioDAO fDAO = new FuncionarioDAO();
		fDAO.save(f);
		*/

	}

}
