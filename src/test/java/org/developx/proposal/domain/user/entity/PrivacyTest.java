package org.developx.proposal.domain.user.entity;

import org.developx.proposal.domain.user.entity.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[entity] privacy entity")
class PrivacyTest {

    @DisplayName("11자리 모바일 번호는 010-xxxx-xxxx 형태로 보여준다.")
    @Test
    void getDisplayMobileCounting11() {
        // given
        Privacy privacy = Privacy.builder().mobile("01012345678").build();

        // when
        String displayMobile = privacy.getDisplayMobile();

        // then
        assertThat(displayMobile).isEqualTo("010-1234-5678");
    }

    @DisplayName("10자리 모바일 번호는 010-xxx-xxxx 형태로 보여준다.")
    @Test
    void getDisplayMobileCounting10() {
        // given
        Privacy privacy = Privacy.builder().mobile("0101234567").build();

        // when
        String displayMobile = privacy.getDisplayMobile();

        // then
        assertThat(displayMobile).isEqualTo("010-123-4567");
    }

    @DisplayName("10자리 미만인 모바일 번호는 그대로 출력된다.")
    @Test
    void getDisplayMobileLessthen10() {
        // given
        Privacy privacy = Privacy.builder().mobile("010123456").build();

        // when
        String displayMobile = privacy.getDisplayMobile();

        // then
        assertThat(displayMobile).isEqualTo(privacy.getMobile());
    }

    @DisplayName("주민등록번호 앞자리를 구할 수 있다.")
    @CsvSource({"2000,1,1,000101", "1999,12,31,991231", "1985,3,12,850312"})
    @ParameterizedTest
    void getSsnFrontFormat(int yyyy, int mm, int dd, String expected) {
        // given
        Privacy privacy = Privacy.builder().birth(LocalDate.of(yyyy, mm, dd)).build();

        // when
        String ssn1 = privacy.getSsnFrontFormat();

        // then
        assertThat(ssn1).isEqualTo(expected);
    }

    @DisplayName("주민등록번호의 성별 자리까지 조회할 수 있다.")
    @CsvSource({"2000,1,1,MALE, 000101-3", "2000,1,1,FEMALE,000101-4", "1999,12,31,MALE,991231-1", "1999,12,31,FEMALE,991231-2"})
    @ParameterizedTest
    void getSsnFormat(int yyyy, int mm, int dd, Gender gender, String expected) {
        // given
        Privacy privacy = Privacy.builder()
                .birth(LocalDate.of(yyyy, mm, dd))
                .gender(gender)
                .build();

        // when
        String ssn = privacy.getSsnFormat();

        // then
        assertThat(ssn).isEqualTo(expected);
    }
}