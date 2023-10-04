package org.developx.proposal.global.utils;

import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

public final class QueryDslUtils {

    private QueryDslUtils() {
        throw new IllegalStateException("생성할 수 없는 클래스입니다.");
    }

    public static String makeLikeText(String text) {
        return String.format("%%%s%%", text);
    }

    public static <T extends Number> boolean positive(T number) {
        return !ObjectUtils.isEmpty(number) && number.intValue() >= 0;
    }
}
