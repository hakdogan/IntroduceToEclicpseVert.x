/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 8.05.2020
 **/
module helloworld
{
    requires vertx.core;
    requires util;
    exports com.kodcu.hello.verticle to vertx.core;
}