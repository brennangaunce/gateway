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

package org.kaazing.gateway.transport.wseb;

import static org.junit.rules.RuleChain.outerRule;

import java.io.File;
import java.net.URI;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.kaazing.gateway.server.Gateway;
import org.kaazing.gateway.server.test.GatewayRule;
import org.kaazing.gateway.server.test.config.GatewayConfiguration;
import org.kaazing.gateway.server.test.config.builder.GatewayConfigurationBuilder;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

public class WseHandshakeIT {

    private static String ECHO_SERVICE_ACCEPT = "wse://localhost:8001/echo";

    private final K3poRule robot = new K3poRule();

    private final GatewayRule gateway = new GatewayRule() {
        {
            // @formatter:off
                GatewayConfiguration configuration =
                        new GatewayConfigurationBuilder()
                            .webRootDirectory(new File("src/test/webapp"))
                            .property(Gateway.GATEWAY_CONFIG_DIRECTORY_PROPERTY,"src/test/resources/gateway/conf")
                            .service()
                                .accept(URI.create(ECHO_SERVICE_ACCEPT))
                                .type("echo")
                            .done()
                        .done();
                // @formatter:on
            init(configuration);
        }
    };

    @Rule
    public TestRule chain = outerRule(robot).around(gateway);

    @Specification("wse.handshake.send.receive.3_5")
    @Test(timeout = 5000)
    public void testHandshakeSendReceiveVersion3_5() throws Exception {
        robot.finish();
    }

    @Specification("wse.handshake.send.receive")
    @Test(timeout = 5000)
    public void testHandshakeSendReceive() throws Exception {
        robot.finish();
    }

    @Specification("closeDownstreamShouldUnbindUpstream")
    @Test(timeout = 5000)
    public void closeDownstreamShouldUnbindUpstream() throws Exception {
        robot.finish();
    }

}
