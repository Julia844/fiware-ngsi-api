/*
 * Copyright (C) 2015 Orange
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

package com.orange.ngsi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Identify if host accept json or xml content
 */
@Component
public class ProtocolRegistry {

    private static Logger logger = LoggerFactory.getLogger(ProtocolRegistry.class);

    /**
     * Keeps track of hosts supporting json (true => v1, false => v2).
     * Hosts only supporting xml are not added (returning null).
     */
    private Map<String, Boolean> jsonHost = new HashMap<>();

    /**
     * memorize the host supports the JSON v1 or v2 APIs
     * @param url url of the host
     * @param v1 true if host accept api v1, false if host accept api v2
     */
    public void registerHost(String url, boolean v1) {
        try {
            jsonHost.put(getHost(url), v1);
        } catch (URISyntaxException e) {
            logger.warn("failed to register url {}", url);
        }
    }

    /**
     * unregister a host (mainly usefull for testing purpose).
     * @param url url of the host
     */
    public void unregisterHost(String url) {
        try {
            jsonHost.remove(getHost(url));
        } catch (URISyntaxException e) {
            logger.warn("failed to register url {}", url);
        }
    }

    /**
     * indicate if the host support V1 Json
     * @param url of the host
     * @return true if host support api v1
     */
    public boolean supportV1Json(String url) {
        try {
            Boolean result = jsonHost.get(getHost(url));
            return result != null && result;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    /**
     * indicate if the host support V2 Json
     * @param url of the host
     * @return true if host support api v2
     */
    public boolean supportV2Json(String url) throws URISyntaxException {
        try {
            Boolean result = jsonHost.get(getHost(url));
            return result != null && !result;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    /**
     * indicate if the host supports xml
     * @param url of the host
     * @return false if host does not support v1 or v2 json
     */
    public boolean supportXml(String url) {
        try {
            return jsonHost.get(getHost(url)) == null;
        } catch (URISyntaxException e) {
            return true;
        }
    }

    private String getHost(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return uri.getHost() + ":" + uri.getPort();
    }
}
