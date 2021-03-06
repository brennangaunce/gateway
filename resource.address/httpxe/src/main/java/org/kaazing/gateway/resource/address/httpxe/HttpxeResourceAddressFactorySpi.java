/**
 * Copyright (c) 2007-2014 Kaazing Corporation. All rights reserved.
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.kaazing.gateway.resource.address.httpxe;

import static org.kaazing.gateway.resource.address.ResourceFactories.changeSchemeOnly;

import java.net.URI;
import java.util.Map;

import org.kaazing.gateway.resource.address.ResourceFactory;
import org.kaazing.gateway.resource.address.ResourceOptions;
import org.kaazing.gateway.resource.address.http.HttpResourceAddress;
import org.kaazing.gateway.resource.address.http.HttpResourceAddressFactorySpi;

public class HttpxeResourceAddressFactorySpi extends HttpResourceAddressFactorySpi {

    private static final String SCHEME_NAME = "httpxe";
    private static final ResourceFactory TRANSPORT_FACTORY = changeSchemeOnly("http");

    static final String PROTOCOL_NAME = "httpxe/1.1";

    @Override
    public String getSchemeName() {
        return SCHEME_NAME;
    }

    @Override
    protected ResourceFactory getTransportFactory() {
        return TRANSPORT_FACTORY;
    }

    @Override
    protected String getProtocolName() {
        return PROTOCOL_NAME;
    }


    @Override
    protected void setAlternateOption(URI location,
                                      ResourceOptions options,
                                      Map<String, Object> optionsByName) {
        // do not make alternates for httpxe addresses
    }

    @Override
    protected void setOptions(HttpResourceAddress address, ResourceOptions options, Object qualifier) {

        super.setOptions(address, options, qualifier);
    }

}
