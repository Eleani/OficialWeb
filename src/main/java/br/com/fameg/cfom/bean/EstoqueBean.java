package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.EstoqueDAO;
import br.com.fameg.cfom.domain.Estoque;

@ManagedBean
@ViewScoped
public class EstoqueBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Estoque estoque;
	private EstoqueDAO estoqueDAO;
	private List<Estoque> estoques;

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public EstoqueDAO getEstoqueDAO() {
		return estoqueDAO;
	}

	public void setEstoqueDAO(EstoqueDAO estoqueDAO) {
		this.estoqueDAO = estoqueDAO;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	@PostConstruct
	public void listar() {
		try {
			EstoqueDAO estoqueDAO = new EstoqueDAO();
			estoques = estoqueDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estoque");
			erro.printStackTrace();
		}
	}

	public void novo() {
		estoque = new Estoque();
	}

	public void salvar() {

		try {
			EstoqueDAO estoqueDAO = new EstoqueDAO();
			estoqueDAO.merge(estoque);

			estoque = new Estoque();
			novo();
			estoques = estoqueDAO.getAll();

			Messages.addGlobalInfo("Estoque salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estoque");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			estoque = (Estoque) evento.getComponent().getAttributes().get("estoqueSelecionado");

			EstoqueDAO estoqueDAO = new EstoqueDAO();
			estoqueDAO.delete(estoque);

			estoques = estoqueDAO.getAll();

			Messages.addGlobalInfo("Estoque removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estoque");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		estoque = (Estoque) evento.getComponent().getAttributes().get("estoqueSelecionado");
	}
	
}
