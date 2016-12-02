package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.CargoDAO;
import br.com.fameg.cfom.domain.Cargo;

@ManagedBean
@ViewScoped
public class CargoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Cargo cargo;
	private CargoDAO cargoDAO;
	private List<Cargo> cargos;

	public CargoBean() {
		cargo = new Cargo();
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public CargoDAO getCargoDAO() {
		return cargoDAO;
	}

	public void setCargoDAO(CargoDAO cargoDAO) {
		this.cargoDAO = cargoDAO;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	@PostConstruct
	public void listar() {
		try {
			CargoDAO cargoDAO = new CargoDAO();
			cargos = cargoDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os cargos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		cargo = new Cargo();
	}

	public void salvar() {

		try {
			CargoDAO cargoDAO = new CargoDAO();
			cargoDAO.merge(cargo);

			cargo = new Cargo();
			novo();
			cargos = cargoDAO.getAll();

			Messages.addGlobalInfo("Cargo salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cargo");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			cargo = (Cargo) evento.getComponent().getAttributes().get("cargoSelecionado");

			CargoDAO cargoDAO = new CargoDAO();
			cargoDAO.delete(cargo);

			cargos = cargoDAO.getAll();

			Messages.addGlobalInfo("Cargo removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o cargo");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		cargo = (Cargo) evento.getComponent().getAttributes().get("cargoSelecionado");
	}
	
}
