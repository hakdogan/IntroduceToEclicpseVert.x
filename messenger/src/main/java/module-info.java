/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 13.05.2020
 **/
module messenger
{
    requires io.vertx.core;
    requires io.vertx.web;
    requires helper;
    exports com.kodcu.nonclustered.messenger.verticle to io.vertx.core;
}