package br.com.alura.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.modelo.Funcionario;
import br.com.alura.springdata.projecao.FuncionarioProjecao;
import br.com.alura.springdata.repository.FuncionarioRepository;

@Service
public class RelatorioService
{
	// Parametros da Classe
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter padraoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioService(FuncionarioRepository funcionarioRepository)
	{
		this.funcionarioRepository = funcionarioRepository;
	}
	
	// Metodo que escolhe o tipo de servico
	public void inicial(Scanner scanner)
	{
		Boolean keep = true;		
		while (keep)
		{
			System.out.println("Qual acao de relatorio deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario nome");
			System.out.println("2 - Busca funcionario nome, data contratacao e salario maior");
			System.out.println("3 - Busca funcionario data contratacao");
			System.out.println("4 - Projecao do funcionario: Id, Nome e Salario");

			int action = scanner.nextInt();

			switch (action)
			{
				case 1:
					buscaPorNome(scanner);
					break;
				case 2:
					buscaPorNomeSalarioMaiorDataContratacao(scanner);
					break;
				case 3:
					buscaFuncionarioDataContratacao(scanner);
					break;
				case 4:
					projecaoIdNomeSalario();
					break;
				default:
					keep = false;
					break;
			}
		}
	}

	private void buscaPorNome(Scanner scanner)
	{
		System.out.println("Qual o nome do funcionario?");
		String nome = scanner.next();
		List<Funcionario> funcionariosEncontrados = funcionarioRepository.findByNome(nome);
		for (Funcionario funcionario : funcionariosEncontrados)
		{
			System.out.println(funcionario);
		}		
	}
	
	private void buscaPorNomeSalarioMaiorDataContratacao(Scanner scanner)
	{
		System.out.println("Qual o nome do funcionario?");
		String nome = scanner.next();
		
		System.out.println("Qual a data de contratacao?");
		String data = scanner.next();
		LocalDate dataFormatada = LocalDate.parse(data, padraoData);
		
		System.out.println("Qual o salario do funcionario?");
		Double salario = scanner.nextDouble();		
		
		List<Funcionario> funcionariosEncontrados = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, dataFormatada);
		for (Funcionario funcionario : funcionariosEncontrados)
		{
			System.out.println(funcionario);
		}	
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner)
	{
		System.out.println("Qual data contratacao deseja pesquisar");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, padraoData);

		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	private void projecaoIdNomeSalario()
	{
		List<FuncionarioProjecao> list = funcionarioRepository.findProjecaoIdNomeSalario();
		for (FuncionarioProjecao projecao : list)
		{
			System.out.println("Funcionario ID: " + projecao.getId() + " Nome: " + 
							projecao.getNome() + " Salario: " + projecao.getSalario());
		}
	}
}
