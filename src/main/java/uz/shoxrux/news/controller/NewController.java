package uz.shoxrux.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.shoxrux.news.dto.ApiResponse;
import uz.shoxrux.news.dto.ApproveAndDeleteDto;
import uz.shoxrux.news.dto.NewsDto;
import uz.shoxrux.news.service.NewsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class NewController {

    final NewsService newsService;

//    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping(value = "/new")
    public ResponseEntity<?> add(@RequestParam(required = false,value = "file") MultipartFile file, @RequestParam String dtoNews) throws JsonProcessingException {
        ApiResponse response = newsService.addNews(dtoNews, file);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }


//    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping("/like_app")
    public ResponseEntity<?> addApprove(@RequestBody ApproveAndDeleteDto appDto) {
        ApiResponse response = newsService.addApprovedNews(appDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    @GetMapping("/all/{userId}")
    public ResponseEntity<?> getForUser(@PathVariable Long userId,@RequestHeader("Accept-language") String lang,
                                                                @RequestParam("page") Integer page,
                                                                @RequestParam("size") Integer size) {
        ApiResponse response = newsService.getAllNews(lang, page, size,userId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

//    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/for_adm/{userId}")
    public ResponseEntity<?> getForADM(@PathVariable Long userId,@RequestParam("page") Integer page,
                                                                      @RequestParam("size") Integer size) {
        ApiResponse response = newsService.getNewsForADM(page,size);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

//    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/body/{id}")
    public ResponseEntity<?> put(@RequestHeader("Accept-language") String lang,
                                                         @PathVariable Long id,
                                                         @RequestBody NewsDto newsDto) {
        ApiResponse response = newsService.editNews(lang, id, newsDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

//    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping("/fr_db")
    public ResponseEntity<?> delete(@RequestBody ApproveAndDeleteDto appDto) {
        ApiResponse response = newsService.deleteNews(appDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
