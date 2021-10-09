package br.com.treinaweb.twprojetos.api.mapper;

import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.entidades.Cargo;

@Component
public class CargoMapper {
	
	public Cargo converterParaEntidade(CargoDTO cargoDTO) {
		
		//antes do cadastro fazer a conversão do cargo para DTO
		
		//1.Instanciar um novo cargo
		Cargo cargo = new Cargo();
		//2. configurar/transformar o cargo com o atributo "nome" do cargoDTO
		cargo.setNome(cargoDTO.getNome());
		
		return cargo;
	}

}
