package org.developx.proposal.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.domain.user.entity.enums.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Privacy {

    @Id
    @GeneratedValue
    @Column(name = "privacy_id")
    private Long id;

    @Enumerated
    @Column(length = 10)
    private Gender gender;

    private LocalDate birth;

    // 서울시 광진구
    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String email;

    @Column(length = 11)
    private String mobile;

    @Builder
    public Privacy(Gender gender, LocalDate birth, String address, String email, String mobile) {
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }

    public String getDisplayMobile() {

        if (mobile.length() < 10) {
            return mobile;
        }

        final int splitCount = mobile.length() == 10 ? 6 : 7;
        return String.format("%s-%s-%s", mobile.substring(0, 3), mobile.substring(3, splitCount), mobile.substring(splitCount));
    }

    // 주민등록번호에 앞에 자리 형태를 조회 한다
    // 850312
    public String getSsnFrontFormat() {
        return birth.format(DateTimeFormatter.ofPattern("yyMMdd"));
    }

    // 이력서에 첨부되는 주민등록번호 형태를 가져온다 (yymmdd-1)
    public String getSsnFormat() {
        return String.format("%s-%s"
                ,birth.format(DateTimeFormatter.ofPattern("yyMMdd"))
                ,gender.getGenderCode(birth)
        );
    }

}
