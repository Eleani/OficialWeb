package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.EspecieDAO;
import br.com.fameg.cfom.domain.Especie;

@ManagedBean
@ViewScoped
public class EspecieBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Especie especie;
	private EspecieDAO eDAO;
	private List<Especie> especies;

	public EspecieBean() {
		especie = new Especie();
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public EspecieDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(EspecieDAO eDAO) {
		this.eDAO = eDAO;
	}

	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}

	@PostConstruct
	public void listar() {
		try {
			EspecieDAO especieDAO = new EspecieDAO();
			especies = especieDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}

	public void novo() {
		especie = new Especie();
	}

	public void salvar() {

		try {
			EspecieDAO especieDAO = new EspecieDAO();
			especieDAO.merge(especie);

			especie = new Especie();
			novo();
			especies = especieDAO.getAll();

			Messages.addGlobalInfo("Estado salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			especie = (Especie) evento.getComponent().getAttributes().get("especieSelecionado");

			EspecieDAO especieDAO = new EspecieDAO();
			especieDAO.delete(especie);

			especies = especieDAO.getAll();

			Messages.addGlobalInfo("Especie removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		especie = (Especie) evento.getComponent().getAttributes().get("especieSelecionado");
	}

}
