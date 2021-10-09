package br.com.treinaweb.twprojetos.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.treinaweb.twprojetos.dto.AlertDTO;
import br.com.treinaweb.twprojetos.entidades.Cargo;
import br.com.treinaweb.twprojetos.servicos.CargoServico;
import br.com.treinaweb.twprojetos.web.excecoes.CargoPossuiFuncionariosException;

@Controller
@RequestMapping("/cargos")
public class CargoControle {

	//3 Formas de INJEÇÃO DE DEPENDÊNCIAS
	
	//1.Anotação AUTOWIRED no atributo da classe
    @Autowired
    private CargoServico cargoServico;
//    
    //2. Anotação AUTOWIRED no método set dos atributos a serem utilizados
//    @Autowired
//    public void setCargoServico(CargoServico cargoServico) {
//		this.cargoServico = cargoServico;
//	}
    
    //3. Através do construtor da classe
    //facilita a aplicação de testes automatizados
//    public CargoControle(CargoServico cargoServico) {
//    	this.cargoServico = cargoServico;
//	}
    

	@GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cargo/home");

        modelAndView.addObject("cargos", cargoServico.buscarTodos());

        return modelAndView;
    }

	@GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cargo/formulario");

        modelAndView.addObject("cargo", new Cargo());

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cargo/formulario");

        modelAndView.addObject("cargo", cargoServico.buscarPorId(id));

        return modelAndView;
    }

    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(@Valid Cargo cargo, BindingResult resultado, RedirectAttributes attrs,
    		@PathVariable(required = false) Long id) {
    	
    	if(resultado.hasErrors()) {
    		return "cargo/formulario";
    	}
    	
        if(cargo.getId() ==  null) {
        	cargoServico.cadastrar(cargo);
        	attrs.addFlashAttribute("alert", new AlertDTO("Cargo cadastrado com sucesso!", "alert-success"));
        } else {
        	cargoServico.atualizar(cargo, id);
        	attrs.addFlashAttribute("alert", new AlertDTO("Cargo editado com sucesso!", "alert-success"));
        }

        return "redirect:/cargos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
        try{
        	cargoServico.excluirPorId(id);
        	attrs.addFlashAttribute("alert", new AlertDTO("Cargo excluído com sucesso!", "alert-success"));
        } catch(CargoPossuiFuncionariosException e) {
        	attrs.addFlashAttribute("alert", new AlertDTO("Cargo não pode ser excluído, pois possui funcionario(s) relacionado(s)", "alert-danger"));
        }
        

        return "redirect:/cargos";
    }
    
}