package org.developx.proposal.global.infra.jpa;

import java.time.LocalDateTime;

public record BaseData(LocalDateTime createdDate, LocalDateTime modifiedDate, String createBy, String lastModifiedBy) {
    public static <T extends BaseEntity> BaseData from(T baseEntity) {
        return new BaseData(
                baseEntity.getCreatedDate(),
                baseEntity.getModifiedDate(),
                baseEntity.getCreateBy(),
                baseEntity.getLastModifiedBy()
        );
    }
}