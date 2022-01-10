package com.devsuperior.clientchallenge.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.clientchallenge.dto.ClientDTO;
import com.devsuperior.clientchallenge.entities.Client;
import com.devsuperior.clientchallenge.exceptions.ResourceNotFoundExpressions;
import com.devsuperior.clientchallenge.repository.ClientRepository;
import com.devsuperior.clientchallenge.services.exceptions.DatabaseException;
import com.devsuperior.clientchallenge.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPage(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundExpressions("ID not found: " + id));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDTOToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	private void copyDTOToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setBirthdate(dto.getBirthdate());
		entity.setChildren(dto.getChildren());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
			Client entity = repository.getOne(id);
			copyDTOToEntity(clientDTO, entity);
			entity = repository.save(entity);
			return new ClientDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID " + id + " not found!");
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID " + id + " not found!");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
