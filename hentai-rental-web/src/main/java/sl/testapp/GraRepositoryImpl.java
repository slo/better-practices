package sl.testapp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class GraRepositoryImpl implements GraRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Gra> getGamesNamedLike(String name) {
		Query query = entityManager.createNativeQuery(
				"Select graX.* from Gra graX where graX.name like ?", Gra.class);
		query.setParameter(1, name + "%");
		return query.getResultList();
	}
}
