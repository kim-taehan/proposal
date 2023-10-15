package org.developx.proposal.domain.user.data.response;

import org.developx.proposal.SpringBootTestSupport;
import org.developx.proposal.domain.user.entity.Privacy;
import org.developx.proposal.domain.user.entity.enums.Gender;
import org.developx.proposal.domain.user.repository.PrivacyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[record] privacy dto")
class PrivacyDtoTest extends SpringBootTestSupport {

    @Autowired
    private PrivacyRepository privacyRepository;


    @DisplayName("privacy entity로 PrivacyDto 객체를 생성한다.")
    @Test
    void from(){
        // given
        LocalDate birth = LocalDate.of(2001, 1, 1);
        Privacy privacy = Privacy.builder()
                .birth(birth)
                .email("test@gmail.com")
                .gender(Gender.MALE)
                .address("서울시 강남구")
                .mobile("01051413383")
                .build();
        privacyRepository.save(privacy);

        // when
        PrivacyDto privacyDto = PrivacyDto.from(privacy);

        // then
        assertThat(privacyDto)
                .extracting("gender", "birth", "address", "email", "mobile")
                .contains(privacy.getGender(), privacy.getBirth(), privacy.getAddress(), privacy.getEmail(), privacy.getMobile());
        assertThat(privacyDto.privacyId())
                .isNotNull()
                .isPositive();

    }

    @DisplayName("privacy entity가 없는 경우 애러가 발생하지 않고 빈객체를 생성한다")
    @Test
    void fromOfNull(){
        // given
        // when
        PrivacyDto privacyDto = PrivacyDto.from(null);

        // then
        assertThat(privacyDto).isNotNull();
    }


}