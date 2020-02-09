package texas.sbv.game.person;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
	public Person findByUseridAndPasswd (String userid, String passwd);
	public Person findByUserid (String userid);
	@Query(value="select * from person group by", nativeQuery=true)
	public List<Person> findGroupByJob();
	public List<Person> findByJob(String job);
	
}
