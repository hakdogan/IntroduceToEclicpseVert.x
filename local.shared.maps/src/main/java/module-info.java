/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 17.05.2020
 **/
module local.shared.maps
{
    requires vertx.core;
    requires vertx.web;
    requires entity;
    requires helper;

    exports com.kodcu.local.shared.maps.verticle to vertx.core;
}