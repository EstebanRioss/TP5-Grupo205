package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService {
	
	private static final Logger logger = LoggerFactory.getLogger(CarreraServiceImp.class);
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Autowired
	CarreraRepository carreraRepository;

	@Override
    public void guardarCarrera(CarreraDTO carreraDTO) {
        logger.info("Guardando carrera: {}", carreraDTO.getCodigo());
        carreraDTO.setEstado(true);
        carreraRepository.save(carreraMapDTO.convertirCarreraDTOACarrera(carreraDTO));
        logger.info("Carrera guardada exitosamente: {}", carreraDTO.getCodigo());
    }

    @Override
    public List<CarreraDTO> MostrarCarrera() {
        logger.info("Mostrando listado de carreras activas");
        List<CarreraDTO> carrerasDTO = carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(true));
        logger.info("Listado de carreras activas obtenido correctamente");
        return carrerasDTO;
    }

    @Override
    public void borrarCarrera(String codigo) {
        logger.info("Borrando carrera con código: {}", codigo);
        List<Carrera> todasLasCarreras = carreraRepository.findAll();
        for (Carrera carrera : todasLasCarreras) {
            if (carrera.getCodigo().equals(codigo)) {
                carrera.setEstado(false);
                carreraRepository.save(carrera);
                logger.info("Carrera con código {} borrada correctamente", codigo);
                break;
            }
        }
    }

    @Override
    public CarreraDTO buscaCarrera(String codigo) {
        logger.info("Buscando carrera con código: {}", codigo);
        CarreraDTO carreraDTO = carreraMapDTO.convertirAcarreraDTO(carreraRepository.findById(codigo).orElse(null));
        if (carreraDTO == null) {
            logger.warn("Carrera con código {} no encontrada", codigo);
        } else {
            logger.info("Carrera encontrada correctamente: {}", carreraDTO.getCodigo());
        }
        return carreraDTO;
    }

    @Override
    public void modificarCarrera(CarreraDTO carreraDTO) {
        logger.info("Modificando carrera con código: {}", carreraDTO.getCodigo());
        CarreraDTO carreraExistente = carreraDTO;
        if (carreraExistente != null) {
            carreraExistente.setEstado(true);
            carreraRepository.save(carreraMapDTO.convertirCarreraDTOACarrera(carreraExistente));
            logger.info("Carrera modificada correctamente: {}", carreraDTO.getCodigo());
        }
    }

    @Override
    public void darDeAltaCarrera(String codigo) {
        logger.info("Dando de alta carrera con código: {}", codigo);
        Carrera carrera = carreraRepository.findById(codigo).orElse(null);
        if (carrera != null) {
            carrera.setEstado(true);
            carreraRepository.save(carrera);
            logger.info("Carrera dada de alta correctamente: {}", codigo);
        } else {
            logger.warn("Carrera con código {} no encontrada para dar de alta", codigo);
        }
    }

    @Override
    public List<CarreraDTO> MostrarCarreraInactivas() {
        logger.info("Mostrando listado de carreras inactivas");
        List<CarreraDTO> carrerasDTO = carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(false));
        logger.info("Listado de carreras inactivas obtenido correctamente");
        return carrerasDTO;
    }

    @Override
    public void darDeBajaAlumnoDeCarrera(String codigoCarrera, String LU) {
        logger.info("Dando de baja alumno con LU {} de carrera con código: {}", LU, codigoCarrera);
        Carrera carrera = carreraRepository.findById(codigoCarrera).orElse(null);
        if (carrera != null) {
            carrera.getAlumnos().removeIf(alumno -> alumno.getLU().equals(LU));
            carreraRepository.save(carrera);
            logger.info("Alumno con LU {} dado de baja correctamente de la carrera", LU);
        } else {
            logger.warn("Carrera con código {} no encontrada para dar de baja alumno con LU {}", codigoCarrera, LU);
        }
    }

    @Override
    public void darDeBajaMateriaDeCarrera(String codigoCarrera, String codigoMateria) {
        logger.info("Dando de baja materia con código {} de carrera con código: {}", codigoMateria, codigoCarrera);
        Carrera carrera = carreraRepository.findById(codigoCarrera).orElse(null);
        if (carrera != null) {
            carrera.getMaterias().removeIf(materia -> materia.getCodigo().equals(codigoMateria));
            carreraRepository.save(carrera);
            logger.info("Materia con código {} dada de baja correctamente de la carrera", codigoMateria);
        } else {
            logger.warn("Carrera con código {} no encontrada para dar de baja materia con código {}", codigoCarrera, codigoMateria);
        }
    }
}