package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.AgendaDAO;
import br.com.fameg.cfom.dao.CargoDAO;
import br.com.fameg.cfom.dao.EnderecoDAO;
import br.com.fameg.cfom.dao.FuncionarioDAO;
import br.com.fameg.cfom.dao.TelefoneDAO;
import br.com.fameg.cfom.domain.Agenda;
import br.com.fameg.cfom.domain.Cargo;
import br.com.fameg.cfom.domain.Endereco;
import br.com.fameg.cfom.domain.Funcionario;
import br.com.fameg.cfom.domain.Telefone;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	private FuncionarioDAO funcionarioDAO;
	private List<Funcionario> funcionarios;
	private List<Cargo> cargos;
	private List<Telefone> telefones;
	private List<Endereco> enderecos;

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public FuncionarioBean() {
		funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FuncionarioDAO getFuncionarioDAO() {
		return funcionarioDAO;
	}

	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
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
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionarios");
			erro.printStackTrace();
		}
	}

	public void novo() {

		try {
			funcionario = new Funcionario();

			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.getAll();
			
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefones = telefoneDAO.getAll();
			
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecos = enderecoDAO.getAll();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova Raca");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			
			funcionarioDAO.merge(funcionario);
			
			System.out.println(funcionario);
			
			Funcionario f = new Funcionario();
			f = funcionario;
			f.setId(1L);
			
			AgendaDAO agendaDAO = new AgendaDAO();
			Agenda agenda = new Agenda();
			
			System.out.println(f);
			
			agenda.setFuncionario(f);
			
			System.out.println(agenda);
			
			agendaDAO.save(agenda);

			funcionario = new Funcionario();
			novo();
			
			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.getAll();
			
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefones = telefoneDAO.getAll();
			
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecos = enderecoDAO.getAll();
			
			funcionarios = funcionarioDAO.getAll();
			
			Messages.addGlobalInfo("Funcionario salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o funcionario");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.delete(funcionario);

			funcionarios = funcionarioDAO.getAll();

			Messages.addGlobalInfo("Cargo removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o cargo");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		novo();
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
	}

}
