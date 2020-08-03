/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.05.2020
 **/
module helper
{
    requires io.vertx.core;
    requires io.vertx.web;
    requires com.hazelcast.core;
    requires transitive util;
    exports com.kodcu.helper;
}