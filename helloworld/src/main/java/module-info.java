/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 8.05.2020
 **/
module helloworld
{
    requires io.vertx.core;
    requires helper;
    exports com.kodcu.hello.verticle to io.vertx.core;
}