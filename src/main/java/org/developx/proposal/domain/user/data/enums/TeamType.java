package org.developx.proposal.domain.user.data.enums;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@RequiredArgsConstructor(access = PROTECTED)
public enum TeamType {
    DIVISION("부문"), DEPARTMENT("그룹"), TEAM("팀");
    private final String value;
}
