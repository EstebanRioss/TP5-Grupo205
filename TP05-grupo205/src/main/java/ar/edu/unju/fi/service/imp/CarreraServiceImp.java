package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService {
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Autowired
	CarreraRepository carreraRepository;

	@Override
	public void guardarCarrera(CarreraDTO carreraDTO) {
		// TODO Auto-generated method stub
		carreraDTO.setEstado(true);
		carreraRepository.save(carreraMapDTO.convertirCarreraDTOACarrera(carreraDTO));
	}

	@Override
	public List<Carrera> MostrarCarrera() {
		// TODO Auto-generated method stub
		return carreraRepository.findAll();
	}

	@Override
	public void borrarCarrera(String codigo) {
		// TODO Auto-generated method stub
		List<Carrera> todosLasCarreras = carreraRepository.findAll();
		for (int i = 0; i < todosLasCarreras.size(); i++) {
			Carrera carrera = todosLasCarreras.get(i);
			if (carrera.getCodigo().equals(codigo)) {
				carrera.setEstado(false);
				carreraRepository.save(carrera);
				break;
			}
		}
	}

	@Override
	public Carrera buscaCarrera(String codigo) {
		// TODO Auto-generated method stub
		return carreraRepository.findById(codigo).orElse(null);
	}

	@Override
	public void modificarCarrera(CarreraDTO carreraDTO) {
		// TODO Auto-generated method stub
		CarreraDTO carreraExistente = carreraMapDTO.convertirAcarreraDTO(this.buscaCarrera(carreraDTO.getCodigo()));
		if(	carreraExistente != null){
			carreraExistente.setCodigo(carreraDTO.getCodigo());
			carreraExistente.setDuracion(carreraDTO.getDuracion());
			carreraExistente.setNombre(carreraDTO.getNombre());
			carreraExistente.setEstado(true);
	        carreraRepository.save(carreraMapDTO.convertirCarreraDTOACarrera(carreraExistente));
		}
	}

	@Override
	public void darDeAltaCarrera(String codigo) {
		// TODO Auto-generated method stub
		List<Carrera> todosLasCarreras = carreraRepository.findAll();
		for (int i = 0; i < todosLasCarreras.size(); i++) {
			Carrera carrera = todosLasCarreras.get(i);
			if (carrera.getCodigo().equals(codigo)) {
				carrera.setEstado(true);
				carreraRepository.save(carrera);
				break;
			}
		}
	}
}