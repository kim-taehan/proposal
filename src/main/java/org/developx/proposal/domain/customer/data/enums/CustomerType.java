package org.developx.proposal.domain.customer.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum CustomerType {

    BANK("은행"),
    STOCK("증권"),
    INSURANCE("보험");

    private final String text;

}
