package org.developx.proposal.domain.project.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentType {
    PROPOSAL("제안서"), SUGGESTION("제언서"), RFP("RFP");

    private final String value;
}
