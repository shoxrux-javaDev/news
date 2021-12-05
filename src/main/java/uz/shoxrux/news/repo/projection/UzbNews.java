package uz.shoxrux.news.repo.projection;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public interface UzbNews {

    String uzTitle();

    String uzText();

    ZonedDateTime uzTime();

    String uzShortText();

}
