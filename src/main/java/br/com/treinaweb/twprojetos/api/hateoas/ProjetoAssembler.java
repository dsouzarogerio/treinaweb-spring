package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controller.ClienteApiController;
import br.com.treinaweb.twprojetos.api.controller.FuncionarioApiController;
import br.com.treinaweb.twprojetos.api.controller.ProjetoApiController;
import br.com.treinaweb.twprojetos.entidades.Projeto;

@Component
public class ProjetoAssembler implements SimpleRepresentationModelAssembler<Projeto> {

	@Override
	public void addLinks(EntityModel<Projeto> resource) {

		Long id = resource.getContent().getId();
		Long clienteId = resource.getContent().getCliente().getId();
		Long liderId = resource.getContent().getLider().getId();
		
		Link selfRel = linkTo(methodOn(ProjetoApiController.class).buscarPorId(id))
				.withSelfRel()
				.withType("GET");
		
		Link liderLink = linkTo(methodOn(FuncionarioApiController.class).buscarPorId(liderId))
				.withRel("lider")
				.withType("GET");
		
		Link clienteLink = linkTo(methodOn(ClienteApiController.class).buscarPorId(clienteId))
				.withRel("cliente")
				.withType("GET");
		
		Link editarLink = linkTo(methodOn(ProjetoApiController.class).atualizar(null, id))
				.withSelfRel()
				.withType("PUT");
		
		Link excluirLink = linkTo(methodOn(ProjetoApiController.class).excluirPorId(id))
				.withSelfRel()
				.withType("DELETE");
		
		Link equipeLink = linkTo(methodOn(ProjetoApiController.class).buscarEquipe(id))
				.withRel("equipe")
				.withType("GET");
		
		Link atualizarEquipeLink = linkTo(methodOn(ProjetoApiController.class).atualizarEquipe(id, null))
				.withRel("equipe")
				.withType("PATCH");
		
		resource.add(selfRel, liderLink, clienteLink, editarLink, excluirLink, equipeLink, atualizarEquipeLink);
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
	
		Link selfLink = linkTo(methodOn(ProjetoApiController.class).buscarTodos(null))
				.withSelfRel()
				.withType("GET");
		
		resources.add(selfLink);
		
	}
}
