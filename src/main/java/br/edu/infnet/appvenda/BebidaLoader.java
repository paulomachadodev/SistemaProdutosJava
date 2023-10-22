package br.edu.infnet.appvenda;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.appvenda.model.domain.Bebida;
import br.edu.infnet.appvenda.model.service.BebidaService;

@Order(4)
@Component
public class BebidaLoader implements ApplicationRunner {

	@Autowired
	private BebidaService bebidaService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		FileReader file = new FileReader("files/bebida.txt");		
		BufferedReader leitura = new BufferedReader(file);
		
		String linha = leitura.readLine();

		String[] campos = null;
				
		while(linha != null) {
			
			campos = linha.split(";");
			
			Bebida bebida = new Bebida();

			bebida.setCodigo(Integer.valueOf(campos[0]));
			bebida.setDescricao(campos[1]);
			bebida.setPreco(Float.valueOf(campos[2]));
			bebida.setEstoque(Boolean.valueOf(campos[3]));
			bebida.setMarca(campos[4]);
			bebida.setSabor(campos[5]);
			bebida.setAlcoolica(Boolean.valueOf(campos[6]));
			
			bebidaService.incluir(bebida);
			
			linha = leitura.readLine();
		}

		for(Bebida bebida: bebidaService.obterLista()) {
			System.out.println("[Bebida] " + bebida);			
		}

		leitura.close();
	}
}
