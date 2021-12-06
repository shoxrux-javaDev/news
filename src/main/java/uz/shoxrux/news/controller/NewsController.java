package uz.shoxrux.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.shoxrux.news.dto.ApiResponse;
import uz.shoxrux.news.dto.ApproveAndDeleteDto;
import uz.shoxrux.news.dto.NewsDto;
import uz.shoxrux.news.service.NewsService;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class NewsController {

    final NewsService newsService;

//    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getToUser(@PathVariable Long userId,@RequestHeader("Accept-language") String lang,
                                                                @RequestParam("page") Integer page,
                                                                @RequestParam("size") Integer size) {
        ApiResponse response = newsService.getToUser(lang, page, size,userId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
