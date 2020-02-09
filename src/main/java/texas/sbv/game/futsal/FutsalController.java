package texas.sbv.game.futsal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import texas.sbv.game.util.Constants;

@RestController
@RequestMapping("/futsal")
@CrossOrigin(origins = Constants.S3URL)
public class FutsalController {
	@Autowired FutsalRepository futsalRepository;
	@Autowired FutsalService futsalMatchService;
	@Autowired ModelMapper futModelMapper;
	@Autowired Futsal fut;
	@Bean
	public ModelMapper futModelMapper() {return new ModelMapper();}
	
	@GetMapping("/")
	public List<Futsal> findAll(){
		Iterable<Futsal> all = futsalRepository.findAll();
		List<Futsal> list = new ArrayList<>();
		for(Futsal match : all) {
			Futsal dto = futModelMapper.map(match, Futsal.class);
			list.add(dto);
		}
		System.out.println("findAll");
		return list.stream().collect(Collectors.toList());
	}
	
	@PostMapping("/insertdummy")
	public void insertDummy(@RequestBody List<Futsal> param) {
		futsalRepository.saveAll(param);
		System.out.println("insertdummy");
	}
	
	@GetMapping("/match/{matchId}")
	public Futsal selectMatch(@PathVariable Long matchId) {
		return futsalRepository.findById(matchId).get();
	}
	
	@PutMapping("/match/{matchId}")
	public void updateMatch(@PathVariable Long matchId){
		fut = futsalRepository.findById(matchId).get();
		fut.setRemain(fut.getRemain()-1);
		futsalRepository.save(fut);
	}
	
	@PostMapping("/register")
	public boolean createMath(@RequestBody Futsal match) {
		futsalRepository.save(match);
		return true;
	}
	
	@GetMapping("/test")
	public Map<?, ?> test() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "asdfasdf");
		return map;
	}
}
