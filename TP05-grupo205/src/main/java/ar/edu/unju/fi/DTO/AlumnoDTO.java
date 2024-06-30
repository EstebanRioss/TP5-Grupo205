package ar.edu.unju.fi.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Component
public class AlumnoDTO {
	
	@Id
    @Column(unique = true)
	private String LU;
	
	@NotBlank(message = "Debe ingresar su DNI")
    @Pattern(regexp = "\\d+", message = "El DNI debe contener solo numeros")
	private String dni;	
	
	@NotBlank(message = "Debe ingresar su nombre")
    @Size(min = 3, max = 20, message = "El nombre debe contener como minimo 3 caracteres y como maximo 20 caracteres")
    private String nombre;
    
    @NotBlank(message = "Debe ingresar su apellido")
    @Size(min = 3, max = 20, message = "El apellido debe contener como minimo 3 caracteres y como maximo 20 caracteres")
    private String apellido;
    
    @NotNull(message = "Debe especificar el estado")
    private Boolean estado;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<String> codigoMateria;
    
}
