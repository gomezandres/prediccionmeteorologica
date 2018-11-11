package com.ml.solarium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.solarium.model.entity.Clima;

@Repository
public interface ClimaRepository extends JpaRepository<Clima, Long> {

	public Clima findByDia(int dia);

	public List<Clima> findAllByClima(String clima);

}
