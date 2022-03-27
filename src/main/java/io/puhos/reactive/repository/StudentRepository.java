package io.puhos.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import io.puhos.reactive.entity.Student;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    public Flux<Student> findByName(String name);
    
    


}
