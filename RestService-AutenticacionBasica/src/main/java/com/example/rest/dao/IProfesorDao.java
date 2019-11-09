package com.example.rest.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.rest.entity.Profesor;

public interface IProfesorDao extends CrudRepository<Profesor, Long> {

}
