/*
 * Copyright 2019-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.MyTest;

import com.google.common.collect.ImmutableSet;

import org.jvnet.hk2.annotations.Service;
import org.onosproject.cfg.ComponentConfigService;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.onosproject.core.ApplicationId;
import org.onosproject.core.CoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinyradius.util.*;
import org.tinyradius.packet.*;
import org.tinyradius.attribute.*;
import org.tinyradius.dictionary.*;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Properties;

import static org.onlab.util.Tools.get;

/**
 * Skeletal ONOS application component.
 */

@Component(immediate = true)
public class MyRADIUStest {

    public MyRADIUStest(){
        rc = createRadiusClient();
    }
    private final Logger log = LoggerFactory.getLogger(getClass());
    private ApplicationId appId;

    private String host = "127.0.0.1";
    private String NASpassword = "testing123";
    private String user = "gary199722";
    private String user_pass = "zxc199722";
    private RadiusClient rc;
    private AccessRequest ar;

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    protected CoreService coreService;

    /** Some configurable property. */
    private String someProperty;

    @Reference(cardinality = ReferenceCardinality.MANDATORY)
    protected ComponentConfigService cfgService;

    @Activate
    protected void activate() {
        // cfgService.registerProperties(getClass());
        appId = coreService.registerApplication("org.onosproject.MyTest");

        /* RESTful API passing User credential? */

        rc = createRadiusClient();
        ar = createRequest(user, user_pass);
        authWithRadius(ar);

        log.info("Started");
    }

    public String authWithRadius(AccessRequest auth_ar) {
        log.info("Packet before it is sent\n" + auth_ar + "\n");
        RadiusPacket response = null;
        try {
            log.info("Packet to RADIUS server");
            if(rc == null)
                log.info("FUUUUUUUUUUUUUUUUUUUUUUUUUUUUUCK");
            response = rc.authenticate(auth_ar);
        } catch (IOException e) {
            log.info("Exeption from IO");
            e.printStackTrace();
        } catch (RadiusException e) {
            log.info("Exception from RADIUS");
            e.printStackTrace();
        }
        log.info("Packet after it was sent\n" + auth_ar + "\n");
        log.info("Response\n" + response.getPacketTypeName() + "\n");
        rc.close();
        return response.getPacketTypeName();
    }

    public RadiusClient createRadiusClient() {
        log.info("Radius Client created!!");
        return new RadiusClient(host, NASpassword);
    }

    public AccessRequest createRequest(String myUser, String myPass) {
        AccessRequest thisAr = new AccessRequest(myUser, myPass);
        log.info("Access Request created!!");
        thisAr.setAuthProtocol("pap");
        thisAr.addAttribute("NAS-Identifier", "My localhost NAS~");
        thisAr.addAttribute("NAS-IP-Address", "127.0.1.1");
        thisAr.addAttribute("Service-Type", "Login-User");
        return thisAr;
    }

    @Deactivate
    protected void deactivate() {
        // cfgService.unregisterProperties(getClass(), false);
        log.info("Stopped");
    }

    @Modified
    public void modified(ComponentContext context) {
        Dictionary<?, ?> properties = context != null ? context.getProperties() : new Properties();
        if (context != null) {
            someProperty = get(properties, "someProperty");
        }
        log.info("Reconfigured");
    }

}
