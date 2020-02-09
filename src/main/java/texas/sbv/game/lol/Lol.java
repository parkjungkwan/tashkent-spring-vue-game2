package texas.sbv.game.lol;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import texas.sbv.game.proxy.Proxy;
@Component
@Entity
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@Table(name="lol",
   uniqueConstraints = 
   {@UniqueConstraint
         (columnNames = {"card_seq"})})

public class Lol extends Proxy implements Serializable{
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name="card_seq") private Long cardSeq;
   @Column(name="rhost") @NotNull private String rhost;
   @Column(name="rguest") private String rguest;
   @Column(name="lol_black") private String lolBlack;
   @Column(name="title") @NotNull private String title;
   @Column(name="contents") private String contents;
   @Column(name="tier") private String tier;
   @Column(name="image_url") @NotNull private String imageUrl;
   @Column(name="image") private String image;
   @Column(name="crawl_tier") private String crawlTier;
   @Column(name="crawl_rate") private String crawlRate;
   @Column(name="wtime") private Date wtime;
   @Column(name="position") private String position;
   
   
   @Builder
   private Lol(String rhost,String rguest, String lolBlack,
         String title, String contents, String tier,
         String imageUrl, String image, Date wtime, String position,
         String crawlTier, String crawlRate) {
      this.rhost = rhost;
      this.rguest = rguest;
      this.lolBlack = lolBlack;
      this.title = title;
      this.contents = contents;
      this.tier = tier;
      this.imageUrl = imageUrl;
      this.image = image;
      this.crawlTier = crawlTier;
      this.crawlRate = crawlRate;
      this.wtime = wtime;
      this.position = position;
   }
}