package uz.shoxrux.news.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;
import uz.shoxrux.news.dto.ApiResponse;
import uz.shoxrux.news.dto.ApproveAndDeleteDto;
import uz.shoxrux.news.dto.NewsDto;

import java.io.IOException;

public interface NewsService {

    ApiResponse addNews(NewsDto newsDto, MultipartFile file) throws IOException;

    ApiResponse updateStatus(ApproveAndDeleteDto appDto);

    ApiResponse getToUser(String lang, Integer page, Integer size, Long userId);

    ApiResponse editNews(String lang, Long id, NewsDto newsDto);

    ApiResponse getToAdmin(Integer page, Integer size);
}
