package uz.shoxrux.news.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.shoxrux.news.entity.News;
import uz.shoxrux.news.repo.projection.EngNews;
import uz.shoxrux.news.repo.projection.RusNews;
import uz.shoxrux.news.repo.projection.UzbNews;

import java.util.List;

@Repository
public interface NewsRepo extends JpaRepository<News, Long> {

    @Query(value = "select n.UPDATED_AT as uzTime,n.TITLE_UZ as uzTitle,n.TEXT_UZ as uzText,concat(SUBSTR(n.TEXT_UZ,1,100),'...') as uzShortText from " +
            " NEWS n join NEWS_USER nu on n.ID = nu.NEWS_ID where  nu.USER_ID is null or nu.USER_ID = ? and n.STATUS ='APPROVED' order by n.CREATED_AT ", nativeQuery = true)
    Page<UzbNews> getUzNews(Long userId, Pageable pageable);

    @Query(value = "select n.UPDATED_AT as ruTime,n.TITLE_RU as ruTitle,n.TEXT_RU as ruText,concat(SUBSTR(n.TEXT_UZ,1,100),'...') as ruShortText from " +
            " NEWS n join NEWS_USER nu on n.ID = nu.NEWS_ID where nu.USER_ID is null or nu.USER_ID = ? and n.STATUS ='APPROVED' order by n.CREATED_AT ", nativeQuery = true)
    List<RusNews> getRuNews(Long userId, Pageable pageable);

    @Query(value = "select n.UPDATED_AT as engTime,n.TITLE_ENG as engTitle,n.TEXT_ENG as engText,concat(SUBSTR(n.TEXT_UZ,1,100),'...') as engShortText from " +
            " NEWS n join NEWS_USER nu on n.ID = nu.NEWS_ID where nu.USER_ID is null or nu.USER_ID = ? and n.STATUS ='APPROVED' order by n.CREATED_AT ", nativeQuery = true)
    List<EngNews> getEngNews(Long userId, Pageable pageable);

    @Query(value = "select * from News n join NEWS_USER NU on n.ID = NU.NEWS_ID where n.STATUS <> 'DELETED'  order by n.CREATED_AT ", nativeQuery = true)
    List<News> getNewsForAdm(Pageable pageable);

}
