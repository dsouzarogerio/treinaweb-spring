package br.com.treinaweb.twprojetos.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import br.com.treinaweb.twprojetos.api.anotacoes.ApiPageable;
import br.com.treinaweb.twprojetos.api.excecoes.ApiError;
import br.com.treinaweb.twprojetos.entidades.Cliente;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Interface de customização da documentação da Api 
 * a ser implementada pela classe ClienteApiController
 * 
 * @author dsouzarogerio
 *
 */
@Api(tags = "Clientes", description = "Cliente Controller")
public interface ClienteApiControllerDoc {

	@ApiOperation(value = "Listar todos os Clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Listagem dos clientes realizada com sucesso.")
	})
	@ApiPageable
	CollectionModel<EntityModel<Cliente>> buscarTodos(@ApiIgnore Pageable paginacao);
	
	@ApiOperation(value = "Buscar Cliente Por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente encontrado com sucesso."),
			@ApiResponse(code = 404, message = "Cliente não encontrado.", response = ApiError.class)
	})
	EntityModel<Cliente> buscarPorId(Long id);
	
	@ApiOperation(value = "Buscar Projeto do Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Projeto do Cliente encontrado com sucesso."),
			@ApiResponse(code = 404, message = "Cliente não encontrado.", response = ApiError.class)
	})
	CollectionModel<EntityModel<Projeto>> buscarPorProjetos(Long id);
}
