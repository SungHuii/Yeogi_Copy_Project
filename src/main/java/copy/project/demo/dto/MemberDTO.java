package copy.project.demo.dto;

import copy.project.demo.entity.enums.MemberGender;
import copy.project.demo.entity.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 1. 24.
 */


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;
    private MemberType type;
    private String name;
    private String nickname;
    private String phone;
    private MemberGender gender;
    private LocalDate birthDate;

}
