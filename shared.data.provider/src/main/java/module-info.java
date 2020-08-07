/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 5.08.2020
 **/
module shared.data.provider
{
    requires io.vertx.core;
    requires entity;
    requires helper;
    requires com.hazelcast.core;
    requires io.vertx.clustermanager.hazelcast;
    requires org.slf4j;
    exports com.kodcu.shared.data.provider.verticle to io.vertx.core;
}