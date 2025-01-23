package copy.project.demo.entity;

import copy.project.demo.entity.common.CommonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by SungHui on 2025. 1. 23.
 */

@Entity
@Getter
@Setter
public class Review extends CommonEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "accommodation_id", nullable = false)
    private Accommodation accommodation;

    @Column(name = "rating", nullable = false)
    private byte rating;

    @Column(name = "comment")
    private String comment;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", member=" + member +
                ", accommodation=" + accommodation +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
