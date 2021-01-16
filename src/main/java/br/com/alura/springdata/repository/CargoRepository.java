package br.com.alura.springdata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.springdata.modelo.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer>
{
	@Transactional
	void deleteByDescricao(String descricao);
	
	boolean existsByDescricao(String descricao);
}
