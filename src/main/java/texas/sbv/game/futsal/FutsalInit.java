package texas.sbv.game.futsal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import texas.sbv.game.proxy.CrawlProxy;
import texas.sbv.game.proxy.Proxy;



@Component
public class FutsalInit extends Proxy implements ApplicationRunner{
	private FutsalRepository futsalRepository;
	@Autowired CrawlProxy crawler;
	
	@Autowired
	public FutsalInit(FutsalRepository futsalRepository) {
		this.futsalRepository = futsalRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = futsalRepository.count();
		Futsal match = null;
		List<Map<String, String>> stadiumList = new ArrayList<>();
		List<Futsal> matchList = new ArrayList<Futsal>();
		if(count < 3000) {
			for(int i = 1; i<=3; i++) {
				stadiumList.addAll(crawler.crawlFutMatch(i));
			}
			for(int i = 0; i<=1000; i++) {
				int ranIndex = random(1, stadiumList.size()-1);
				match = new Futsal();
				match.setGameTime(System.currentTimeMillis()-(random(1,480)-240)*1000*3600);
				match.setStadiumName(stadiumList.get(ranIndex).get("name"));
				match.setStadiumAddr(stadiumList.get(ranIndex).get("address"));
				match.setStadiumTel(stadiumList.get(ranIndex).get("tel"));
				match.setStadiumNum(random(4, 6));
				match.setGender(Arrays.asList("male","female","male").get(random(1,2)));
				match.setGameDifficulty(random(1, 3));
				match.setShoes("shoes0");
				match.setStadiumFacility(String.format("size1,shower%s,park%s,shoes%s,wear%s"
						,random(0,1),random(0,1),random(0,1),random(0,1)));
				match.setStadiumImage("1,2,3");
				match.setRemain(random(1, 14));
				match.setAdminName("펭수");
				matchList.add(match);
			}
			futsalRepository.saveAll(matchList);
		}
	}
}
