package sl.testapp;

import java.util.List;

public interface GraRepositoryCustom {
	List<Gra> getGamesNamedLike(final String name);
}
