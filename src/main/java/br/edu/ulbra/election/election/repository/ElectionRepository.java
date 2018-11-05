package br.edu.ulbra.election.election.repository;

import br.edu.ulbra.election.election.model.Election;
import org.springframework.data.repository.CrudRepository;

public interface ElectionRepository extends CrudRepository<Election, Long> {
    Election findFirstByYearAndStateCodeAndDescription(Integer year, String stateCode, String description);
}
