package uz.shoxrux.news.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;
import uz.shoxrux.news.dto.ApiResponse;
import uz.shoxrux.news.dto.ApproveAndDeleteDto;
import uz.shoxrux.news.dto.NewsDto;

import java.io.IOException;

public interface NewsService {

    ApiResponse addNews(String newsDto, MultipartFile file) throws IOException;

    ApiResponse addApprovedNews(ApproveAndDeleteDto appDto);

    ApiResponse getAllNews(String lang, Integer page, Integer size, Long userId);

    ApiResponse editNews(String lang, Long id, NewsDto newsDto);

    ApiResponse deleteNews(ApproveAndDeleteDto appDto);

    ApiResponse getNewsForADM(Integer page, Integer size);
}
