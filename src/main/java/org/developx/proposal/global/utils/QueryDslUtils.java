package org.developx.proposal.global.utils;

public final class QueryDslUtils {

    private QueryDslUtils() {
        throw new IllegalStateException("생성할 수 없는 클래스입니다.");
    }

    public static String makeLikeText(String text) {
        return String.format("%%%s%%", text);
    }
}
