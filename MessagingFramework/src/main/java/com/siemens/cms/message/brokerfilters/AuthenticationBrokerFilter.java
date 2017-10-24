package com.siemens.cms.message.brokerfilters;


import com.siemens.cms.message.config.UsersConfig;
import com.siemens.cms.message.container.SpringContainerFactory;
import com.siemens.cms.message.model.User;
import com.siemens.cms.message.utils.EncryptionUtil;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerFilter;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.ConnectionInfo;

/**
 * Created by Simon.Lau on 23-Nov-16.
 */
public class AuthenticationBrokerFilter extends BrokerFilter {

    public AuthenticationBrokerFilter(Broker next) {
        super(next);
    }

    @Override
    public void addConnection(ConnectionContext context, ConnectionInfo info) throws Exception {
        if (isUserValid(info.getUserName(), info.getPassword())) {
            super.addConnection(context, info);
        }
        else {
            throw new SecurityException("Authentication failed!");
        }
    }

    private boolean isUserValid(String username, String password) {
        boolean isValid = false;
        if (username == null || password == null) {
            return false;
        }

        User user = getUserByName(username);
        if (user == null) {
            return false;
        }

        try {
            String salt = username;
            String encryptPwd = EncryptionUtil.encrypt(salt + password, "SHA256");
            isValid = username.equals(user.getUsername()) && encryptPwd.equals(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    private User getUserByName (String username) {
        UsersConfig usersConfig = SpringContainerFactory.getBean(UsersConfig.class);
        User target = null;
        for (User user : usersConfig.getUsers()) {
            if (user.getUsername().equals(username)) {
                target = user;
                break;
            }
        }
        return target;
    }
}
