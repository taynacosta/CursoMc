package com.costatayna.mc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.costatayna.mc.domain.Cidade;
import com.costatayna.mc.domain.Cliente;
import com.costatayna.mc.domain.Endereco;
import com.costatayna.mc.domain.enums.Perfil;
import com.costatayna.mc.domain.enums.TipoCliente;
import com.costatayna.mc.dto.ClienteDTO;
import com.costatayna.mc.dto.NewClienteDto;
import com.costatayna.mc.repositories.ClienteRepository;
import com.costatayna.mc.repositories.EnderecoRepository;
import com.costatayna.mc.security.UserSS;
import com.costatayna.mc.services.exceptions.AuthorizationException;
import com.costatayna.mc.services.exceptions.DataIntegridyException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	/*public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElse(null); 
	}*/
	
	public Cliente find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasHole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		try {
		find(id);
		repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegridyException("Não é possível excluir uma cliente que possui pedidos");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
		
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente fromDTO(@Valid NewClienteDto objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipocliente()), pe.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2()!=null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3()!=null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
}