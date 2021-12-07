package uz.shoxrux.news.repo.projection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;


@JsonInclude(JsonInclude.Include.NON_NULL)
public interface NewsProjection {

    String getTitle();

    String getText();

    String getNewsShortText();

    Instant getTime();

}
