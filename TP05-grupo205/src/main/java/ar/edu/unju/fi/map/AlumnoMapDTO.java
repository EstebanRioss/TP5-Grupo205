package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlumnoMapDTO {
    
    @Mapping(target = "codigoMateria", ignore = true)
	@Mapping(source="estado", target="estado")
	@Mapping(source="nombre", target="nombre")
    @Mapping(source="dni", target="dni")
    @Mapping(source="apellido",target = "apellido")
	@Mapping(source="LU",target = "LU")
    AlumnoDTO convertirAlumnoAAlumnoDTO(Alumno a);
    
    
	@Mapping(target = "domicilio", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "fechaNacimiento", ignore = true)
	@Mapping(target = "telefono", ignore = true)
	@InheritInverseConfiguration
    Alumno convertirAlumnoDTOAAlumno(AlumnoDTO adto);
    
    List<AlumnoDTO> convertirListaAlumnoAListaAlumnoDTO(List<Alumno> listaA);
    List<Alumno> convertirListaAlumnoDTOAListaAlumno(List<AlumnoDTO> listaADTO);
}

