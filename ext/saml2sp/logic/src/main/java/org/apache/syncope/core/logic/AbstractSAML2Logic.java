/*
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
package org.apache.syncope.core.logic;

import org.apache.syncope.common.lib.to.EntityTO;
import org.apache.syncope.core.logic.init.SAML2SPLoader;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSAML2Logic<T extends EntityTO> extends AbstractTransactionalLogic<T> {

    @Autowired
    protected SAML2SPLoader loader;

    protected void check() {
        if (!loader.isInited()) {
            throw new IllegalStateException("Keystore setup did not work properly, SAML 2.0 SP features disabled");
        }
    }
}
