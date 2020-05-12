package com.kodcu.helper;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import javax.net.ssl.SSLServerSocketFactory;
import java.net.ServerSocket;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 24.10.2018
 */

public class RandomPortHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomPortHelper.class);
    private RandomPortHelper() {}

    public static int getRandomLocalPort(){
        try (ServerSocket socket = SSLServerSocketFactory.getDefault().createServerSocket(0); ){
            return socket.getLocalPort();
        } catch (Exception ex) {
            LOGGER.error("An exception was thrown in getRandomLocalPort method! " + ex);
        }
        return 0;
    }
}
