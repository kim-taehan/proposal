package org.developx.proposal.domain.proposal.data;

import org.developx.proposal.domain.proposal.entity.Proposal;

public record ProposalsResponse() {

    public static ProposalsResponse from(Proposal proposal) {
        return new ProposalsResponse();
    }
}
