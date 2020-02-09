package texas.sbv.game.reservation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import texas.sbv.game.futsal.FutsalRepository;
import texas.sbv.game.person.Person;
import texas.sbv.game.person.PersonRepository;
import texas.sbv.game.util.Constants;
import texas.sbv.game.util.Printer;

@RestController
@RequestMapping("/res")
@CrossOrigin(origins = Constants.S3URL)
public class ReservationController {
	@Autowired private ReservationRepository reservationRepository;
	@Autowired private PersonRepository personRepository;
	@Autowired private Reservation reservation;
	@Autowired private FutsalRepository futsalRepository;
	@Autowired ModelMapper modelMapper;
	@Autowired private Printer p;
	@GetMapping("/1")
	public List<Reservation> reslist(){
		p.accept("res 컨트롤러 들어옴");
		Iterable<Reservation> res = reservationRepository.findAll();
		List<Reservation> list1 = new ArrayList<>();
		for(Reservation r : res) {
			Reservation dto1 = modelMapper.map(r, Reservation.class);
			list1.add(dto1);
		}
		return list1.stream().collect(Collectors.toList());
	}
	

	 @GetMapping("/todaylist")
	   public List<Reservation> filterList(){
	      Iterable<Reservation> res = reservationRepository.findAll(); // 대문자 수정!
	      List<Reservation> list2 = new ArrayList<>();
	      for(Reservation r : res) {
	         Reservation dto1 = modelMapper.map(r, Reservation.class);
	         list2.add(dto1);
	      }
	      SimpleDateFormat sdf = new SimpleDateFormat("d");
          System.out.println("오늘날짜 ====>>>>>"+sdf.format(new Date())); // test commit!2

	      return list2.stream()
	    		  .sorted(Comparator.comparing(Reservation::getResSeq).reversed())
	    		  .filter(t-> sdf.format(new Date(t.getResDate())).equals(sdf.format(new Date())) ) //&& t.getResdate() < new Date().getTime()
	    		  .collect(Collectors.toList());
	   }

	
	@PostMapping("/{matchId}")
	public boolean createReservation(@PathVariable Long matchId, @RequestBody Person person) {
		reservation.setPerson(person);
		reservation.setFutsal(futsalRepository.findById(matchId).get());
		reservation.setResDate(System.currentTimeMillis());
		reservationRepository.save(reservation);
		return reservationRepository.findByResDate(reservation.getResDate()) != null;
	}
	
	@PutMapping("/{resseq}")
	public boolean updateReservation(@PathVariable Long resseq, @RequestBody Reservation reservation) {
		Reservation res = reservationRepository.findById(resseq).get();
		res.setKm(reservation.getKm());
		res.setWin(reservation.getWin());
		res.setScore(reservation.getScore());
		reservationRepository.save(res);
		return reservationRepository
				.findByResDate(res.getResDate()).getKm() == reservation.getKm();
	}

}