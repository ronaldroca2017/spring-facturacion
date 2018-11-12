package com.spring.sisfact.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	
	private Page<T> page;
	
	private int totalPagina;
	
	private int numeElementosPorPagina;
	
	private int paginaActual;
	
	private List<PageItem> paginas;
	
	/*
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		numeElementosPorPagina = page.getSize();
		totalPagina = page.getTotalPages();
		paginaActual = page.getNumber() + 1; //@RequestParam(name = "page", defaultValue = "0"
		
		paginas = new ArrayList<PageItem>();
		
		int desde, hasta;
		if(totalPagina <= numeElementosPorPagina) {
			desde = 1;
			hasta = totalPagina;
		}else {
			if(paginaActual <= numeElementosPorPagina/2) {
				desde = 1;
				hasta = numeElementosPorPagina;
			}else if(paginaActual >= totalPagina - numeElementosPorPagina/2) {
				desde = totalPagina - numeElementosPorPagina + 1;
				hasta = numeElementosPorPagina;
			}else {
				desde = paginaActual - numeElementosPorPagina/2;
				hasta = numeElementosPorPagina; 
			}
			
			
		}
		
		for(int i = 0;  i< hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde +i));
		}
		
	}*/

	public String getUrl() {
		return url;
	}

	public int getTotalPagina() {
		return totalPagina;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
	
	
	  public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<PageItem>();
		
		numeElementosPorPagina = page.getSize();
		totalPagina = page.getTotalPages();
		paginaActual = page.getNumber()+1;
		
		int desde, hasta;
		
		if(totalPagina <= 5) {
			desde=1;
			hasta=totalPagina;
		}else{
			if(paginaActual - 2 <=0) {
				desde = 1;
				hasta = 5;
			}else if(paginaActual + 2 > totalPagina) {
				desde=totalPagina - 4;
				hasta=totalPagina;
			}else {
				desde = paginaActual - 2;
				hasta = paginaActual + 2;
			}
		}
			
		
		for(int i=desde; i <= hasta; i++) {
			paginas.add(new PageItem(i, paginaActual == i));
		}
 
	}
	 
	
}
