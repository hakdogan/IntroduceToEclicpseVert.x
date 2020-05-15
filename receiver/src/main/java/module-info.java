/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 15.05.2020
 **/
module receiver
{
    requires vertx.core;
    requires vertx.hazelcast;
    requires helper;
    requires java.transaction.xa;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
}