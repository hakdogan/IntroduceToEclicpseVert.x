/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 27.05.2020
 **/
module routing
{
    requires vertx.core;
    requires vertx.web;
    requires vertx.config;
    requires vertx.hazelcast;
    requires com.hazelcast.core;
    requires soapui;
    requires helper;
    requires entity;
    exports com.kodcu.routing.verticle to vertx.core;
}