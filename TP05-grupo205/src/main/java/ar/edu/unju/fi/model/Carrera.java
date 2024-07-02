package ar.edu.unju.fi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Component
@Entity
public class Carrera {
	@Id
	private String codigo;
	private String nombre;
	private Integer duracion;
	private Boolean estado;
	
	@OneToMany
	private List<Alumno> alumnos = new ArrayList<Alumno>();
	
	@OneToMany
	private List<Materia> materias = new ArrayList<Materia>();
	
}