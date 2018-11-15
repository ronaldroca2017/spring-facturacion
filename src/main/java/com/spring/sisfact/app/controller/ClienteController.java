package com.spring.sisfact.app.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.sisfact.app.dao.IClienteDao;
import com.spring.sisfact.app.entity.Cliente;
import com.spring.sisfact.app.service.IClienteService;
import com.spring.sisfact.app.util.paginator.PageRender;


@Controller
public class ClienteController {
	
	
	@Autowired
	//@Qualifier("clienteDaoJPA")//Para saber que clase implementa la interface le ponemos un nombre a @Repository("clienteDaoJPA")
	private IClienteDao clienteDao;
	
	@Autowired
	private IClienteService clienteService;
	
	@RequestMapping(value = "/listar" , method = RequestMethod.GET)//by default the method is GET
	public String listar(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
		
		Pageable pageRequest = new PageRequest(page, 5);
		Page<Cliente> clientesPaginado = clienteService.findAllPaginate(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientesPaginado);
		
		 model.addAttribute("titulo", "Listado de Clientes");
		 model.addAttribute("clientes", clientesPaginado);
		 model.addAttribute("page", pageRender);
		
		 return "listar";
	}
	  
	@RequestMapping(value="/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> mapObject) {
		
		Cliente cliente = new Cliente();
		mapObject.put("titulo", "Formulario de cliente");
		mapObject.put("cliente", cliente);
		return "registrarform";
	}
	
	@RequestMapping(value="/form", method = RequestMethod.POST)
	public String submit(Cliente cliente, RedirectAttributes flash) {

		
		if(cliente.getId()==null) {
		flash.addFlashAttribute("success", "Cliente creado con éxito");
		}else {
			flash.addFlashAttribute("success", "Cliente Actualizado con éxito");
		}
		
		clienteService.save(cliente);
		return "redirect:/listar";
	}
 
	@RequestMapping(value="/form/{id}" , method = RequestMethod.GET)
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> mapObject,  RedirectAttributes flash) {
		Cliente cliente = null;
		
		if(id>0) {
			cliente = clienteService.findOne(id);
			if(cliente==null) {
				flash.addFlashAttribute("error", "El cliente no existe en la BBDD !");
				return "redirect:/listar";
			}
		}else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero !");
			return "redirect:/listar";
		}
		
		mapObject.put("cliente", cliente);
		mapObject.put("titulo", "editar cliente");
		
		return "registrarform";
	}
	
	
	@RequestMapping(value="/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		if(id>0) {
			clienteService.delete(id);
			flash.addFlashAttribute("warning", "Cliente Eliminado !");
		}
		
		return "redirect:/listar";
	}
	
	@RequestMapping(value="/ver/{id}" , method = RequestMethod.GET)
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> mapObject) {
		Cliente cliente = null;
		
		if(id>0) {
			cliente = clienteService.findOne(id);
			if(cliente==null) {
			
				return "redirect:/listar";
			}
		}else {
			
			return "redirect:/listar";
		}
		
		mapObject.put("cliente", cliente);
		mapObject.put("titulo", "Detalle de los datos y facturas del cliente");
		
		return "ver";
	}
	
}
