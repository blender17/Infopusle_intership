package repository;

import model.Subject;

import java.util.Optional;

public interface SubjectRepository extends Repository<Subject, Long>{

	Optional<Subject> findByName(String name);

}
