package br.unigranrio.bean.requisito;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@Entity
@Table(name="requisitoNaoFuncional")
@XStreamAlias("requisitoNaoFuncional")
public class RequisitoNaoFuncional implements Serializable{

	private static final long serialVersionUID = 1L;
	@XStreamOmitField
	private Long id;
	private String codigo;
	private String descricao;
	@XStreamOmitField
	private List<CasoDeUsoRequisito> casosDeUsoRequisito;
	@XStreamOmitField
	private Projeto projeto;
	
	public RequisitoNaoFuncional() {
	}

	@Id
	@GeneratedValue
	@XmlElement(name= "requisito_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement(name= "requisito_descricao")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Projeto.class)
	@PrimaryKeyJoinColumn
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="pk.requisito", cascade=CascadeType.REMOVE)
	public List<CasoDeUsoRequisito> getCasoDeUsoRequisito() {
		return casosDeUsoRequisito;
	}

	public void setCasoDeUsoRequisito(List<CasoDeUsoRequisito> casosDeUsoRequisito) {
		this.casosDeUsoRequisito = casosDeUsoRequisito;
	}
	
}
