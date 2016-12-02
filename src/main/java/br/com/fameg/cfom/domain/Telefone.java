package br.com.fameg.cfom.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Telefone implements Serializable{

	@Id
    @GeneratedValue
    private Long id;
    @Column(length = 15)
    private String residencial;
    @Column(length = 15)
    private String celular;
    @Column(length = 15)
    private String celularSeg;
    @Column(length = 15)
    private String comercial;
	
    public Telefone() {
		super();
	}

	public Telefone(String residencial, String celular) {
		super();
		this.residencial = residencial;
		this.celular = celular;
	}

	public Telefone(String residencial, String celular, String celularSeg, String comercial) {
		super();
		this.residencial = residencial;
		this.celular = celular;
		this.celularSeg = celularSeg;
		this.comercial = comercial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResidencial() {
		return residencial;
	}

	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCelularSeg() {
		return celularSeg;
	}

	public void setCelularSeg(String celularSeg) {
		this.celularSeg = celularSeg;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
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
        final Telefone other = (Telefone) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
