package br.com.treinaweb.twprojetos.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import br.com.treinaweb.twprojetos.api.anotacoes.ApiPageable;
import br.com.treinaweb.twprojetos.api.excecoes.ApiError;
import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Interface de customização da documentação da Api 
 * a ser implementada pela classe FuncionarioApiController
 * 
 * @author dsouzarogerio
 *
 */
@Api(tags = "Funcionarios",  description = "Funcionario Controller")
public interface FuncionarioApiControllerDoc {
	
	@ApiOperation(value = "Listar todos os Funcionários")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Listagem dos funcionários realizada com sucesso.")
	})
	@ApiPageable
	CollectionModel<EntityModel<Funcionario>> buscarTodos(@ApiIgnore Pageable paginacao);
	
	@ApiOperation(value = "Buscar Funcionário por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Busca por funcionário realizada com sucesso."),
			@ApiResponse(code = 404, message = "Funcionário não encontrado.", response = ApiError.class)
	})
	EntityModel<Funcionario> buscarPorId(Long id);
	
	@ApiOperation(value = "Buscar Projetos do Funcionário")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Busca por projeto do funcionário realizada com sucesso."),
			@ApiResponse(code = 404, message = "Funcionário não encontrado.", response = ApiError.class)
	})
	CollectionModel<EntityModel<Projeto>> buscarProjetos(Long id);
}
