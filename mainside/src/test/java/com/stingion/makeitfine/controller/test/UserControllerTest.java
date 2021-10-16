/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller.test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.common.collect.Lists;
import com.stingion.makeitfine.controller.UserController;
import com.stingion.makeitfine.data.model.user.User;
import com.stingion.makeitfine.data.service.model.UserService;
import com.stingion.makeitfine.testconfiguration.UnitTest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * To run tagged test: mvn -Dgroups="simple" test mvn -DexcludedGroups="other" test.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@UnitTest
@Tag("controllerTest")
class UserControllerTest {

    private static List<User> userList;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @BeforeAll
    public static void beforeClass() {
        User user1 = new User();
        user1.setId(1);

        User user2 = new User();
        user2.setId(2);

        userList = Lists.newArrayList(user1, user2);
    }

    public static final Stream<Integer> intNumbs() {
        return Stream.iterate(6, i -> i + 1).limit(3);
    }

    @BeforeEach
    public void beforeEach() {
        when(userService.findAll()).thenReturn(userList);
        when(userService.findById(1)).thenReturn(userList.get(0));
        when(userService.findById(2)).thenReturn(userList.get(1));

        IllegalArgumentException idShouldBePositiveException =
                new IllegalArgumentException("id should be positive");

        doThrow(idShouldBePositiveException)
                .when(userService)
                .findById(argThat(new NumberIsNotPositive()));
    }

    @Test
    @Tag("simple")
    public void listAllUsers() throws Exception {
        mockMvcByUrlAndContents("/user", "2", "1");

        verify(userService, times(1)).findAll();
    }

    @Test
    @Tag("simple")
    public void getUserOne() throws Exception {
        int id = 1;
        mockMvcByUrlAndContents("/user/" + id, String.valueOf(id));

        verify(userService, times(1)).findById(id);
    }

    @Timeout(value = 250, unit = TimeUnit.MILLISECONDS)
    @Test
    @RepeatedTest(3)
    public void getUser() throws Exception {
        int id = 2;
        mockMvcByUrlAndContents("/user/" + id, String.valueOf(id));

        verify(userService, times(1)).findById(id);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5})
    @MethodSource("intNumbs")
    public void getUserParam(int id) throws Exception {
        User userById = new User();
        userById.setId(id);
        userList.add(userById);

        when(userService.findById(id)).thenReturn(userList.get(id - 1));

        mockMvcByUrlAndContents("/user/" + id, String.valueOf(id));
    }

    private void mockMvcByUrlAndContents(String urlTemplate, String... content) throws Exception {
        List<Matcher<? super String>> matchers =
                Arrays.stream(content).map(e -> containsString(e)).collect(Collectors.toList());
        mockMvc
                .perform(get(urlTemplate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.allOf(matchers)));
        // below '.andExpect(..' can also be used as array or varargs type instead of above one
        //      .andExpect(content().string(Matchers.allOf(matchers.toArray(new Matcher[0]))));
    }

    @TestFactory
    public Stream<DynamicTest> translateDynamicTestsFromStream() {
        return Stream.of(-717, -2, -1, 0)
                .map(id -> DynamicTest.dynamicTest("Test negative scenario with no id = " + id, () -> {
                    Throwable ex = assertThrows(IllegalArgumentException.class, () -> userService.findById(id));
                    assertTrue("id should be positive".equalsIgnoreCase(ex.getMessage()));
                }));
    }

    static class DisplayNameGen extends DisplayNameGenerator.ReplaceUnderscores {

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return UUID.randomUUID().toString();
        }
    }

    public static class TemplateProvider implements TestTemplateInvocationContextProvider {

        @Override
        public boolean supportsTestTemplate(ExtensionContext context) {
            return true;
        }

        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
                ExtensionContext context) {
            return Stream.of(
                    invocationContext(10000), invocationContext(2123434234), invocationContext(1000023423));
        }

        private TestTemplateInvocationContext invocationContext(Integer parameter) {
            return new InvocationContext(parameter);
        }
    }

    @AllArgsConstructor
    private static class InvocationContext implements TestTemplateInvocationContext {

        private Integer parameter;

        @Override
        public String getDisplayName(int invocationIndex) {
            return "Number: " + parameter;
        }

        @Override
        public List<Extension> getAdditionalExtensions() {
            return Collections.singletonList(
                    new ParameterResolver() {
                        @Override
                        public boolean supportsParameter(
                                ParameterContext parameterContext, ExtensionContext extensionContext) {
                            return parameterContext.getParameter().getType().equals(Integer.class);
                        }

                        @Override
                        public Object resolveParameter(
                                ParameterContext parameterContext, ExtensionContext extensionContext) {
                            return parameter;
                        }
                    });
        }
    }

    private static class NumberIsNotPositive implements ArgumentMatcher<Integer> {

        @Override
        public boolean matches(Integer argument) {
            return argument < 1;
        }
    }

    @Nested
    @ExtendWith(TemplateProvider.class)
    @DisplayName("Testing template on userService")
    @DisplayNameGeneration(DisplayNameGen.class)
    public class UserControllerWithTemplateTest {

        @BeforeEach
        public void beforeEach() {
            IllegalArgumentException idShouldBePositiveException =
                    new IllegalArgumentException("id should be positive");
            doReturn(new User()).when(userService).findById(anyInt());
            doThrow(idShouldBePositiveException)
                    .when(userService)
                    .findById(argThat(new NumberIsNotPositive()));
        }

        @TestTemplate
        public void testUserWithHighNumberId(Integer id) {
            assertNotNull(userService.findById(id));
        }
    }
}
