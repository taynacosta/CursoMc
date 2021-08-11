package com.costatayna.mc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.costatayna.mc.domain.Cidade;

public class CidadeDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	Integer id;
	
	@NotEmpty(message= "Preenhimento obrigatório")
	@Length(min= 5, max= 80, message= "o tamanho deve ser entre 5 e 80 caracteres")
	String nome;
	
	public CidadeDTO() {
	}
	
	public CidadeDTO(Cidade obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
