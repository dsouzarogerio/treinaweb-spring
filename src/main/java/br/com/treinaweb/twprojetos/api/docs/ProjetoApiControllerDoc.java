package br.com.treinaweb.twprojetos.api.docs;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.treinaweb.twprojetos.api.anotacoes.ApiPageable;
import br.com.treinaweb.twprojetos.api.dto.EquipeDTO;
import br.com.treinaweb.twprojetos.api.dto.ProjetoDTO;
import br.com.treinaweb.twprojetos.api.excecoes.ApiError;
import br.com.treinaweb.twprojetos.api.excecoes.ValidacaoApiError;
import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Interface de customização da documentação da Api 
 * a ser implementada pela classe ProjetoApiController
 * 
 * @author dsouzarogerio
 *
 */
@Api(tags = "Projetos",  description = "Projeto Controller")
public interface ProjetoApiControllerDoc {

	@ApiOperation(value = "Listar todos os projetos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Listagem dos projetos realizada com sucesso.")
	})
	@ApiPageable
	CollectionModel<EntityModel<Projeto>> buscarTodos(@ApiIgnore Pageable paginacao);
	
	@ApiOperation(value = "Buscar projeto por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Projeto encontrado com sucesso."),
			@ApiResponse(code = 404, message = "Projeto não encontrado.", response = ApiError.class)
	})
	EntityModel<Projeto> buscarPorId(@PathVariable Long id);
	
	@ApiOperation(value = "Cadastrar projeto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Projeto cadastrado com sucesso."),
			@ApiResponse(code = 400, message = "Há erros de validação.", response = ValidacaoApiError.class)
	})
	EntityModel<Projeto> cadastrar(ProjetoDTO projetoDTO);
	
	@ApiOperation(value = "Atualizar projeto por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Projeto atualizado com sucesso."),
			@ApiResponse(code = 400, message = "Há erros de validação.", response = ValidacaoApiError.class),
			@ApiResponse(code = 404, message = "Projeto não encontrado.", response = ApiError.class)
	})
	EntityModel<Projeto> atualizar(ProjetoDTO projetoDTO, Long id);
	
	@ApiOperation(value = "Excluir projeto por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Projeto excluído com sucesso."),
			@ApiResponse(code = 404, message = "Projeto não encontrado.", response = ApiError.class)
	})
	ResponseEntity<?> excluirPorId(Long id);
	
	@ApiOperation(value = "Buscar equipe do projeto por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Projeto encontrado com sucesso."),
			@ApiResponse(code = 404, message = "Projeto não encontrado.", response = ApiError.class)
	})
	CollectionModel<EntityModel<Funcionario>> buscarEquipe(Long id);
	
	@ApiOperation(value = "Atualizar equipe do projeto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Projeto atualizado com sucesso."),
			@ApiResponse(code = 400, message = "Há erros de validação.", response = ValidacaoApiError.class),
			@ApiResponse(code = 404, message = "Projeto não encontrado.", response = ApiError.class)
	})
	CollectionModel<EntityModel<Funcionario>> atualizarEquipe(Long id, EquipeDTO equipeDTO);
}
