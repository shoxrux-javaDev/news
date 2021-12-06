package uz.shoxrux.news.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import uz.shoxrux.news.entity.template.AbsMain;
import uz.shoxrux.news.enums.NewsStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@ToString
public class News extends AbsMain {

    @Id
    @SequenceGenerator(name = "newsSeq",sequenceName = "pk_user_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newsSeq")
    @Column(name = "id")
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "accept_lang")
    private String acceptLanguage;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "newsUser",joinColumns = @JoinColumn(columnDefinition = "news_id"))
    @Column(name = "userId")
    private List<Long> userId;

    @Column(name = "title_uz",length =600,columnDefinition = "varchar(600)")
    private String titleUz;

    @Column(name = "text_uz",length = 8000,columnDefinition = "text")
    private String textUz;

    @Column(name = "title_ru",length = 600,columnDefinition = "varchar(600)")
    private String titleRu;

    @Column(name = "text_ru",length =8000,columnDefinition = "text")
    private String textRu;

    @Column(name="title_eng",length = 600,columnDefinition = "varchar(600)")
    private String titleEng;

    @Column(name = "text_eng",length =8000,columnDefinition = "text")
    private String textEng;

    @Enumerated(value = EnumType.STRING)
    private NewsStatus status;
}
