/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 15.05.2020
 **/
module sender
{
    requires io.vertx.core;
    requires io.vertx.web;
    requires io.vertx.clustermanager.hazelcast;
    requires helper;
    requires java.transaction.xa;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    exports com.kodcu.clustered.sender.verticle to io.vertx.core;
}