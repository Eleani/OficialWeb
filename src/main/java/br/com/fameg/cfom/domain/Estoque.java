package br.com.fameg.cfom.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
public class Estoque implements Serializable {

	@Id
    @GeneratedValue
    private Long id;
    private double quantidade;
    
    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal valorUniario;

	public Estoque() {
		super();
	}

	public Estoque(Long id, double quantidade, BigDecimal valorUniario) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valorUniario = valorUniario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUniario() {
		return valorUniario;
	}

	public void setValorUniario(BigDecimal valorUniario) {
		this.valorUniario = valorUniario;
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
        final Estoque other = (Estoque) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
