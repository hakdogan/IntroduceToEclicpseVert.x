/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.05.2020
 **/
module helper
{
    requires io.vertx.core;
    requires io.vertx.web;
    requires io.vertx.web.template.freemarker;
    requires io.vertx.web.common;
    requires com.fasterxml.jackson.core;
    requires com.hazelcast.core;
    requires transitive util;
    requires transitive org.slf4j;
    exports com.kodcu.helper;
}