package com.demo.framework.wonders;

import com.demo.framework.wonders.service.impl.BlockMatchingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Krishan Shukla
 */

//@RunWith(SpringRunner.class)
@RunWith(Parameterized.class)
@SpringBootTest
@ContextConfiguration(classes = BlockMatchingApiTests.SimpleConfiguration.class)

public class WonderApiTests implements ApplicationContextAware, InitializingBean {


    private TestContextManager testContextManager;
    @Parameterized.Parameter(value = 0)
    public static ArrayList<String> firstList;
    @Parameterized.Parameter(value = 1)
    public static ArrayList<String>  secondList ;
    @Parameterized.Parameter(value = 2)
    public static boolean expected;


    @Before
    public void setUp() throws Exception {
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
         blockMatchingService = new BlockMatchingService();

    }
    @After
    public void tearDown() throws Exception {
        blockMatchingService = null;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {new ArrayList<>(Arrays.asList("String11a", "String11b")), new ArrayList<>(Arrays.asList("String11a", "String11b")), true},
                {new ArrayList<>(Arrays.asList("String21a", "String21b")), new ArrayList<>(Arrays.asList("String22a", "String22b")), false},
        });
    }

    @Configuration
    public static class SimpleConfiguration {}
    private ApplicationContext applicationContext;
    private boolean beanInitialized = false;
    private BlockMatchingService blockMatchingService = null;

    @Override
    @Ignore
    public void afterPropertiesSet() throws Exception {
        this.beanInitialized = true;
    }
    @Override
    @Ignore
    public void setApplicationContext(
            final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testParamQAUsingList(){
        blockMatchingService.isWonderPossible("[W/B/S/O , W , S/B , S]" , "WWSS");

    }

}
