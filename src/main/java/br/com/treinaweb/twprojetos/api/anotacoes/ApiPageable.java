package br.com.treinaweb.twprojetos.api.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * Interface de Anotação a ser anotada na documentação como parametro de paginação
 * 
 * @author dsouzarogerio
 *
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})//locais onde a anotação pode ser utilizada
@Retention(RetentionPolicy.RUNTIME)//momento de aplicação da anotação
 
/*
 * Anotações especificas dos parâmetros
 */
@ApiImplicitParams({
	@ApiImplicitParam(name = "pagina", dataType = "int", paramType = "query", defaultValue = "1", value = "Número da página que deseja recuperar(1..N)"),
	@ApiImplicitParam(name = "tamanho", dataType = "int", paramType = "query", defaultValue = "2", value = "Número de elementos por página."),
	@ApiImplicitParam(name = "ordenacao", dataType = "String", allowMultiple = true, paramType = "query", value = "Critério no formato: propriedade(, asc|desc)")
})
public @interface ApiPageable {

}
