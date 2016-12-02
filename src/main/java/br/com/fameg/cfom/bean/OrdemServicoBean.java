package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.AnimalDAO;
import br.com.fameg.cfom.dao.FuncionarioDAO;
import br.com.fameg.cfom.dao.OrdemServicoDAO;
import br.com.fameg.cfom.dao.ServicoDAO;
import br.com.fameg.cfom.domain.Animal;
import br.com.fameg.cfom.domain.Funcionario;
import br.com.fameg.cfom.domain.OrdemServico;
import br.com.fameg.cfom.domain.Servico;

@ManagedBean
@ViewScoped
public class OrdemServicoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private OrdemServico ordemServico;
	private OrdemServicoDAO ordemServicoDAO;
	private List<OrdemServico> ordemServicos;
	private List<Animal> animais;
	private List<Funcionario> funcionarios;
	private List<Servico> servicos;
	
	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public OrdemServicoBean() {
		ordemServico = new OrdemServico();
	}
	
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public OrdemServicoDAO getOrdemServicoDAO() {
		return ordemServicoDAO;
	}

	public void setOrdemServicoDAO(OrdemServicoDAO ordemServicoDAO) {
		this.ordemServicoDAO = ordemServicoDAO;
	}

	public List<OrdemServico> getOrdemServicos() {
		return ordemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void listar() {
		try {
			OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
			ordemServicos = ordemServicoDAO.getAll();
			
			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.getAll();
			
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.getAll();
			
			FuncionarioDAO funcionariosDAO = new FuncionarioDAO();
			funcionarios = funcionariosDAO.getAll();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as ordemServico");
			erro.printStackTrace();
		}
	}

	public void novo() {

		try {
			ordemServico = new OrdemServico();
			
			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.getAll();
			
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.getAll();
			
			FuncionarioDAO funcionariosDAO = new FuncionarioDAO();
			funcionarios = funcionariosDAO.getAll();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova ordemServico");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {
			OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
			ordemServicoDAO.merge(ordemServico);

			ordemServico = new OrdemServico();
			novo();
			
			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.getAll();
			
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.getAll();
			
			FuncionarioDAO funcionariosDAO = new FuncionarioDAO();
			funcionarios = funcionariosDAO.getAll();
			
			ordemServicos = ordemServicoDAO.getAll();
			
			Messages.addGlobalInfo("Ordem Servico salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Ordem Servico");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ordemServico = (OrdemServico) evento.getComponent().getAttributes().get("ordemServicoSelecionado");

			OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
			ordemServicoDAO.delete(ordemServico);

			ordemServicos = ordemServicoDAO.getAll();
			
			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.getAll();
			
			ServicoDAO servicoDAO = new ServicoDAO();
			servicos = servicoDAO.getAll();
			
			FuncionarioDAO funcionariosDAO = new FuncionarioDAO();
			funcionarios = funcionariosDAO.getAll();
			
			Messages.addGlobalInfo("Ordem Servico removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a ordem servico");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		novo();
		ordemServico = (OrdemServico) evento.getComponent().getAttributes().get("ordemServicoSelecionado");
	}
	
}
