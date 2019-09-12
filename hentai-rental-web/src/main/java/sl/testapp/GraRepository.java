package sl.testapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface GraRepository extends CrudRepository<Gra, Long>, GraRepositoryCustom {

}
