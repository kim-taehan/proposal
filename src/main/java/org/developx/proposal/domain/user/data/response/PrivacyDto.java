package org.developx.proposal.domain.user.data.response;

import lombok.Builder;
import org.developx.proposal.domain.user.entity.Privacy;
import org.developx.proposal.domain.user.entity.enums.Gender;

import java.time.LocalDate;

public record PrivacyDto(
        Long privacyId,
        Gender gender,
        LocalDate birth,
        String address,
        String email,
        String mobile
) {
    @Builder
    public PrivacyDto(Long privacyId, Gender gender, LocalDate birth, String address, String email, String mobile) {
        this.privacyId = privacyId;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }

    private static PrivacyDto getInstance() {
        return PrivacyDto.builder()
                .gender(Gender.MALE)
                .build();
    }
    public static PrivacyDto from(Privacy privacy) {
        if (privacy == null) {
            return getInstance();
        }

        return PrivacyDto.builder()
                .privacyId(privacy.getId())
                .gender(privacy.getGender())
                .address(privacy.getAddress())
                .birth(privacy.getBirth())
                .email(privacy.getEmail())
                .mobile(privacy.getMobile())
                .build();
    }
}
