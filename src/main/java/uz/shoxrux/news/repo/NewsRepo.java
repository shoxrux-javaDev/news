package uz.shoxrux.news.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.shoxrux.news.entity.News;
import uz.shoxrux.news.enums.NewsStatus;
import uz.shoxrux.news.repo.projection.NewsProjection;

import java.util.List;

@Repository
public interface NewsRepo extends JpaRepository<News, Long> {

    @Query(value = "select * from news n inner join news_user nu on n.id = nu.news_id where nu.user_id = :id ", nativeQuery = true)
    List<News> getUserId_(@Param("id") Long id);

//    @Query(value = "select cast(id as varchar),receive_phone_number,created_at,status,receive_amount_with_tax_ding,received_currency_iso,operator_name " +
//            "from payment\n where sender_id = :userId;", nativeQuery = true)
//    Page<IReportMobile> getAllReportWithUserIdForMobile(@Param("pageable") Pageable pageable, @Param("userId") String userId);

    @Query(value = "select n.updated_at time,n.title_uz title,n.text_uz text,concat(SUBSTR(n.text_uz,1,100),'...') as newsShortText from " +
            " news n join news_user nu on n.ID = nu.news_id where  nu.user_id = -1 or nu.user_id = (:userId) and n.status ='NEW' order by n.created_at ", nativeQuery = true)
    Page<NewsProjection> getUzNews(@Param("userId") Long userId, @Param("pageable") Pageable pageable);

    @Query(value = "select n.updated_at time,n.title_ru as title,n.TEXT_RU as ruText,concat(SUBSTR(n.text_ru,1,100),'...') as newsShortText from " +
            " news n join news_user nu on n.ID = nu.news_id where nu.user_id = -1 or nu.user_id = (:userId) and n.status ='NEW' order by n.created_at ", nativeQuery = true)
    Page<NewsProjection> getRuNews(@Param("userId") Long userId,@Param("pageable") Pageable pageable);

    @Query(value = "select n.updated_at time,n.title_eng title,n.text_eng text,concat(SUBSTR(n.text_eng,1,100),'...') as newsShortText from " +
            " news n join news_user nu on n.ID = nu.news_id where nu.user_id = -1 or nu.user_id = (:userId) and n.status ='NEW' order by n.created_at ", nativeQuery = true)
    Page<NewsProjection> getEngNews(@Param("userId") Long userId,@Param("pageable") Pageable pageable);

    @Query(value = "select * from News n join NEWS_USER NU on n.ID = NU.NEWS_ID where n.STATUS <> 'DELETED'  order by n.CREATED_AT ", nativeQuery = true)
    Page<News> getNewsForAdm(String deleted,Pageable pageable);

    Page<News> findAllByStatusNot(NewsStatus status, Pageable pageable);

}
