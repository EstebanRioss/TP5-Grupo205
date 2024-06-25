package ar.edu.unju.fi.service.imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;
@Service
public class MateriaServiceImp implements MateriaService{
	@Autowired
	MateriaRepository materiaRepository;

	@Autowired
	MateriaMapDTO materiaMapDTO;

	@Override
	public void guardarMateria(MateriaDTO materiaParaGuardar) {
		// TODO Auto-generated method stub
		materiaParaGuardar.setEstado(true);
		materiaRepository.save(materiaMapDTO.toEntity(materiaParaGuardar));
	}

	@Override
	public List<Materia> mostrarMaterias() {
		// TODO Auto-generated method stub
		return materiaRepository.findAll();
	}

	@Override
	public void borrarMateria(String codigo) {
		// TODO Auto-generated method stub
		List<MateriaDTO> todosLasMaterias = materiaMapDTO.listMateriaToListMateriaDTO(materiaRepository.findAll());
		for (int i = 0 ; i < todosLasMaterias.size();i++) {
			MateriaDTO materia = todosLasMaterias.get(i);
			if(materia.getCodigo().equals(codigo)) {
				materia.setEstado(false);
				materiaRepository.save(materiaMapDTO.toEntity(materia));
			}
		}
	}

	@Override
	public void modificarMateria(MateriaDTO materia) {
		// TODO Auto-generated method stub
		MateriaDTO materiaExistente = this.buscarMateria(materia.getCodigo());
		if(	materiaExistente != null){
			materiaExistente.setCodigo(materia.getCodigo());
			materiaExistente.setCurso(materia.getCurso());
			materiaExistente.setEstado(true);
			materiaExistente.setModalidad(materia.getModalidad());
			materiaExistente.setCantidadHoras(materia.getCantidadHoras());
			materiaExistente.setNombre(materia.getNombre());
			materiaRepository.save(materiaMapDTO.toEntity(materiaExistente));
		}
	}

	@Override
	public MateriaDTO buscarMateria(String codigo) {
		// TODO Auto-generated method stub
		List<MateriaDTO> todosLasMaterias = materiaMapDTO.listMateriaToListMateriaDTO(materiaRepository.findAll());
		for (int i = 0 ; i < todosLasMaterias.size();i++) {
			MateriaDTO materia = todosLasMaterias.get(i);
			if(materia.getCodigo().equals(codigo)) {
				return materia;
			}
		}
		return null;
	}

	@Override
	public void darDeAlta(String codigo) {
		// TODO Auto-generated method stub
		List<MateriaDTO> todosLasMaterias = materiaMapDTO.listMateriaToListMateriaDTO(materiaRepository.findAll());
		for (int i = 0 ; i < todosLasMaterias.size();i++) {
			MateriaDTO materia = todosLasMaterias.get(i);
			if(materia.getCodigo().equals(codigo)) {
				materia.setEstado(true);
				materiaRepository.save(materiaMapDTO.toEntity(materia));
			}
		}
	}
}
