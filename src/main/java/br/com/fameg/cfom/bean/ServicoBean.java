package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.ServicoDAO;
import br.com.fameg.cfom.domain.Servico;

@ManagedBean
@ViewScoped
public class ServicoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Servico servico;
	private ServicoDAO servicoDAO;
	private List<Servico> servicos;

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public ServicoDAO getServicoDAO() {
		return servicoDAO;
	}

	public void setServicoDAO(ServicoDAO servicoDAO) {
		this.servicoDAO = servicoDAO;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public ServicoBean() {
		super();
	}

	@PostConstruct
	public void listar() {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os servicos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		servico = new Servico();
	}

	public void salvar() {

		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.merge(servico);

			servico = new Servico();
			novo();
			servicos = servicoDAO.getAll();

			Messages.addGlobalInfo("Servico salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o servico");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");

			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.delete(servico);

			servicos = servicoDAO.getAll();

			Messages.addGlobalInfo("Servico removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o servico");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");
	}
	
}
