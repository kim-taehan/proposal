package org.developx.proposal.global.infra.jpa;

import java.time.format.DateTimeFormatter;

public record BaseData(
        String createdDate,
        String modifiedDate,
        String createBy,
        String lastModifiedBy
) {
    public static <T extends BaseEntity> BaseData of(T baseEntity, String format){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
        return new BaseData(
                dateFormat.format(baseEntity.getCreatedDate()),
                dateFormat.format(baseEntity.getModifiedDate()),
                baseEntity.getCreateBy(),
                baseEntity.getLastModifiedBy()
        );
    }

    public static <T extends BaseEntity> BaseData from(T baseEntity) {
        return of(baseEntity, "yyyy-MM-dd HH:mm");
    }
}