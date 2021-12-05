package uz.shoxrux.news.repo.projection;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public interface EngNews {

    String engTitle();

    String engText();

    ZonedDateTime engTime();

    String engShortText();
}
