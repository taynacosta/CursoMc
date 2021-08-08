package com.costatayna.mc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.costatayna.mc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	//add sete dias 
	public void preencerPagamentoCompleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

}
