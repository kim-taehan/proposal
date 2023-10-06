package org.developx.proposal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.annotation.Persistent;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public abstract class DataJpaTestSupport {

    @PersistenceContext
    protected EntityManager entityManager;
}
