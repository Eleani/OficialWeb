package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.EspecieDAO;
import br.com.fameg.cfom.dao.RacaDAO;
import br.com.fameg.cfom.domain.Especie;
import br.com.fameg.cfom.domain.Raca;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RacaBean implements Serializable {

	private Raca raca;
	private List<Raca> racas;
	private List<Especie> especies;

	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public List<Raca> getRacas() {
		return racas;
	}

	public void setRacas(List<Raca> racas) {
		this.racas = racas;
	}

	public void novo() {
		try {
			raca = new Raca();

			EspecieDAO especieDAO = new EspecieDAO();
			especies = especieDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova Raca");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			RacaDAO racaDAO = new RacaDAO();
			racas = racaDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao listar as raças");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		System.out.println("Teste");
		try {
			
			RacaDAO racaDAO = new RacaDAO();
			racaDAO.merge(raca);
			
			raca = new Raca();
			
			EspecieDAO estadoDAO = new EspecieDAO();
			especies = estadoDAO.getAll();
			
			racas = racaDAO.getAll();
			
			Messages.addGlobalInfo("Raça salvo");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao salvar a raça");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			raca = (Raca) evento.getComponent().getAttributes().get("racaSelecionado");

			RacaDAO racaDAO = new RacaDAO();
			racaDAO.delete(raca);

			racas = racaDAO.getAll();

			Messages.addGlobalInfo("Raça removido");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("erro ao remover a raça");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		novo();
		raca = (Raca) evento.getComponent().getAttributes().get("racaSelecionado");
	}

}
