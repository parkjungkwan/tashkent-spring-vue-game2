package texas.sbv.game.futsal;

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
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.game.reservation.Reservation;

@Component
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@ToString
@Table(name = "futsal")
public class Futsal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "futsal_seq") private Long futsalSeq;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "res_seq") private Long resSeq;
	@NotNull @Column(name = "game_time") private Long gameTime;
	@NotNull @Column(name = "stadium_name") private String stadiumName;
	@NotNull @Column(name = "stadium_addr") private String stadiumAddr;
	@NotNull @Column(name = "stadium_tel") private String stadiumTel;
	@NotNull @Column(name = "stadium_num") private int stadiumNum;
	@NotNull @Column(name = "gender") private String gender;
	@NotNull @Column(name = "game_difficult") private int gameDifficulty;
	@NotNull @Column(name = "shoes") private String shoes;
	@NotNull @Column(name = "stadium_facility") private String stadiumFacility;
	@NotNull @Column(name = "stadium_image") private String stadiumImage;
	@NotNull @Column(name = "remain") private int remain;
	@NotNull @Column(name = "admin_name") private String adminName;
	@JsonIgnore
	@OneToMany(mappedBy = "futsal", cascade = CascadeType.ALL, orphanRemoval =true)
	private List<Reservation> reservations = new ArrayList<>();
	
	@Builder
	private Futsal(@NotNull Long futsalSeq, @NotNull Long gameTime, @NotNull String stadiumName,
			@NotNull String stadiumAddr, @NotNull String stadiumTel, @NotNull int stadiumNum, @NotNull String gender,
			@NotNull int gameDifficulty, @NotNull String shoes, @NotNull String stadiumFacility, @NotNull String stadiumImage,
			@NotNull int remain, @NotNull String adminName) {
		this.futsalSeq = futsalSeq;
		this.gameTime = gameTime;
		this.stadiumName = stadiumName;
		this.stadiumAddr = stadiumAddr;
		this.stadiumTel = stadiumTel;
		this.stadiumNum = stadiumNum;
		this.gender = gender;
		this.gameDifficulty = gameDifficulty;
		this.shoes = shoes;
		this.stadiumFacility = stadiumFacility;
		this.stadiumImage = stadiumImage;
		this.remain = remain;
		this.adminName = adminName;
	}
}
