package com.kodcu;

import com.kodcu.helper.RouterHelper;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.junit.Test;
import static junit.framework.TestCase.assertFalse;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 24.10.2018
 */

public class RouterTest {

    @Test
    public void getRouter(){
        Router router = RouterHelper.createRouter(Vertx.vertx(), "Hello");
        assertFalse(router.getRoutes().isEmpty());
    }
}
