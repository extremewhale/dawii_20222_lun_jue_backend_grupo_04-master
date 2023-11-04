package com.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Sede;
import com.cibertec.repository.SedeRepository;

@Service
public class SedeServiceImp implements SedeService {

	@Autowired
	private SedeRepository Repository;

	@Override
	public List<Sede> listaTodos() {
		return Repository.findAll();

	}

}
