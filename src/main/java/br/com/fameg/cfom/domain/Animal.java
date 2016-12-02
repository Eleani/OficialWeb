package br.com.fameg.cfom.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Animal implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 50)
	private String nome;

	@Temporal(TemporalType.DATE)
	private Date dataNasc;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Raca raca;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente dono;

	@Column(length = 100)
	private String observacao;

	public Animal(Long id, String nome, Date dataNasc, Raca raca, Cliente dono, String observacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.raca = raca;
		this.dono = dono;
		this.observacao = observacao;
	}

	public Animal() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Cliente getDono() {
		return dono;
	}

	public void setDono(Cliente dono) {
		this.dono = dono;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
