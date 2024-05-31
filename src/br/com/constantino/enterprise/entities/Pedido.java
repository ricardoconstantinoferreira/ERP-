package br.com.constantino.enterprise.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_pedido")
	private Calendar dataPedido;
	
	@Column(name="preco_total")
	private Double precoTotal;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="id_estabelecimento", referencedColumnName="id")
	})
	private Estabelecimento estabelecimento;
	
	@ManyToMany
	private List<Produto> produto;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;	
	}
	
	public Calendar getDataPedido() {
		return dataPedido;
	}
	
	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public Double getPrecoTotal() {
		return precoTotal;
	}
	
	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
