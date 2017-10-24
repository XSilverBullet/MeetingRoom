package com.siemens.cms.message.brokerplugins;

import com.siemens.cms.message.brokerfilters.AuthenticationBrokerFilter;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerPlugin;

/**
 * Created by Simon.Lau on 23-Nov-16.
 */
public class AuthenticationPlugin implements BrokerPlugin {

    @Override
    public Broker installPlugin(Broker broker) throws Exception {
        return new AuthenticationBrokerFilter(broker);
    }

}
