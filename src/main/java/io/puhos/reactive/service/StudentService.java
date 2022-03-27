package io.puhos.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import io.puhos.reactive.entity.Student;
import io.puhos.reactive.repository.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    DatabaseClient client;

    public StudentService() {
    }
 
    public Flux<Student> findStudentsByName(String name) {
        return (name != null) ? studentRepository.findByName(name) : studentRepository.findAll();
    }

    public Mono<Student> findStudentById(long id) {
        return studentRepository.findById(id);
    }

    public Mono<Student> addNewStudent(Student student) {
        return studentRepository.save(student);
    }

    public Mono<Student> updateStudent(long id, Student student) {
        return studentRepository.findById(id)
                .flatMap(s -> {
                    student.setId(s.getId());
                    return studentRepository.save(student);
                });

    }

    public Mono<Void> deleteStudent(Student student) {
        return studentRepository.delete(student);
    }
    
    public Flux<Student> findAll() {
     //   DatabaseClient client = DatabaseClient. create(connectionFactory);
        return client.sql("select * from student")
                .map(row -> new Student(row.get("id", Long.class),
                        row.get("name", String.class),
                        row.get("address", String.class))).all();
 }

}
