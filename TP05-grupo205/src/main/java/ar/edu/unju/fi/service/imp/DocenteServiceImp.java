 package ar.edu.unju.fi.service.imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService {

	@Autowired 
	DocenteMapDTO docenteMapDTO;
	
	@Autowired
	DocenteRepository docenteRepository;
	
	
	@Override
	public List<DocenteDTO> MostrarDocente() {
		
		return docenteMapDTO.toDocenteDTOList(docenteRepository.findDocenteByEstado(true));
	
	}

	@Override
	public void save(DocenteDTO docenteDTO) {
		docenteDTO.setEstadoDTO(true);
		docenteRepository.save(docenteMapDTO.toDocente(docenteDTO));
	}

	@Override
	public void deleteByLegajo(String legajo) {
		// TODO Auto-generated method stub
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		for (int i = 0; i < todosLosDocentes.size(); i++) {
		      Docente docente = todosLosDocentes.get(i);
		      if (docente.getLegajo().equals(legajo)) {
		        docente.setEstado(false);
		        docenteRepository.save(docente);
		        break;
		      }
		    }
	}

	@Override
	public void edit(DocenteDTO docenteDTO) {
		// TODO Auto-generated method stub
		docenteMapDTO.toDocente(docenteDTO);
		DocenteDTO docenteExistente = this.buscaDocente(docenteDTO.getLegajoDTO());	
			if(	docenteExistente != null){
				docenteExistente.setApellidoDTO(docenteDTO.getApellidoDTO());
				docenteExistente.setEmailDTO(docenteDTO.getEmailDTO());
				docenteExistente.setEstadoDTO(docenteDTO.getEstadoDTO());
				docenteExistente.setLegajoDTO(docenteDTO.getLegajoDTO());
				docenteExistente.setNombreDTO(docenteDTO.getNombreDTO());
				docenteExistente.setTelefonoDTO(docenteDTO.getTelefonoDTO());
				docenteRepository.save(docenteMapDTO.toDocente(docenteExistente));
		}
		
		
	}

	@Override
	public DocenteDTO buscaDocente(String legajo) {
		List<Docente> todosLasCarreras = docenteRepository.findAll();
		for (int i = 0 ; i < todosLasCarreras.size();i++) {
			Docente docente = todosLasCarreras.get(i);
			if(docente.getLegajo().equals(legajo)){
				return docenteMapDTO.toDocenteDTO(docente);
			}
		}
		return null;
	}

	@Override
	public void darDeAlta(String legajo) {
		// TODO Auto-generated method stub
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		for (int i = 0; i < todosLosDocentes.size(); i++) {
		      Docente docente = todosLosDocentes.get(i);
		      if (docente.getLegajo().equals(legajo)) {
		        docente.setEstado(true);
		        docenteRepository.save(docente);
		        break;
		      }
		    }
	}

	@Override
	public List<DocenteDTO> MostrarDocenteInactivos() {
		// TODO Auto-generated method stub
		return docenteMapDTO.toDocenteDTOList(docenteRepository.findDocenteByEstado(false));
	}

	@Override
	public void deletDefinitiveeByLegajo(String legajo) {
		// TODO Auto-generated method stub
	    Docente docente = docenteRepository.findById(legajo).orElse(null);
	    
	    if (docente != null) {
	        docenteRepository.delete(docente);
	    } 
	}




	
	
	
	
	
}