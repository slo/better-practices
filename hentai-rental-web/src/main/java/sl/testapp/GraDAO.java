package sl.testapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface GraDAO extends CrudRepository<Gra, Long> {

}
