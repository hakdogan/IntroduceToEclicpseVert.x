package com.kodcu;

import com.kodcu.helper.RandomPortHelper;
import org.junit.Assert;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 24.10.2018
 */

public class RandomLocalPortTest {

    @Test
    public void getRandomLocalPort(){
        assertTrue(RandomPortHelper.getRandomLocalPort() > -1);
    }
}
