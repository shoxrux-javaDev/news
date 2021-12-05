package uz.shoxrux.news.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDto {

    @JsonProperty("titleUz")
    private String titleUz;

    @JsonProperty("textUz")
    private String textUz;

    @JsonProperty("titleRu")
    private String titleRu;

    @JsonProperty("textRu")
    private String textRu;

    @JsonProperty("titleEng")
    private String titleEng;

    @JsonProperty("textEng")
    private String textEng;

    @JsonProperty("userId")
    private Long userId;

}
