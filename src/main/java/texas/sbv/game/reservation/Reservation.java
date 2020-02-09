package texas.sbv.game.reservation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.game.futsal.Futsal;
import texas.sbv.game.person.Person;
import texas.sbv.game.proxy.Proxy;


@Component
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@Table(name = "reservation")
public class Reservation extends Proxy implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "res_seq") private Long resSeq;
	@Column(name = "res_date") @NotNull private Long resDate;
	@Column(name = "win") private String win;
	@Column(name = "km") private int km;
	@Column(name = "score") private int score;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_seq") private Person person;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "futsal_seq") private Futsal futsal;
	
	@Builder
	private Reservation(@NotNull Long resSeq, @NotNull Long resDate, String win, int km,
			int score) {
		this.resSeq = resSeq;
		this.resDate = resDate;
		this.win = win;
		this.km = km;
		this.score = score;

	}

}
