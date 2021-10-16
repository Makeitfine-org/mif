/*
 * Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.testconfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.stingion.makeitfine.data.service.model.EntityService;
import com.stingion.makeitfine.data.service.model.it.EntityHelper;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.boot.test.web.client.TestRestTemplate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public final class CommonUtil {

    public static void indexPageTest(TestRestTemplate restTemplate, String hostPort) {
        @Nullable String[] responseBodies = new String[]{
                getResponseBody(restTemplate, hostPort, "/index", String.class),
                getResponseBody(restTemplate, hostPort, "/", String.class)
        };

        for (String r : responseBodies) {
            if (r == null) {
                fail("response body is null");
                continue;
            }
            assertTrue(r.contains("English"));
            assertTrue(r.contains(" | "));
            assertTrue(r.contains("Русский"));
            assertTrue(r.contains("Make it fine"));
            assertTrue(r.contains("Домашняя страница") || r.contains("Home page"));
        }
    }

    @SafeVarargs
    @Nullable
    public static <T> T getResponseBody(TestRestTemplate restTemplate, String hostPort, String relativePath,
            Class<T>... clasz) {

        @SuppressWarnings("unchecked")
        Class<T> typeAlternative = (Class<T>) String.class;

        Class<T> type = Optional.ofNullable(clasz)
                .map(classes -> classes.length)
                .orElse(0) > 0 ? clasz[0] : typeAlternative;

        return restTemplate.getForEntity(hostPort + relativePath, type).getBody();
    }

    public static <T> void deleteTest(EntityHelper<T> entityHelper, EntityService<T> entityService,
            ServiceTestConfiguration.EntityTestData<T> entityTestData) {

        int beforeDelete = entityHelper.getCount();
        T entity = entityService.findById(entityTestData.getId());
        entityService.delete(entity);
        int afterDelete = entityHelper.getCount();

        assertThat(beforeDelete == afterDelete + 1);
        assertThat(!entityHelper.isExist(entity));
    }
}
