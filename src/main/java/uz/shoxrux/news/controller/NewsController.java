package uz.shoxrux.news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.shoxrux.news.dto.ApiResponse;
import uz.shoxrux.news.service.NewsService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class NewsController {
    @PersistenceUnit
    private EntityManagerFactory emf;
    final NewsService newsService;

//    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getToUser(@PathVariable Long userId,@RequestHeader("Accept-language") String lang,
                                                                @RequestParam("page") Integer page,
                                                                @RequestParam("size") Integer size) {
        ApiResponse response = newsService.getToUser(lang, page, size,userId);
        EntityManager list = emf.createEntityManager();
        list.createQuery("select n from News n where n.userId = 0 ");
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
