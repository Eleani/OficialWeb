package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.AnimalDAO;
import br.com.fameg.cfom.dao.ClienteDAO;
import br.com.fameg.cfom.dao.RacaDAO;
import br.com.fameg.cfom.domain.Animal;
import br.com.fameg.cfom.domain.Cliente;
import br.com.fameg.cfom.domain.Raca;

@ManagedBean
@ViewScoped
public class AnimalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Animal animal;
	private AnimalDAO animalDAO;
	private List<Animal> animals;
	private List<Cliente> donos;
	private List<Raca> racas;

	public AnimalBean() {
		animal = new Animal();
	}
	
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public AnimalDAO getAnimalDAO() {
		return animalDAO;
	}

	public void setAnimalDAO(AnimalDAO animalDAO) {
		this.animalDAO = animalDAO;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}

	public List<Cliente> getDonos() {
		return donos;
	}

	public void setDonos(List<Cliente> donos) {
		this.donos = donos;
	}

	public List<Raca> getRacas() {
		return racas;
	}

	public void setRacas(List<Raca> racas) {
		this.racas = racas;
	}

	@PostConstruct
	public void listar() {
		try {
			AnimalDAO animalDAO = new AnimalDAO();
			animals = animalDAO.getAll();
			
			ClienteDAO cargoDAO = new ClienteDAO();
			donos = cargoDAO.getAll();
			
			RacaDAO racaDAO = new RacaDAO();
			racas = racaDAO.getAll();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os animals");
			erro.printStackTrace();
		}
	}

	public void novo() {

		try {
			animal = new Animal();

			ClienteDAO clienteDAO = new ClienteDAO();
			donos = clienteDAO.getAll();
			
			RacaDAO racaDAO = new RacaDAO();
			racas = racaDAO.getAll();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo animal");
			erro.printStackTrace();
		}

	}

	public void salvar() {

		try {
			AnimalDAO animalDAO = new AnimalDAO();
			animalDAO.merge(animal);

			animal = new Animal();
			novo();
			
			ClienteDAO cargoDAO = new ClienteDAO();
			donos = cargoDAO.getAll();
			
			RacaDAO racaDAO = new RacaDAO();
			racas = racaDAO.getAll();
			
			animals = animalDAO.getAll();
			
			Messages.addGlobalInfo("Animal salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o animal");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			animal = (Animal) evento.getComponent().getAttributes().get("animalSelecionado");

			AnimalDAO animalDAO = new AnimalDAO();
			animalDAO.delete(animal);

			animals = animalDAO.getAll();

			ClienteDAO cargoDAO = new ClienteDAO();
			donos = cargoDAO.getAll();
			
			RacaDAO racaDAO = new RacaDAO();
			racas = racaDAO.getAll();
			
			Messages.addGlobalInfo("Animal removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o animal");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		novo();
		animal = (Animal) evento.getComponent().getAttributes().get("animalSelecionado");
	}
	
}
