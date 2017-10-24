package com.siemens.cms.message.brokerfilters;

import com.siemens.cms.message.validators.JWTValidator;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerFilter;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.ConnectionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Matteo on 6/2/16.
 */
public class TokenAuthenticationBroker extends BrokerFilter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public TokenAuthenticationBroker(Broker next) {
        super(next);
    }

    @Override
    public void addConnection(ConnectionContext context, ConnectionInfo info) throws Exception {

        String client = context.getClientId();
        String token = context.getUserName();

        logger.debug("Client {} is connecting with token={}] ", client, token);

        boolean isValid = JWTValidator.getInstance().isValid(token);

        if(! isValid) {
            throw new SecurityException("The signature is not valid");
        } else {
            logger.info("Found token [{}] belonging to client: {}. Allowing connection", token, client);
            super.addConnection(context, info);
        }
    }
}
