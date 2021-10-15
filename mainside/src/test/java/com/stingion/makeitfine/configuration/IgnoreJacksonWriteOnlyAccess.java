/*
 *  Created under not commercial project "Make it fine"
 *
 *  Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class IgnoreJacksonWriteOnlyAccess extends JacksonAnnotationIntrospector {

    private static final long serialVersionUID = 4787108556228621714L;

    @Override
    public JsonProperty.Access findPropertyAccess(Annotated m) {
        JsonProperty.Access access = super.findPropertyAccess(m);
        if (access == JsonProperty.Access.WRITE_ONLY) {
            return JsonProperty.Access.AUTO;
        }
        return access;
    }
}
