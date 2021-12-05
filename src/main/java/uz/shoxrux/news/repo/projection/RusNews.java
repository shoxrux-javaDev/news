package uz.shoxrux.news.repo.projection;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public interface RusNews {

    String ruTitle();

    String ruText();

    ZonedDateTime ruTime();

    String ruShortText();

}
