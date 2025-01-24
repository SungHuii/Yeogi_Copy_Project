package copy.project.demo.dto;

import copy.project.demo.entity.enums.MemberGender;
import copy.project.demo.entity.enums.MemberType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * Created by SungHui on 2025. 1. 24.
 */

@Getter
@RequiredArgsConstructor
public class MemberDTO {

    private final Long id;
    private final MemberType type;
    private final String name;
    private final String nickname;
    private final String phone;
    private final MemberGender gender;
    private final LocalDate birthDate;

}
