package uz.shoxrux.news.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

@RestController
@RequestMapping("/v2")
@RequiredArgsConstructor
public class AdminController {

    final NewsService newsService;

    //    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNews(@RequestParam(required = false, value = "file") MultipartFile file, @ModelAttribute NewsDto newsDto) throws IOException {
        ApiResponse response = newsService.addNews(newsDto, file);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    //    @PreAuthorize(value = "hasRole('ADMIN')")
    @PatchMapping("/status")
    public ResponseEntity<?> UpdateStatus(@RequestBody ApproveAndDeleteDto appDto) {
            ApiResponse response = newsService.updateStatus(appDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    //    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getToAdmin(@PathVariable Long userId, @RequestParam("page") Integer page,
                                      @RequestParam("size") Integer size) {
        ApiResponse response = newsService.getToAdmin(page, size);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    //    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> EditNews(@RequestHeader("Accept-language") String lang,
                                      @PathVariable Long id,
                                      @RequestBody NewsDto newsDto) {
        ApiResponse response = newsService.editNews(lang, id, newsDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
}
