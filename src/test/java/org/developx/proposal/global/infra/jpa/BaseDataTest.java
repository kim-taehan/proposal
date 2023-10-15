package org.developx.proposal.global.infra.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.developx.proposal.SpringBootTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("[record] base entity dto")
class BaseDataTest extends SpringBootTestSupport {

    @Entity
    @Getter
    @NoArgsConstructor(access = PRIVATE)
    static class TestEntity extends BaseEntity {
        @Id
        private String name;

        public TestEntity(String name) {
            this.name = name;
        }
    }
    @DisplayName("테이블 컬럼의 등록자와 등록일시를 조회한다.")
    @Test
    @WithMockUser(username = "USER", roles = "USER")
    void from(){
        // given
        TestEntity testEntity = new TestEntity("test");
        entityManager.persist(testEntity);

        // when
        BaseData baseData = BaseData.from(testEntity);

        // then
        assertThat(baseData).isNotNull()
                .extracting("createBy", "lastModifiedBy")
                .contains("USER", "USER");

        // 시간은 존재하는지만 확인한다.
        assertThat(baseData.createdDate()).isNotBlank();
        assertThat(baseData.modifiedDate()).isNotBlank();
    }

    @DisplayName("테이블 컬럼의 등록일시를 조회시 포맷을 지정할 수 있다.")
    @Test
    @WithMockUser(username = "USER", roles = "USER")
    void of(){
        // given
        TestEntity testEntity = new TestEntity("test");
        entityManager.persist(testEntity);

        // when
        BaseData baseData = BaseData.of(testEntity, "yyyyMMdd");

        // then
        assertThat(baseData).isNotNull()
                .extracting("createBy", "lastModifiedBy")
                .contains("USER", "USER");

        // 시간은 존재하는지만 확인한다.
        assertThat(baseData.createdDate())
                .isNotBlank().hasSize(8);
        assertThat(baseData.modifiedDate())
                .isNotBlank().hasSize(8);
    }
}