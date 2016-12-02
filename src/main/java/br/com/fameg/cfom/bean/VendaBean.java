package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.ClienteDAO;
import br.com.fameg.cfom.dao.EnderecoDAO;
import br.com.fameg.cfom.dao.FuncionarioDAO;
import br.com.fameg.cfom.dao.ItemDAO;
import br.com.fameg.cfom.dao.ProdutoDAO;
import br.com.fameg.cfom.dao.TelefoneDAO;
import br.com.fameg.cfom.dao.VendaDAO;
import br.com.fameg.cfom.domain.Cliente;
import br.com.fameg.cfom.domain.Funcionario;
import br.com.fameg.cfom.domain.ItemVenda;
import br.com.fameg.cfom.domain.Venda;

@ManagedBean
@ViewScoped
public class VendaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Venda venda;
	private VendaDAO vendaDAO;
	private List<Venda> vendas;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<ItemVenda> itensVenda;
	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public VendaDAO getVendaDAO() {
		return vendaDAO;
	}
	public void setVendaDAO(VendaDAO vendaDAO) {
		this.vendaDAO = vendaDAO;
	}
	public List<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}	
	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	@PostConstruct
	public void listar() {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.getAll();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os vendas");
			erro.printStackTrace();
		}
	}

	public void novo() {

		try {
			venda = new Venda();
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.getAll();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.getAll();
			
			itensVenda = new ArrayList<>();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova Venda");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.merge(venda);

			venda = new Venda();
			novo();
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.getAll();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.getAll();
			
			ItemDAO itemDAO = new ItemDAO();
			itensVenda = itemDAO.getAll();
			
			vendas = vendaDAO.getAll();
			
			Messages.addGlobalInfo("Venda salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o venda");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			
			ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

			int achou = -1;
			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
				if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
					achou = posicao;
				}
			}

			if (achou > -1) {
				itensVenda.remove(achou);
			}

			Messages.addGlobalInfo("Item venda removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o item venda");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		novo();
		venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionado");
	}
	
	public void novoItem(ActionEvent evento) {
		novo();
		venda = (Venda) evento.getComponent().getAttributes().get("vendaSelecionado");
	}
	
}
