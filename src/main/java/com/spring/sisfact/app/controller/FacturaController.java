package com.spring.sisfact.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sisfact.app.entity.Cliente;
import com.spring.sisfact.app.entity.Factura;
import com.spring.sisfact.app.service.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {
	
	
	@Autowired
	IClienteService clienteService;

	@GetMapping("/crear/{clienteId}")
	public String crear(@PathVariable(value = "clienteId") long clienteId,
			Model model,
			RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(clienteId);
		if(cliente!=null) {
			Factura factura = new Factura();
			factura.setCliente(cliente);
			
			/*model.put("titulo", "Crear Factura");
			model.put("factura", factura);		*/
			
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("factura", factura);
			model.addAttribute("nombre",  factura.getCliente().getNombre());
			

		}else {
			flash.addFlashAttribute("error", "El cliente no existe en la BBDD");
			return "redirect:/listar";
		}
		
		
		return "factura/form";
	}
}
