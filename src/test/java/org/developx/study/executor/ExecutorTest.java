package org.developx.study.executor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class ExecutorTest {

    @DisplayName("")
    @Test
    void test(){
        // given
        ExecutorService executorService = Executors.newCachedThreadPool();
        // when
        // 작업1 (스레드)
        executorService.submit(() -> {
            // Something job
            log.info("Test");
        });
        executorService.shutdown();
        // then
        Assertions.assertThat("");
    }

    @DisplayName("")
    @Test
    void test2(){
        // give
        Thread thread = new Thread(()->System.out.println("TEST"));
        thread.start();

        // when

        // then
        Assertions.assertThat("");
    }

}
