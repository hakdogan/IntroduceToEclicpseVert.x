/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.05.2020
 **/
module helper
{
    requires vertx.core;
    requires vertx.web;
    requires vertx.hazelcast;
    requires com.hazelcast.core;
    requires soapui;
    requires transitive util;
    exports com.kodcu.helper;
}