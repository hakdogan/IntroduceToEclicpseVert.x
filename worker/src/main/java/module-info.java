/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 14.05.2020
 **/
module worker
{
    requires vertx.core;
    requires vertx.web;
    requires helper;
    requires util;
    exports com.kodcu.worker.verticle to vertx.core;
}