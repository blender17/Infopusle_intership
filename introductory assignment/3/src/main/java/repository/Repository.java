package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

	int save(T t);

	int saveAll(Iterable<T> ts);

	Optional<T> findById(ID Id);

	List<T> findAll();

	List<T> findAllById(Iterable<ID> ids);

	int delete(T t);

	int deleteById(ID id);

	int deleteAllByIdInBatch(Iterable<ID> ids);

	int deleteInBatch(Iterable<T> ts);

	int deleteAll();

}
