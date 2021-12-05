package uz.shoxrux.news.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveAndDeleteDto {

    @JsonProperty("ids")
    private Long[] ids;

    @JsonProperty("status")
    private String status;
}
