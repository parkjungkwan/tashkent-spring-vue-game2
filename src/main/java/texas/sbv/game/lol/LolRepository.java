package texas.sbv.game.lol;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LolRepository extends CrudRepository<Lol, Long>{
	public Lol findByCardSeq(Long cardSeq);
}
