package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.EnderecoDAO;
import br.com.fameg.cfom.domain.Endereco;

@ManagedBean
@ViewScoped
public class EnderecoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Endereco endereco;
	private EnderecoDAO enderecoDAO;
	private List<Endereco> enderecos;

	public EnderecoBean() {
		endereco = new Endereco();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public EnderecoDAO getEnderecoDAO() {
		return enderecoDAO;
	}

	public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
		this.enderecoDAO = enderecoDAO;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@PostConstruct
	public void listar() {
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecos = enderecoDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os enderecos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		endereco = new Endereco();
	}

	public void salvar() {

		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.merge(endereco);

			endereco = new Endereco();
			novo();
			enderecos = enderecoDAO.getAll();

			Messages.addGlobalInfo("Endereco salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o endereco");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			endereco = (Endereco) evento.getComponent().getAttributes().get("enderecoSelecionado");

			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.delete(endereco);

			enderecos = enderecoDAO.getAll();

			Messages.addGlobalInfo("Endereco removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o endereco");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		endereco = (Endereco) evento.getComponent().getAttributes().get("enderecoSelecionado");
	}
	
}
