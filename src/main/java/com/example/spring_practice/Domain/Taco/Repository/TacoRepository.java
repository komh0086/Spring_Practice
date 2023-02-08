package com.example.spring_practice.Domain.Taco.Repository;

import com.example.spring_practice.Domain.Taco.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

}
