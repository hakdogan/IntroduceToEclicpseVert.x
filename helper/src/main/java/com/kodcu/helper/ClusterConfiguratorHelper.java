package com.kodcu.helper;


import com.hazelcast.config.Config;
import com.hazelcast.config.InterfacesConfig;
import com.hazelcast.config.JoinConfig;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-03-01
 */

public class ClusterConfiguratorHelper
{

    private ClusterConfiguratorHelper(){}

    public static Config getHazelcastConfiguration(){
        final Config config = new Config();
        final JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true).addMember("127.0.0.1");

        final InterfacesConfig interfaceConfig = config.getNetworkConfig().getInterfaces();
        interfaceConfig.setEnabled(true).addInterface( "127.0.0.1" );

        return config;
    }
}
