package com.kodcu.helper;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLServerSocketFactory;
import java.net.ServerSocket;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 24.10.2018
 */

@Slf4j
public class RandomPortHelper
{
    private RandomPortHelper() {}

    public static int getRandomLocalPort(){
        try (ServerSocket socket = SSLServerSocketFactory.getDefault().createServerSocket(0); ){
            return socket.getLocalPort();
        } catch (Exception ex) {
            log.error("An exception was thrown in getRandomLocalPort method!", ex);
        }
        return 0;
    }
}
