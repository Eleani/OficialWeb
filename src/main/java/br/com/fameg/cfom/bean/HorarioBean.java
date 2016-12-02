package br.com.fameg.cfom.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.fameg.cfom.dao.AgendaDAO;
import br.com.fameg.cfom.dao.HorarioDAO;
import br.com.fameg.cfom.dao.OrdemServicoDAO;
import br.com.fameg.cfom.dao.ServicoDAO;
import br.com.fameg.cfom.domain.Agenda;
import br.com.fameg.cfom.domain.Horario;
import br.com.fameg.cfom.domain.OrdemServico;
import br.com.fameg.cfom.domain.Servico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HorarioBean implements Serializable {

	private Horario horario;
	private List<Horario> horarios;
	private List<Agenda> agendas;
	private List<OrdemServico> ordemServicos;

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public List<OrdemServico> getOrdemServicos() {
		return ordemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}

	public void novo() {
		try {
			horario = new Horario();

			AgendaDAO agendaDAO = new AgendaDAO();
			agendas = agendaDAO.getAll();
			OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
			ordemServicos = ordemServicoDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma novo servico");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			HorarioDAO horaDAO = new HorarioDAO();
			horarios = horaDAO.getAll();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao listar os horarios");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		System.out.println("Teste");
		try {

			HorarioDAO horaDAO = new HorarioDAO();
			horaDAO.merge(horario);

			horario = new Horario();

			horarios = horaDAO.getAll();

			Messages.addGlobalInfo("Horario salvo");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("erro ao salvar a raça");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			horario = (Horario) evento.getComponent().getAttributes().get("horarioSelecionado");

			HorarioDAO horaDAO = new HorarioDAO();
			horaDAO.delete(horario);

			horarios = horaDAO.getAll();

			Messages.addGlobalInfo("Horario removido");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("erro ao remover o horario");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		novo();
		horario = (Horario) evento.getComponent().getAttributes().get("horarioSelecionado");
	}

}