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

package org.kaazing.gateway.transport.wsn;

import static org.junit.rules.RuleChain.outerRule;

import java.net.InetAddress;
import java.net.URI;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.kaazing.gateway.server.test.GatewayRule;
import org.kaazing.gateway.server.test.config.GatewayConfiguration;
import org.kaazing.gateway.server.test.config.builder.GatewayConfigurationBuilder;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

@Ignore("Ignoring until NR-56 is fixed")
public class WsnIPv6IT {

    private K3poRule robot = new K3poRule();

    private static final boolean ENABLE_DIAGNOSTICS = false;

    @BeforeClass
    public static void init() throws Exception {
        if (ENABLE_DIAGNOSTICS) {
            PropertyConfigurator.configure("src/test/resources/log4j-diagnostic.properties");
        }
    }

    public GatewayRule gateway = new GatewayRule() {
        {
            GatewayConfiguration configuration = new GatewayConfigurationBuilder()
                    // proxy service to the below service
                    .service()
                        .accept(URI.create("ws://[::1]:8001/echo"))
                        .type("echo")
                        .crossOrigin()
                            .allowOrigin("*")
                        .done()
                    .done()
                .done();

            init(configuration);
        }
    };

    @Rule
    public TestRule chain = outerRule(robot).around(gateway);

    @Specification("echoTextFrameInIPv6Service")
    @Test
    public void proxyPreservesTextFrame() throws Exception {
        InetAddress[] addresses = InetAddress.getAllByName("::1");
        Assume.assumeTrue(addresses.length != 0);

        robot.finish();
    }
    
}
