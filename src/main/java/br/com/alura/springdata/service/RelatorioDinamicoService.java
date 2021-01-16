package br.com.alura.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.springdata.modelo.Funcionario;
import br.com.alura.springdata.repository.FuncionarioRepository;
import br.com.alura.springdata.specification.SpecificationFuncionario;

@Service
public class RelatorioDinamicoService
{
	// Parametros da Classe
	private final DateTimeFormatter padraoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;

	public RelatorioDinamicoService(FuncionarioRepository funcionarioRepository)
	{
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner)
	{
		Boolean keep = true;
		while (keep)
		{
			System.out.println("Qual relatorio dinamico deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Relatorio dinamico de Funcionarios");

			int action = scanner.nextInt();

			switch (action)
			{
				case 1:
					relatorioDinamicoFuncionario(scanner);
					break;
				default:
					keep = false;
					break;
			}
		}
	}

	private void relatorioDinamicoFuncionario(Scanner scanner)
	{
		System.out.println("Digite o nome");
		String nome = scanner.next();

		if (nome.equalsIgnoreCase("NULL"))
		{
			nome = null;
		}

		System.out.println("Digite o cpf");
		String cpf = scanner.next();

		if (cpf.equalsIgnoreCase("NULL"))
		{
			cpf = null;
		}

		System.out.println("Digite o Salario");
		Double salario = scanner.nextDouble();

		if (salario == 0)
		{
			salario = null;
		}

		System.out.println("Digite o data de contratacao");
		String data = scanner.next();

		LocalDate dataContratacao;
		if (data.equalsIgnoreCase("NULL"))
		{
			dataContratacao = null;
		}
		else
		{
			dataContratacao = LocalDate.parse(data, padraoData);
		}

		List<Funcionario> funcionarios = funcionarioRepository
				.findAll(Specification.where(
						SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
						);
		funcionarios.forEach(System.out::println);
	}
}
