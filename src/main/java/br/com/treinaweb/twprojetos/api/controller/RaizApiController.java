package br.com.treinaweb.twprojetos.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.hateoas.RaizAssembler;

/**
 * Classe Raiz que instância o RaizAssembler para os links relacionados ao projeto
 * @author dsouzarogerio
 *
 */

@RestController
@RequestMapping("/api/v1")
public class RaizApiController {

	//método para retorno dos links
	@GetMapping
	public  RaizAssembler raiz(){
		
		RaizAssembler raizAssembler = new RaizAssembler();
		
		//Link para o recurso de cargos
		Link cargosLink = linkTo(methodOn(CargoApiController.class).buscarTodos(null))
				.withRel("cargos")
				.withType("GET");
		
		raizAssembler.add(cargosLink);
		
		return raizAssembler;
	}
}