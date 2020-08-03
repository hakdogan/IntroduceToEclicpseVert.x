/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 27.05.2020
 **/
module routing
{
    requires io.vertx.core;
    requires io.vertx.web;
    requires io.vertx.config;
    requires io.vertx.clustermanager.hazelcast;
    requires com.hazelcast.core;
    requires soapui;
    requires helper;
    requires entity;
    exports com.kodcu.routing.verticle to io.vertx.core;
}