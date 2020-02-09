package texas.sbv.game.lol;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import texas.sbv.game.proxy.Box;
import texas.sbv.game.proxy.CrawlProxy;
import texas.sbv.game.proxy.Proxy;
import texas.sbv.game.proxy.Trunk;
import texas.sbv.game.util.Constants;
import texas.sbv.game.util.Printer;

@RestController
@RequestMapping("/lol")
@CrossOrigin(origins = Constants.S3URL)
public class LolController {
	@Autowired CrawlProxy crawler;
	@Autowired Trunk<Object> trunk;
	@Autowired Box<Object> box;
	@Autowired Proxy pxy;
	@Autowired Printer p;
	@Autowired ModelMapper modelMapper;
	@Autowired LolRepository lolRepository; // test commit
	
	@GetMapping("/summoner/userName={summonerName}")
	public ArrayList<HashMap<String, String>> opgg(@PathVariable String summonerName){
		return crawler.opggCrawling(summonerName);
	}
	
	@DeleteMapping("/delete/{cardSeq}")
	public void withdrawal(@PathVariable Long cardSeq) {
		lolRepository
		.delete(lolRepository
				.findByCardSeq(cardSeq));
	}
	@PutMapping("/update/{cardseq}")
	public void modify(@RequestBody Lol lol, @PathVariable Long cardSeq) {
		lol = lolRepository.save(lolRepository.findByCardSeq(cardSeq));
	}
	
	@GetMapping("/listpage={page}")
	public List<Lol> roomlist(@PathVariable int page){
		System.out.println(page);
		Iterable<Lol> entites = lolRepository.findAll();
		List<Lol> list = new ArrayList<>();
		Date date = new Date();
		for(Lol l : entites) {
			Lol dto = modelMapper.map(l,Lol.class);
			list.add(dto);
		}
		return list.stream()
				.sorted(Comparator.comparing(Lol::getCardSeq)
				.reversed()) 
				.limit(page*9) 
				.collect(Collectors.toList()); 
	}
	
	@GetMapping("/filterpositionlist/position={position}/page={page}")
	   public List<Lol> filterpositionlist(@PathVariable int page, @PathVariable String position){
	      System.out.println(page);
	      Iterable<Lol> entites = lolRepository.findAll();
	      List<Lol> list = new ArrayList<>();
	      Date date = new Date();
	      
	      for(Lol l : entites) {
	         Lol dto = modelMapper.map(l,Lol.class);
	         list.add(dto);
	      }
	      return list.stream()
	            .sorted(Comparator.comparing(Lol::getCardSeq)
	            .reversed())
	            .filter(role->role.getPosition().equals(position))
	            .limit(page*9) 
	            .collect(Collectors.toList()); 
	   }
	@GetMapping("/filtertierlist/tier={tier}/page={page}")
	   public List<Lol> filtertierlist(@PathVariable int page, @PathVariable String tier){
	      System.out.println(page);
	      Iterable<Lol> entites = lolRepository.findAll();
	      List<Lol> list = new ArrayList<>();
	      Date date = new Date();
	      
	      for(Lol l : entites) {
	         Lol dto = modelMapper.map(l,Lol.class);
	         list.add(dto);
	      }
	      return list.stream()
	            .sorted(Comparator.comparing(Lol::getCardSeq)
	            .reversed())
	            .filter(role->role.getTier().equals(tier))
	            .limit(page*9) 
	            .collect(Collectors.toList()); 
	   }
	   
	   @GetMapping("/filtertplist/tier={tier}/position={position}/page={page}")
	   public List<Lol> filtertplist(@PathVariable int page, @PathVariable String tier, @PathVariable String position){
	      Iterable<Lol> entites = lolRepository.findAll();
	      List<Lol> list = new ArrayList<>();
	      Date date = new Date();
	      
	      for(Lol l : entites) {
	         Lol dto = modelMapper.map(l,Lol.class);
	         list.add(dto);
	      }
	      return list.stream()
	            .filter(role->role.getTier().equals(tier))
	            .filter(role->role.getPosition().equals(position))
	            .sorted(Comparator.comparing(Lol::getCardSeq)
	            .reversed())
	            .limit(page*9) 
	            .collect(Collectors.toList()); 
	   }
	@PostMapping("/createroom")
	public HashMap<String, Object> createroom(@RequestBody Lol lol){
		HashMap<String, Object> map = new HashMap<>();
		String[] img = {
				"ahri",
				"Ashe",
				"garen",
				"jarvaniv",
				"Jinx",
				"Leona",
				"Lux",
				"missfortune",
				"neeko",
				"sona",
				"Teemo",
				"xayah",
				"xinzhao",
				"Yasuo",
				"zoe",
		};
		
		lol.setImageUrl(img[pxy.random(0, 14)]);
		lol = lolRepository.save(lol);
		if(lol != null) {
			map.put("result","SUCCESS");
			map.put("lol",lol);
		}else {
			map.put("result","FAIL");
		}
		return map;
	}
}

	