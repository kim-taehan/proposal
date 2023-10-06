package org.developx.proposal.domain.user.entity.enums;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public enum Gender {

    MALE {
        @Override
        public String getText() {
            return "남자";
        }
        @Override
        public String getGenderCode(LocalDate birth) {
            return isAfterMillennium(birth) ? "3" : "1";
        }
    },
    FEMALE {
        @Override
        public String getText() {
            return "여자";
        }
        @Override
        public String getGenderCode(LocalDate birth) {
            return isAfterMillennium(birth) ? "4" : "2";
        }
    };

    protected boolean isAfterMillennium(LocalDate birth){
        return birth.getYear() / 100 > 19;
    }

    public abstract String getGenderCode(LocalDate birth);
    public abstract String getText();

}
