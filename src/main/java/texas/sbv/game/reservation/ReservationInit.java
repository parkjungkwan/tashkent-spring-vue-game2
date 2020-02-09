package texas.sbv.game.reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import texas.sbv.game.futsal.Futsal;
import texas.sbv.game.futsal.FutsalRepository;
import texas.sbv.game.person.Person;
import texas.sbv.game.person.PersonRepository;
import texas.sbv.game.proxy.Proxy;


@Component
public class ReservationInit extends Proxy implements ApplicationRunner  {
	private ReservationRepository reservationRepository;
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public ReservationInit(ReservationRepository reservationrepository) {
		this.reservationRepository = reservationrepository;
	}
	@Autowired private PersonRepository personRepository;
	@Autowired private FutsalRepository futsalRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = reservationRepository.count();
		int initdummy = 1000;
		if(count < 100) {
			dummyres(initdummy);
		}
	}
	public void dummyres(int dummycount) {
		Futsal fut = null;
		Person person = null;
		Reservation res = null;
		int personCount = (int) personRepository.count();
		int futsalCount = (int) futsalRepository.count();
		List<Reservation> resList = new ArrayList<Reservation>();
		for(int i=0; i< dummycount; i++) {
			res = new Reservation();
			fut = new Futsal();
			person = new Person();
			res.setResDate(System.currentTimeMillis()+random(-7, 14*3600*1000*24));
			fut.setFutsalSeq((long)random(1,futsalCount-1));
			person.setPersonSeq((long) random(1, personCount-1));
			res.setFutsal(fut);
			res.setPerson(person);
			res.setKm(random(5,20));
			res.setScore(random(0,4));
			res.setWin(Arrays.asList("win","lose").get(random(0,1)));
			resList.add(res);
		}
		reservationRepository.saveAll(resList);
	}
}
