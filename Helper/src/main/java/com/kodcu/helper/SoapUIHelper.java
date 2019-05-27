package com.kodcu.helper;

import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.support.xml.XmlObjectConfigurationBuilder;
import org.apache.xmlbeans.XmlObject;
import java.util.List;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-02
 */

public class SoapUIHelper
{
    private SoapUIHelper(){}

    /**
     *
     * @param steps
     */
    public static void updateEndpoint(final List<TestStep> steps, final String hostName, final int port){
        for(TestStep step :steps){
            step.setPropertyValue("endpoint", String.format(hostName, port));
        }
    }

    /**
     *
     * @param testDelay
     * @param randomFactor
     * @return
     */
    public static XmlObject getLoadTestConfiguration(final int testDelay, final int randomFactor){
        XmlObjectConfigurationBuilder builder = new XmlObjectConfigurationBuilder();
        return builder.add("testDelay", testDelay).add("randomFactor", randomFactor).finish();
    }

    public static int getMilisecond(){
        return (int) System.currentTimeMillis() % 1000;
    }

    /**
     *
     * @return
     */
    public static int getSecond(){
        return (int) (System.currentTimeMillis() / 1000) % 60;
    }

    /**
     *
     * @return
     */
    public static int getMinute(){
        return (int) ((System.currentTimeMillis() / (1000 * 60)) % 60);
    }

    /**
     *
     * @return
     */
    public static int getHour(){
        return (int) ((System.currentTimeMillis() / (1000 * 60 * 60)) % 24);
    }

}
