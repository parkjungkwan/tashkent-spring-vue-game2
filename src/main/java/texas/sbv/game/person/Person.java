package texas.sbv.game.person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.game.proxy.Proxy;
import texas.sbv.game.reservation.Reservation;
@Component
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
@Table(name="person", 
	uniqueConstraints = 
	{@UniqueConstraint
			(columnNames = {"userid"})})
public class Person extends Proxy implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="person_seq")private Long personSeq;
	@Column(name="userid", length= 64 ) @NotNull private String userid;
	@Column(name="passwd") @NotNull private String passwd;
	@Column(name="name") @NotNull private String name;
	@Column(name="tel") @NotNull private String tel;
	@Column(name="point") private String point;
	@Column(name="age")@NotNull private int age;
	@Column(name="male")@NotNull private boolean male;
	@Column(name="score") private int score;
	@Column(name="mvp") private int mvp;
	@Column(name="win") private int win;
	@Column(name="km") private int km;
	@Column(name="bookmark") private boolean bookmark;
	@Column(name="interest") @NotNull private String interest;
	@Column(name="lol_black") private boolean lolBlack;
	@Column(name="fut_black") private boolean futBlack;
	@Column(name="job")@NotNull private String job;
	@Column(name="email")@NotNull private String email;
	@Column(name="summoner_name") private String summonerName;
	enum Level{HIGH, MID, LOW}
	
	@JsonIgnore
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Reservation> reservations = new ArrayList<>();
	 

	@Builder
	private Person(String userid, String name, String passwd, String tel,
			 String point, int age, boolean male, int score, int mvp,
			 int win, int km, boolean bookmark, String interest,
			 boolean lolBlack, boolean futBlack, String job, String email, String summonerName) {
		
		this.userid = userid;
		this.name = name;
		this.passwd = passwd;
		this.tel = tel;
		this.point = point;
		this.age = age;
		this.male = male;
		this.score = score;
		this.mvp = mvp;
		this.win = win;
		this.km = km;
		this.bookmark = bookmark;
		this.interest = interest;
		this.lolBlack = lolBlack;
		this.futBlack = futBlack;
		this.job = job;
		this.email = email;
		this.summonerName = summonerName;
	}
}