package ar.edu.unju.fi.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceimp implements AlumnoService{

	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Override
	public void guardarAlumno(AlumnoDTO alumnoDTO) {
		// TODO Auto-generated method stub
		alumnoRepository.save(alumnoMapDTO.convertirAlumnoDTOAAlumno(alumnoDTO));
	}

	@Override
	public List<Alumno> mostrarAlumno() {
		// TODO Auto-generated method stub
		return alumnoRepository.findAll();
	}

	@Override
	public void borrarAlumno(String codigo) {
		// TODO Auto-generated method stub
		List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
		for (int i = 0 ; i < todosLosAlumnos.size();i++) {
			Alumno alumno = todosLosAlumnos.get(i);
			if(alumno.getLU().equals(codigo)) {
				alumnoRepository.delete(alumno);
			}
		}
	}

	@Override
	public void modificarAlumno(AlumnoDTO alumnoMod) {
		// TODO Auto-generated method stub
		alumnoMapDTO.convertirAlumnoDTOAAlumno(alumnoMod);
		AlumnoDTO alumnoExistente = this.buscarAlumno(alumnoMod.getLU());
		if(	alumnoExistente != null){
			alumnoExistente.setNombre(alumnoMod.getNombre());
	        alumnoExistente.setApellido(alumnoMod.getApellido());
	        alumnoExistente.setDni(alumnoMod.getDni());
	        alumnoExistente.setDomicilio(alumnoMod.getDomicilio());
	        alumnoExistente.setEmail(alumnoMod.getEmail());
	        alumnoExistente.setFechaNacimiento(alumnoMod.getFechaNacimiento());
	        alumnoExistente.setTelefono(alumnoMod.getTelefono());              
	        alumnoRepository.save(alumnoMapDTO.convertirAlumnoDTOAAlumno(alumnoExistente));
		}
		
	}

	@Override
	public AlumnoDTO buscarAlumno(String codigo) {
		// TODO Auto-generated method stub
		List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
		for (int i = 0 ; i < todosLosAlumnos.size();i++) {
			Alumno alumno = todosLosAlumnos.get(i);
			if(alumno.getLU().equals(codigo)){
				alumno.setFechaNacimiento(todosLosAlumnos.get(i).getFechaNacimiento());
				return alumnoMapDTO.convertirAlumnoAAlumnoDTO(alumno);
			}
		}
		return null;
	}
	

}
