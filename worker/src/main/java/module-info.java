/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 14.05.2020
 **/
module worker
{
    requires io.vertx.core;
    requires io.vertx.web;
    requires helper;
    requires util;
    exports com.kodcu.worker.verticle to io.vertx.core;
}