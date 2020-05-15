/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 15.05.2020
 **/
module sender
{
    requires vertx.core;
    requires vertx.web;
    requires vertx.hazelcast;
    requires helper;
    requires java.transaction.xa;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    exports com.kodcu.clustered.sender.verticle to vertx.core;
}