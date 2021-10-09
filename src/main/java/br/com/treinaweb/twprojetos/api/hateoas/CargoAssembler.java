package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controller.CargoApiController;
import br.com.treinaweb.twprojetos.entidades.Cargo;

/**
 * Classe responsável por criar os links HATEOAS dentro da CargoApiController
 * 
 * 
 * @author dsouzarogerio
 *
 */

@Component
public class CargoAssembler implements SimpleRepresentationModelAssembler<Cargo> {

	// Método que adiciona os links em um recurso específico
	@Override
	public void addLinks(EntityModel<Cargo> resource) {

		Long id = resource.getContent().getId();

		// objeto link que aponta para o próprio recurso
		Link selfLink = linkTo(methodOn(CargoApiController.class).buscarPorId(id))
				.withSelfRel()
				.withType("GET");

		Link editarLink = linkTo(methodOn(CargoApiController.class).atualizar(null, id))
				.withSelfRel()
				.withType("PUT");

		Link excluirLink = linkTo(methodOn(CargoApiController.class).excluir(id))
				.withSelfRel()
				.withType("DELETE");

		resource.add(selfLink, editarLink, excluirLink);

	}

	// Método para adição de um link à própria coleção
	@Override
	public void addLinks(CollectionModel<EntityModel<Cargo>> resources) {
		
		Link cadastroLink = linkTo(methodOn(CargoApiController.class).cadastrar(null))
				.withSelfRel()
				.withType("POST");
		
		Link selfLink = linkTo(methodOn(CargoApiController.class).buscarTodos(null))
				.withSelfRel()
				.withType("GET");
		
		resources.add(selfLink, cadastroLink);

	}

}
