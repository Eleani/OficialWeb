package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.ClienteDAO;
import br.com.fameg.cfom.dao.EnderecoDAO;
import br.com.fameg.cfom.dao.TelefoneDAO;
import br.com.fameg.cfom.domain.Cliente;
import br.com.fameg.cfom.domain.Endereco;
import br.com.fameg.cfom.domain.Telefone;


@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private ClienteDAO clienteDAO;
	private List<Cliente> clientes;
	private List<Telefone> telefones;
	private List<Endereco> enderecos;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {

		try {
			cliente = new Cliente();
			
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefones = telefoneDAO.getAll();
			
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecos = enderecoDAO.getAll();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova Cliente");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);

			cliente = new Cliente();
			novo();
			
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefones = telefoneDAO.getAll();
			
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecos = enderecoDAO.getAll();
			
			clientes = clienteDAO.getAll();
			
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.delete(cliente);

			clientes = clienteDAO.getAll();

			Messages.addGlobalInfo("Cliente removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o cliente");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		novo();
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
	}
}
