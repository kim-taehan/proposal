package org.developx.proposal.web.proposal.data;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.developx.proposal.domain.project.data.enums.DocumentType.PROPOSAL;

@DisplayName("[record] 신규 제안서 생성 폼")
class CreateProposalFormTest {

    @Test
    @DisplayName("비어있는 객체를 생성한다.")
    void empty() {

        // given & when
        CreateProposalForm empty = CreateProposalForm.getInstance();

        // then
        Assertions.assertThat(empty).extracting("documentType", "projectId", "files")
                .contains(PROPOSAL, 0L, Collections.emptyList());
    }
}