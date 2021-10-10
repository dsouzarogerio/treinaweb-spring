package br.com.treinaweb.twprojetos.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twprojetos.api.docs.RaizApiControllerDoc;
import br.com.treinaweb.twprojetos.api.hateoas.RaizAssembler;

/**
 * Classe Raiz que instância o RaizAssembler para os links relacionados ao projeto
 * @author dsouzarogerio
 *
 */

@RestController
@RequestMapping("/api/v1")
public class RaizApiController implements RaizApiControllerDoc {

	//método para retorno dos links
	@GetMapping
	public  RaizAssembler raiz(){
		
		RaizAssembler raizAssembler = new RaizAssembler();
		
		//Link para o recurso de cargos
		Link cargosLink = linkTo(methodOn(CargoApiController.class).buscarTodos(null))
				.withRel("cargos")
				.withType("GET");
		
		Link clientesLink = linkTo(methodOn(ClienteApiController.class).buscarTodos(null))
				.withRel("clientes")
				.withType("GET");
		
		Link funcionariosLink = linkTo(methodOn(FuncionarioApiController.class).buscarTodos(null))
				.withRel("funcionarios")
				.withType("GET");
		
		Link projetosLink = linkTo(methodOn(ProjetoApiController.class).buscarTodos(null))
				.withRel("projetos")
				.withType("GET");
		
		raizAssembler.add(cargosLink, clientesLink, funcionariosLink, projetosLink);
		
		return raizAssembler;
	}
}