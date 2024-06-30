package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;

@Controller
public class AlumnoController {
	
	@Autowired
	AlumnoDTO nuevoAlumnoDTO;
	
	@Autowired
	AlumnoService alumnoService;
	
	@Autowired
	MateriaService materiaService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		ModelAndView modelView = new ModelAndView("alumno/formAlumno");
		modelView.addObject("nuevoAlumno",nuevoAlumnoDTO);
		modelView.addObject("band",false);
		
		return modelView;
	}
	
	@GetMapping("/listadoAlumno")
    public ModelAndView getListadoAlumno() {
    	ModelAndView modelView = new ModelAndView("alumno/listaDeAlumnos");
    	modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumno());
    	return modelView;
    }
	@GetMapping("/listadoAlumnoInactivo")
    public ModelAndView getListadoInactivoAlumno() {
    	ModelAndView modelView = new ModelAndView("alumno/listaDeAlumnosInactivos");
    	modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumnoInactivos());
    	return modelView;
    }

	
	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@Valid @ModelAttribute ("nuevoAlumno") AlumnoDTO Alumno, BindingResult resultado) {
		
		ModelAndView modelView = new ModelAndView();
		alumnoService.guardarAlumno(Alumno);
		if(resultado.hasErrors()){
			modelView.setViewName("alumno/formAlumno");
		}else {
		modelView.setViewName("alumno/listaDeAlumnos");
		modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumno());
		modelView.addObject("listadoMaterias",materiaService.mostrarMaterias());
		}
		return modelView;
	}
	@GetMapping("/borrarAlumno/{LU}")
	public ModelAndView borrarAlumno(@PathVariable(name="LU") String LU){
		//ListadoAlumnos.eliminarAlumno(LU);
		alumnoService.borrarAlumno(LU);
		
		ModelAndView modelView = new ModelAndView("/Alumno/listaDeAlumnos");
		modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumno());
		
		return modelView;
	}
	
	@GetMapping("/modificarAlumno/{LU}")
	public ModelAndView mostrarFormularioModificarAlumno(@PathVariable(name="LU") String LU){
		ModelAndView modelView = new ModelAndView("Alumno/formAlumno");
		modelView.addObject("nuevoAlumno", alumnoService.buscarAlumno(LU));
		modelView.addObject("band",false);

		return modelView;
	}
	
	@PostMapping("/guardarModAlumno")
	public ModelAndView guardarModificacionAlumno(@ModelAttribute ("nuevoAlumno") AlumnoDTO Alumno) {
		
		alumnoService.modificarAlumno(Alumno);
		ModelAndView modelView = new ModelAndView("Alumno/listaDeAlumnos");
		modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumno());
		
		return modelView;
	}
	
	@GetMapping("/DardeAltaAlumno/{LU}")
	public ModelAndView DarDeAltaAlumno(@PathVariable(name="LU") String LU){
		//ListadoAlumnos.eliminarAlumno(LU);
		alumnoService.DardeAlta(LU);
		
		ModelAndView modelView = new ModelAndView("/Alumno/listaDeAlumnos");
		modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumno());
		
		return modelView;
	}
	
}
