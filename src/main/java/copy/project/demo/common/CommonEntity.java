package copy.project.demo.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Created by SungHui on 2025. 1. 23.
 */

/* 공통 엔티티 */
@Getter
@Setter
@MappedSuperclass
public abstract class CommonEntity {

    @Column(name = "created_by", updatable = false) // 업데이트(수정) 불가
    private String createdBy; // 등록자

    @CreationTimestamp // 엔티티가 처음 생성될 때 한 번만 값이 설정
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 회원 등록 일자

    @Column(name = "updated_by")
    private String updatedBy; // 수정자

    @UpdateTimestamp // 엔티티가 변경될 때마다 자동으로 갱신
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 회원 수정 일자
}
