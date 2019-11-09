package com.example.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.dao.IProfesorDao;
import com.example.rest.entity.Profesor;

@Service("profesorService")
public class ProfesorServiceImp implements IProfesorService {

	@Autowired
	private IProfesorDao profesorDao;
	
	@Override
	public List<Profesor> findAllProfesor() {
		return (List<Profesor>) profesorDao.findAll();
	}

}
