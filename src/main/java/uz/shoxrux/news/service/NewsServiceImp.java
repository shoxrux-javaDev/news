package uz.shoxrux.news.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.shoxrux.news.dto.ApiResponse;
import uz.shoxrux.news.dto.ApproveAndDeleteDto;
import uz.shoxrux.news.dto.NewsDto;
import uz.shoxrux.news.entity.News;
import uz.shoxrux.news.enums.EnumMessage;
import uz.shoxrux.news.enums.NewsStatus;
import uz.shoxrux.news.repo.NewsRepo;
import uz.shoxrux.news.repo.projection.NewsProjection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class NewsServiceImp implements NewsService {

    NewsRepo newsRepo;

    @Override
    public ApiResponse addNews(NewsDto newsDto, MultipartFile file) throws IOException {
//        Optional<News> optionalNews = newsRepo.findById(Long.valueOf(UUID.randomUUID().toString()));
//        if (optionalNews.isEmpty() && file.isEmpty() )
//            return new ApiResponse(EnumMessage.NOT_FOUND.toString());
        News newsModel = new News();
        addNewsForAnyField(newsDto, newsModel);
        if (file != null) {
            List<Long> userList = Arrays.stream(new String(file.getBytes(), StandardCharsets.UTF_8).split(","))
                    .map(String::trim)
                    .filter(str -> str.length() >= 2)
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
            newsModel.setUserId(userList);
        } else {
            List<Long> userId = new ArrayList<>();
            userId.add(-1L);
            newsModel.setUserId(userId);
        }
        newsModel.setStatus(NewsStatus.NEW);
        newsRepo.save(newsModel);
        return new ApiResponse(EnumMessage.SAVE_ALL.toString());
    }

    @Override
    public ApiResponse updateStatus(ApproveAndDeleteDto appDelDto) {
        Long[] ids = appDelDto.getIds();
        for (Long id : ids) {
            Optional<News> byId = newsRepo.findById(id);
            if (byId.isEmpty()) return new ApiResponse(EnumMessage.NOT_FOUND.toString());
            News newsModel = byId.get();
            if (appDelDto.getStatus().equals(NewsStatus.APPROVED)) {
                newsModel.setStatus(NewsStatus.APPROVED);
                newsRepo.save(newsModel);
            } else if (appDelDto.getStatus().equals(NewsStatus.DELETED)) {
                newsModel.setStatus(NewsStatus.DELETED);
                newsRepo.save(newsModel);
            } else {
                return new ApiResponse("not found,please,check request body");
            }
        }
        return new ApiResponse(EnumMessage.SUCCESS_APP.toString());
    }

    @Override
    public ApiResponse getToUser(String lang, Integer page, Integer size, Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        List<News> byId = newsRepo.getUserId_(userId);
        if (byId.isEmpty()) return new ApiResponse(EnumMessage.NOT_FOUND.toString());
        switch (lang) {
            case "uz" -> {
                Page<NewsProjection> uzNews = newsRepo.getUzNews(userId, pageable);
                return new ApiResponse(uzNews.toList());
            }
            case "eng" -> {
                Page<NewsProjection> engNews = newsRepo.getEngNews(userId, pageable);
                return new ApiResponse(engNews.toList());
            }
            default -> {
                Page<NewsProjection> ruNews = newsRepo.getRuNews(userId, pageable);
                return new ApiResponse(ruNews.toList());
            }
        }

    }

    @Override
    public ApiResponse getToAdmin(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<News> newsForAdm = newsRepo.getNewsForAdm(pageable);
        if (newsForAdm.isEmpty()) return new ApiResponse(EnumMessage.NOT_NULL_LIST.toString());
        return new ApiResponse(newsForAdm);
    }

    @Override
    public ApiResponse editNews(String lang, Long id, NewsDto newsDto) {
        Optional<News> byId = newsRepo.findById(id);
        if (byId.isEmpty()) return new ApiResponse(EnumMessage.SENDER_NOT.toString());
        Optional<News> optionalNews = newsRepo.findById(newsDto.getUserId());
        if (optionalNews.isEmpty() || id == 0) return new ApiResponse("userNewsId not found");
        News newsModel = byId.get();
        addNewsForAnyField(newsDto, newsModel);
        newsModel.setStatus(NewsStatus.NEW);
        newsRepo.save(newsModel);
        return new ApiResponse(EnumMessage.UPDATE.toString());

    }

    public void addNewsForAnyField(NewsDto newsDto, News news) {
        int count = 0;
        if (newsDto.getTitleRu().isEmpty() || newsDto.getTextRu().isEmpty()) count++;
        news.setTitleRu(newsDto.getTitleRu());
        news.setTextRu(newsDto.getTextRu());
        if (newsDto.getTitleUz().isEmpty() || newsDto.getTextUz().isEmpty()) count++;
        news.setTitleUz(newsDto.getTitleUz());
        news.setTextUz(newsDto.getTextUz());
        if (newsDto.getTitleEng().isEmpty() || newsDto.getTextEng().isEmpty()) count++;
        news.setTitleEng(newsDto.getTitleEng());
        news.setTextEng(newsDto.getTextEng());
        if (count == 3)
            new ApiResponse("news body couldn't be null");
    }


}
