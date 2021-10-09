package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controller.CargoApiController;
import br.com.treinaweb.twprojetos.api.controller.FuncionarioApiController;
import br.com.treinaweb.twprojetos.entidades.Funcionario;

@Component
public class FuncionarioAssembler implements SimpleRepresentationModelAssembler<Funcionario>{

	@Override
	public void addLinks(EntityModel<Funcionario> resource) {
		
		Long funcionarioId = resource.getContent().getId();
		Long cargoId = resource.getContent().getCargo().getId();
		
		Link selfLink = linkTo(methodOn(FuncionarioApiController.class).buscarPorId(funcionarioId))
				.withSelfRel()
				.withType("GET");
		
		Link cargoLink = linkTo(methodOn(CargoApiController.class).buscarPorId(cargoId))
				.withRel("cargo")
				.withType("GET");
		
		Link projetoLink = linkTo(methodOn(FuncionarioApiController.class).buscarProjetos(funcionarioId))
				.withSelfRel()
				.withType("GET");
		
		resource.add(selfLink, cargoLink, projetoLink);
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Funcionario>> resources) {
		
		Link selfLink = linkTo(methodOn(FuncionarioApiController.class).buscarTodos(null))
				.withSelfRel()
				.withType("GET");
		
		resources.add(selfLink);
	}
	
	

}
