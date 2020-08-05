/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 4.08.2020
 **/
module shared.data.reader
{
    requires io.vertx.core;
    requires io.vertx.web;
    requires entity;
    requires helper;
    requires com.hazelcast.core;
    requires io.vertx.clustermanager.hazelcast;
    requires org.slf4j;
    exports com.kodcu.shared.data.reader.verticle to io.vertx.core;
}