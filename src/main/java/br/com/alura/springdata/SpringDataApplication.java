package br.com.alura.springdata;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.springdata.service.CargoService;
import br.com.alura.springdata.service.FuncionarioService;
import br.com.alura.springdata.service.RelatorioDinamicoService;
import br.com.alura.springdata.service.RelatorioService;
import br.com.alura.springdata.service.UnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner
{
	// Atributos da Classe
	private final CargoService cargoService;
	private final FuncionarioService funcionarioService;
	private final UnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatorioService relatorioService;
	private final RelatorioDinamicoService relatorioDinamicoService;

	// Construtor com injecao de dependencia (sem @Autowired)
	public SpringDataApplication(	CargoService cargoService,
									FuncionarioService funcionarioService,
									UnidadeTrabalhoService unidadeTrabalhoService,
									RelatorioService relatorioService,
									RelatorioDinamicoService relatorioDinamicoService
								)
	{
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatorioService = relatorioService;
		this.relatorioDinamicoService = relatorioDinamicoService;
	}

	// Continuacao do metodo main()
	@Override
	public void run(String... args) throws Exception
	{
		Boolean keep = true;		
		Scanner scanner = new Scanner(System.in);

		while (keep)
		{
			System.out.println("Qual função deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("4 - Unidade");
			System.out.println("5 - Relatorios");
			System.out.println("6 - Relatorios Dinamicos");

			
			Integer function = scanner.nextInt();

			switch (function)
			{
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					funcionarioService.inicial(scanner);
					break;
				case 4:
					unidadeTrabalhoService.inicial(scanner);
					break;
				case 5:
					relatorioService.inicial(scanner);
					break;
				case 6:
					relatorioDinamicoService.inicial(scanner);
					break;
				default:
					System.out.println("Finalizando");
					keep = false;
					break;
			}
		}
	}

	// Metodo main()
	public static void main(String[] args)
	{
		SpringApplication.run(SpringDataApplication.class, args);
	}

}