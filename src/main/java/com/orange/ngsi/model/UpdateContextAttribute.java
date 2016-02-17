/*
 * Copyright (C) 2016 Orange
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.orange.ngsi.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Request for POST/PUT /contextEntities/{entityID}/attributes/{attributeName}
 */
@JacksonXmlRootElement(localName = "updateContextAttributeRequest")
public class UpdateContextAttribute {

    @JsonUnwrapped
    ContextAttribute attribute;

    public UpdateContextAttribute() {
    }

    public ContextAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(ContextAttribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "UpdateContextAttribute{" +
                "attribute=" + attribute +
                '}';
    }
}
