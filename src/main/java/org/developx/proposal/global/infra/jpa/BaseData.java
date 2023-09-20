package org.developx.proposal.global.infra.jpa;

import java.time.LocalDateTime;

public record BaseData(LocalDateTime createdDate, LocalDateTime modifiedDate, String createBy, String lastModifiedBy) {

    public static BaseData from(BaseEntity baseEntity) {
        return new BaseData(baseEntity.getCreatedDate(), baseEntity.getModifiedDate(), baseEntity.getCreateBy(), baseEntity.getLastModifiedBy());
    }
}