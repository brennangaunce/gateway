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

package org.kaazing.gateway.service.http.balancer;

import org.kaazing.gateway.transport.IoHandlerAdapter;
import org.kaazing.gateway.transport.wsn.WsnSession;

class WsnBalancerServiceHandler extends IoHandlerAdapter<WsnSession> {
    WsnBalancerServiceHandler() {
    }

    @Override
    protected void doSessionCreated(WsnSession session) throws Exception {
        session.close(false);
    }

    @Override
    protected void doExceptionCaught(WsnSession session, Throwable cause) throws Exception {
        // trigger sessionClosed to update connection capabilities accordingly
        session.close(true);
    }

}
