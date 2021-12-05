package uz.shoxrux.news.entity.template;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsMain {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    protected ZonedDateTime updatedAt;

    @CreatedBy
    @JoinColumn(updatable = false)
    protected Long createdBy;

    @LastModifiedBy
    protected Long updatedBy;

}
