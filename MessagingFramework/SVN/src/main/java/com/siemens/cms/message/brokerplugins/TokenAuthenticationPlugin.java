package com.siemens.cms.message.brokerplugins;

/**
 * Created by Matteo on 6/2/16.
 */

import com.siemens.cms.message.brokerfilters.TokenAuthenticationBroker;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerPlugin;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationPlugin implements BrokerPlugin {

    @Override
    public Broker installPlugin(Broker broker) throws Exception {
        return new TokenAuthenticationBroker(broker);
    }

}