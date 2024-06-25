package ar.edu.unju.fi.model;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
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
}
