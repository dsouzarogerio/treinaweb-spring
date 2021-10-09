package br.com.treinaweb.twprojetos.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinaweb.twprojetos.api.dto.EquipeDTO;
import br.com.treinaweb.twprojetos.api.dto.ProjetoDTO;
import br.com.treinaweb.twprojetos.api.mapper.ProjetoMapper;
import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import br.com.treinaweb.twprojetos.repositorios.ProjetoRepositorio;
import br.com.treinaweb.twprojetos.web.excecoes.ProjetoNaoEncontradoException;

@Service
public class ProjetoServico {
	
	@Autowired
	private ProjetoRepositorio projetoRepositorio;
	
	@Autowired
	private FuncionarioServico funcionarioServico;
	
	@Autowired
	private ProjetoMapper projetoMapper;
	
	public List<Projeto> buscarTodos(){
		return projetoRepositorio.findAll();
	}
	
	public Page<Projeto> buscarTodos(Pageable paginacao){
		return projetoRepositorio.findAll(paginacao);
	}
	
	public Projeto buscarPorId(Long id) {
		return projetoRepositorio.findById(id)
				.orElseThrow(() -> new ProjetoNaoEncontradoException(id));
	}
	
	public Projeto cadastrar(Projeto projeto) {
		return projetoRepositorio.save(projeto);
	}
	
	public Projeto cadastrar(ProjetoDTO projetoDTO) {
		Projeto projeto = projetoMapper.converterParaEntidade(projetoDTO);
		
		return projetoRepositorio.save(projeto);
	}
	
	public Projeto atualizar(Projeto projeto, Long id) {
		buscarPorId(id);
	
		return projetoRepositorio.save(projeto);	
		
	}
	
	public Projeto atualizar(ProjetoDTO projetoDTO, Long id) {
		buscarPorId(id);
		
		Projeto projeto = projetoMapper.converterParaEntidade(projetoDTO);
		projeto.setId(id);
		
		return projetoRepositorio.save(projeto);
	
	}
	
	public List<Funcionario> atualizarEquipe(EquipeDTO equipeDTO, Long id) {
		//buscar o projeto pelo seu id, que desejo atualizar a equipe de funcionarios
		Projeto projeto = buscarPorId(id);
		
		//cria a lista de funcionarios vazia
		List<Funcionario> equipe = new ArrayList<>();
		
		//popula a lista de funcionarios iterando através da busca 
		//no banco de dados pelo id do funcionario
		equipeDTO.getEquipe().forEach(funcionarioId -> {
			Funcionario funcionario = funcionarioServico.buscarPorId(funcionarioId);
			
			equipe.add(funcionario);
		});
		
		//atualizacao do projeto com a nova equipe
		projeto.setEquipe(equipe);
		
		//atualizacao do repositorio do projeto
		projetoRepositorio.save(projeto);
		
		return equipe;
	}
	
	public void excluirPorId(Long id) {
		Projeto projetoEncontrado = buscarPorId(id);
		
		projetoRepositorio.delete(projetoEncontrado);
	}

	
}