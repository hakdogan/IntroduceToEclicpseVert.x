package com.kodcu.helper;

import lombok.extern.slf4j.Slf4j;
import java.net.ServerSocket;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 24.10.2018
 */

@Slf4j
public class RandomPortHelper {

    private RandomPortHelper() {}

    public static int getRandomLocalPort(){

        try (ServerSocket socket = new ServerSocket(0); ){
            int portNumber = socket.getLocalPort();
            return portNumber;
        } catch (Exception ex) {
            log.error("Exception was thrown in getRandomLocalPort method. {} ", ex);
        }

        return 0;
    }
}
