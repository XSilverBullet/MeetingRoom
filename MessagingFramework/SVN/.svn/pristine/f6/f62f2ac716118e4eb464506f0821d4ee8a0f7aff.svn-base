package com.siemens.cms.message.config;

import com.siemens.cms.message.brokerplugins.AuthenticationPlugin;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.apache.activemq.hooks.SpringContextHook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class MqttBrokerConfig {

	@Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService brokerService() throws Exception {
        final BrokerService brokerService = new BrokerService();

        //uncomment this line if you want to use authentication feature
        //addPlugin(brokerService, new AuthenticationPlugin());
        brokerService.addConnector("mqtt://0.0.0.0:1883");
        brokerService.addConnector("ws://0.0.0.0:1884");
        brokerService.setPersistent(false);
        brokerService.setShutdownHooks(Collections.<Runnable>singletonList(new SpringContextHook()));
        final ManagementContext managementContext = new ManagementContext();
        managementContext.setCreateConnector(true);
        brokerService.setManagementContext(managementContext);

        return brokerService;
    }

//    private void enableAuthPlugin(BrokerService brokerService) {
//        TokenAuthenticationPlugin authPlugin = new TokenAuthenticationPlugin();
//        BrokerPlugin[] existPlugins = brokerService.getPlugins();
//        if (existPlugins == null) existPlugins = new BrokerPlugin[] {};
//        ArrayList<BrokerPlugin> newPlugins = new ArrayList<BrokerPlugin>();
//        newPlugins.addAll(Arrays.asList(existPlugins));
//        newPlugins.add(authPlugin);
//        // Setup the broker
//        brokerService.setPlugins(newPlugins.toArray(existPlugins));
//    }

    private void addPlugin(BrokerService brokerService, BrokerPlugin plugin) {
        ArrayList<BrokerPlugin> plugins = new ArrayList<>();
        BrokerPlugin[] existPlugins = brokerService.getPlugins();
        if (existPlugins != null) {
            plugins.addAll(Arrays.asList(existPlugins));
        }

        plugins.add(plugin);
        BrokerPlugin[] allPlugins = new BrokerPlugin[plugins.size()];
        brokerService.setPlugins(plugins.toArray(allPlugins));
    }

}
