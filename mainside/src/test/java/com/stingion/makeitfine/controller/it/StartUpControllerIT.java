/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.controller.it;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.stingion.makeitfine.testconfiguration.CommonUtil;
import org.junit.jupiter.api.Test;

class StartUpControllerIT extends ControllerITProvision {

    @Test
    public void index() {
        CommonUtil.indexPageTest(restTemplate, getHostPort());
    }

    @Test
    public void greeting() {
        assertEquals("Make it fine \"makeitfine\"", getResponseBody("/info"));
        assertEquals("Make it fine \"makeitfine\"", getResponseBody("/info?details=any"));
        assertEquals("Stingion : stingion@gmail.com", getResponseBody("/info?details=author"));
        assertEquals("Social Network for workers", getResponseBody("/info?details=Purpose"));
        assertEquals(
                "Development stage : development milestone", getResponseBody("/info?details=stage"));
    }
}
