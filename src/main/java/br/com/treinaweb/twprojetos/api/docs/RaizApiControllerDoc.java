package br.com.treinaweb.twprojetos.api.docs;

import br.com.treinaweb.twprojetos.api.hateoas.RaizAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * Interface de customização da documentação da Api 
 * a ser implementada pela classe CargoApiController
 * 
 * @author dsouzarogerio
 *
 */
@Api(tags = "Raiz",  description = "Raiz Controller")
public interface RaizApiControllerDoc {

	@ApiOperation(value = "Listar links da aplicação")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Listagem dos links realizada com sucesso.")
	})
	RaizAssembler raiz();
}
