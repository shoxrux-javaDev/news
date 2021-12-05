package uz.shoxrux.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;

    private boolean isSuccess;

    private Object object;

    public ApiResponse(boolean isSuccess, String message) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public ApiResponse(boolean isSuccess, Object object) {
        this.isSuccess = isSuccess;
        this.object = object;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(Object object) {
        this.object = object;
    }
}
