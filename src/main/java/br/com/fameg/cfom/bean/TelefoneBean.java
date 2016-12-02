package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.TelefoneDAO;
import br.com.fameg.cfom.domain.Telefone;

@ManagedBean
@ViewScoped
public class TelefoneBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Telefone telefone;
	private TelefoneDAO telefoneDAO;
	private List<Telefone> telefones;

	public TelefoneBean() {
		telefone = new Telefone();
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public TelefoneDAO getTelefoneDAO() {
		return telefoneDAO;
	}

	public void setTelefoneDAO(TelefoneDAO telefoneDAO) {
		this.telefoneDAO = telefoneDAO;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@PostConstruct
	public void listar() {
		try {
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefones = telefoneDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}

	public void novo() {
		telefone = new Telefone();
	}

	public void salvar() {

		try {
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.merge(telefone);

			telefone = new Telefone();
			novo();
			telefones = telefoneDAO.getAll();

			Messages.addGlobalInfo("Telefone salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o telefone");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			telefone = (Telefone) evento.getComponent().getAttributes().get("telefoneSelecionado");

			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.delete(telefone);

			telefones = telefoneDAO.getAll();

			Messages.addGlobalInfo("Telefone removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o telefone");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		telefone = (Telefone) evento.getComponent().getAttributes().get("telefoneSelecionado");
	}
	
	
	
}
