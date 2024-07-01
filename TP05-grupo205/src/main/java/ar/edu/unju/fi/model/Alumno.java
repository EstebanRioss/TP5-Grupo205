package ar.edu.unju.fi.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@Component
public class Alumno {
	
	@Id
	@Column(unique = true)
    private String LU;
	private String dni;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Date fechaNacimiento;
    private String domicilio;
    private Boolean estado;
    
    @OneToMany
    private List<Carrera> carreras = new ArrayList<Carrera>();
    
    @ManyToMany
    private List<Materia> Materias = new ArrayList<Materia>();
}
