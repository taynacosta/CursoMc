package com.costatayna.mc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.costatayna.mc.domain.Estado;

public class EstadoDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	Integer id;
	
	@NotEmpty(message= "Preenhimento obrigatório")
	@Length(min= 5, max= 80, message= "o tamanho deve ser entre 5 e 80 caracteres")
	String nome;
	
	public EstadoDTO() {
	}
	
	public EstadoDTO(Estado obj) {
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
