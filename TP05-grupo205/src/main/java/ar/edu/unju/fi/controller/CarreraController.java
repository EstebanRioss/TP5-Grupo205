package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.service.CarreraService;

@Controller
public class CarreraController {
    
	@Autowired
	private CarreraDTO nuevacarreraDTO;
	
    @Autowired
    private CarreraService cs;
    
    
    @GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera() {
    	
		ModelAndView modelView = new ModelAndView("carrera/formCarrera");
		modelView.addObject("nuevaCarrera",nuevacarreraDTO);
		
		return modelView;
	}
    
    @PostMapping("/guardarCarrera")
    public ModelAndView saveCarrera(@ModelAttribute("nuevaCarrera") CarreraDTO carrera) {
        cs.guardarCarrera(carrera);
        ModelAndView modelView = new ModelAndView("carrera/listaDeCarreras");
        modelView.addObject("listadoCarreras", cs.MostrarCarrera()); 
        return modelView;
    }
    
    @GetMapping("/borrarCarrera/{codigo}")
	public ModelAndView borrarCarrera(@PathVariable(name="codigo") String codigo){
		cs.borrarCarrera(codigo);
		ModelAndView modelView = new ModelAndView("carrera/listaDeCarreras");
		modelView.addObject("listadoCarreras",cs.MostrarCarrera());
		
		return modelView;
	}
    @GetMapping("/DarDeAlta/{codigo}")
	public ModelAndView DarDeAlta(@PathVariable(name="codigo") String codigo){
		cs.darDeAltaCarrera(codigo);
		ModelAndView modelView = new ModelAndView("carrera/listaDeCarreras");
		modelView.addObject("listadoCarreras",cs.MostrarCarrera());
		
		return modelView;
	}
    
    @GetMapping("/modificarCarrera/{codigo}")
	public ModelAndView mostrarFormularioModificarCarrera(@PathVariable("codigo") String codigo){
		
		ModelAndView modelView = new ModelAndView("carrera/modificacionCarrera");
		modelView.addObject("carrera", cs.buscaCarrera(codigo));

		return modelView;
	}
    
    @PostMapping("/guardarModificacionCarrera")
	public ModelAndView guardarModificacionCarrera(@ModelAttribute ("carrera") CarreraDTO carrera) {
		
		cs.modificarCarrera(carrera);
		ModelAndView modelView = new ModelAndView("carrera/listaDeCarreras");
		modelView.addObject("listadoCarreras",cs.MostrarCarrera());
		
		return modelView;
	}
}