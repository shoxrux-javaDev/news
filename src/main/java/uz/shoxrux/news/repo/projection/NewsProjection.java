package uz.shoxrux.news.repo.projection;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface NewsProjection {

    String getUzTitle();

    String getUzText();

    String getEngTitle();

    String getEngText();

    String getRuTitle();

    String getRuText();

    String getNewsShortText();

    ZonedDateTime getNewsTime();

}
