package br.com.alura.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.modelo.Cargo;
import br.com.alura.springdata.repository.CargoRepository;

@Service
public class CargoService
{	
	private final CargoRepository cargoRepository;

	public CargoService(CargoRepository cargoRepository)
	{
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner)
	{
		Boolean keep = true;
		while (keep)
		{
			System.out.println("Qual acao de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar por ID");
			System.out.println("5 - Deletar por descricao");
			System.out.println("6 - Verificar se cargo existe por descricao");

			int action = scanner.nextInt();

			switch (action)
			{
				case 1:
					salvar(scanner);
					break;
				case 2:
					atualizar(scanner);
					break;
				case 3:
					visualizar();
					break;
				case 4:
					deletarPorId(scanner);
				case 5:
					deletarPorDescricao(scanner);
					break;
				case 6:
					verificarExistenciaPorDescricao(scanner);
					break;
				default:
					keep = false;
					break;
			}
		}
	}

	private void salvar(Scanner scanner)
	{
		//scanner.nextLine();
		System.out.println("Descricao do cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner)
	{
		System.out.println("Id");
		
		int id = scanner.nextInt();
		System.out.println("Descricao do Cargo");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Atualizado");
	}

	private void visualizar()
	{
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}

	private void deletarPorId(Scanner scanner)
	{
		System.out.println("Qual o ID?");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	private void deletarPorDescricao(Scanner scanner)
	{
		System.out.println("Qual a descricao do cargo?");
		String descricao = scanner.next();
		cargoRepository.deleteByDescricao(descricao);
		System.out.println("Deletado");
	}
	
	private void verificarExistenciaPorDescricao(Scanner scanner)
	{
		System.out.println("Qual a descricao do cargo?");
		String descricao = scanner.next();
		boolean existencia = cargoRepository.existsByDescricao(descricao);
		if (existencia)
			System.out.println("Sim, este cargo existe");
		else
			System.out.println("Esse cargo nao existe");
		
	}
}
