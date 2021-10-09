package br.com.treinaweb.twprojetos.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import br.com.treinaweb.twprojetos.repositorios.FuncionarioRepositorio;
import br.com.treinaweb.twprojetos.repositorios.ProjetoRepositorio;
import br.com.treinaweb.twprojetos.utils.SenhaUtils;
import br.com.treinaweb.twprojetos.web.excecoes.FuncionarioEhLiderDeProjetoException;
import br.com.treinaweb.twprojetos.web.excecoes.FuncionarioNaoEncontradoException;

@Service
public class FuncionarioServico {
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	@Autowired
	private ProjetoRepositorio projetoRepositorio;
	
	public List<Funcionario> buscarTodos(){
		return funcionarioRepositorio.findAll();
	}
	
	public Page<Funcionario> buscarTodos(Pageable paginacao){
		return funcionarioRepositorio.findAll(paginacao);
	}
	
	public Funcionario buscarPorId(Long id) {
		return funcionarioRepositorio.findById(id)
				.orElseThrow(() -> new FuncionarioNaoEncontradoException(id));
	}
	
	public List<Funcionario> buscarLider() {
		return funcionarioRepositorio.findByCargoNome("Gerente");
	}
	
	public List<Funcionario> buscarEquipe(){
		return funcionarioRepositorio.findByCargoNomeNot("Gerente");
	}
	
	public Funcionario cadastrar(Funcionario funcionario) {
		
		String senhaEncriptada = SenhaUtils.encode(funcionario.getSenha());
        funcionario.setSenha(senhaEncriptada);
		
		return funcionarioRepositorio.save(funcionario);
	}
	
	public Funcionario atualizar(Funcionario funcionario, Long id) {
		Funcionario funcionarioEncontrado = buscarPorId(id);
		
		funcionario.setSenha(funcionarioEncontrado.getSenha());
			
		return funcionarioRepositorio.save(funcionario);
	}
	
	public void excluirPorId(Long id) {
		
		Funcionario funcionarioEncontrado = buscarPorId(id);
		
		if(projetoRepositorio.findByLider(funcionarioEncontrado).isEmpty()) {
			if(!funcionarioEncontrado.getProjetos().isEmpty()) {
				for(Projeto projeto : funcionarioEncontrado.getProjetos()) {
					projeto.getEquipe().remove(funcionarioEncontrado);
					projetoRepositorio.save(projeto);
				}
			}
			funcionarioRepositorio.delete(funcionarioEncontrado);
		} else {
			throw new FuncionarioEhLiderDeProjetoException(id);
		}
		
	}
}
