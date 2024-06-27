package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Service
public interface CarreraService {

		public void guardarCarrera(CarreraDTO carreraDTO);
		public List<Carrera> MostrarCarrera();
		public void borrarCarrera(String codigo);
		public void darDeAltaCarrera(String codigo);
		public void modificarCarrera(CarreraDTO carreraDTO);
		public Carrera buscaCarrera(String codigo);
		
}