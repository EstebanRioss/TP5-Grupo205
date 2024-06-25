package ar.edu.unju.fi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.service.MateriaService;

@Controller
public class MateriaController {
	@Autowired
	MateriaDTO nuevaMateria;
	
	@Autowired
	MateriaService materiaService;
	
	@GetMapping("/formularioMateria")
	public ModelAndView getFormMateria() {
		ModelAndView modelView = new ModelAndView("materia/formMateria");
		modelView.addObject("nuevaMateria",nuevaMateria);
		
		return modelView;
	}
	
	@PostMapping("/guardarMateria")
	public ModelAndView saveMateria(@ModelAttribute ("nuevaMateria") MateriaDTO Materia) {
		
		materiaService.guardarMateria(Materia);
		ModelAndView modelView = new ModelAndView("materia/listadoDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.mostrarMaterias());
		
		return modelView;
	}
	
	@GetMapping("/borrarMateria/{codigo}")
	public ModelAndView borrarMateria(@PathVariable(name="codigo") String codigo){
		materiaService.borrarMateria(codigo);
		ModelAndView modelView = new ModelAndView("materia/listadoDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.mostrarMaterias());
		
		return modelView;
	}
	
	@GetMapping("/darDeAlta/{codigo}")
		public ModelAndView darDeAlta(@PathVariable(name="codigo") String codigo){
		materiaService.darDeAlta(codigo);
		ModelAndView modelView = new ModelAndView("materia/listadoDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.mostrarMaterias());
		
		return modelView;
	}
	
	@GetMapping("/modificarMateria/{codigo}")
	public ModelAndView mostrarFormularioModificarMateria(@PathVariable("codigo") String codigo){
		ModelAndView modelView = new ModelAndView("materia/modificacionMateria");
		modelView.addObject("Materia",materiaService.buscarMateria(codigo));

		return modelView;
	}
	
	@PostMapping("/guardarModificacionMateria")
	public ModelAndView guardarModificacionMateria(@ModelAttribute ("Materia") MateriaDTO Materia) {
		
		materiaService.modificarMateria(Materia);
		ModelAndView modelView = new ModelAndView("materia/listadoDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.mostrarMaterias());
		
		return modelView;
	}
}
