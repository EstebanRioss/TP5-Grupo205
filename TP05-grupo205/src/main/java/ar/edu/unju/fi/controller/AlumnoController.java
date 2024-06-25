package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.service.AlumnoService;

@Controller
public class AlumnoController {
	
	@Autowired
	AlumnoDTO nuevoAlumnoDTO;
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		ModelAndView modelView = new ModelAndView("alumno/formAlumno");
		modelView.addObject("nuevoAlumno",nuevoAlumnoDTO);
		
		return modelView;
	}
	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@ModelAttribute ("nuevoAlumno") AlumnoDTO Alumno) {
		
		alumnoService.guardarAlumno(Alumno);
		ModelAndView modelView = new ModelAndView("Alumno/listaDeAlumnos");
		modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumno());
		
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
		ModelAndView modelView = new ModelAndView("Alumno/modificacionAlumno");
		modelView.addObject("Alumno", alumnoService.buscarAlumno(LU));

		return modelView;
	}
	
	@PostMapping("/guardarModificacionAlumno")
	public ModelAndView guardarModificacionAlumno(@ModelAttribute ("Alumno") AlumnoDTO Alumno) {
		
		alumnoService.modificarAlumno(Alumno);
		ModelAndView modelView = new ModelAndView("/Alumno/listaDeAlumnos");
		modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumno());
		
		return modelView;
	}
	
}
