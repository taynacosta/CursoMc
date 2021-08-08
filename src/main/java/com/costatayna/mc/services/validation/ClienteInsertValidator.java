package com.costatayna.mc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.costatayna.mc.domain.enums.TipoCliente;
import com.costatayna.mc.dto.NewClienteDto;
import com.costatayna.mc.resources.exceptions.FildMessage;
import com.costatayna.mc.services.validation.utils.BR;



public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDto> {

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(NewClienteDto objDto, ConstraintValidatorContext context) {

		List<FildMessage> list = new ArrayList<>();

		if (objDto.getTipocliente().equals(TipoCliente.PESSOAFÍSICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FildMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipocliente().equals(TipoCliente.PESSOAJURÍDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FildMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		for (FildMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}