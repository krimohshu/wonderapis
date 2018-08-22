package com.demo.framework.wonders;

import org.junit.Before;
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
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
@RunWith(Parameterized.class)
@SpringBootTest
@ContextConfiguration(classes = BlockMatchingApiTests.SimpleConfiguration.class)

public class BlockMatchingApiTests implements ApplicationContextAware, InitializingBean {
    private TestContextManager testContextManager;
    @Parameterized.Parameter(value = 0) // Note 3i
    public String a;
    @Parameterized.Parameter(value = 1) // Note 3ii
    public String b ;
    @Parameterized.Parameter(value = 2) // Note 3iii
    public String expected;

    @Before // Note 2
    public void setUp() throws Exception {
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }

    @Parameterized.Parameters // Note 4
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList<>();
        params.add(new Object[] { "string1a", "string1a", "string1a"});
        params.add(new Object[] { "string2a", "string2a", "string2a"});
        params.add(new Object[] { "string3a", "string3a", "string3a"});
        return params;
    }

    @Configuration
    public static class SimpleConfiguration {}
    private ApplicationContext applicationContext;
    private boolean beanInitialized = false;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.beanInitialized = true;
    }
    @Override
    public void setApplicationContext(
            final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void whenTestStarted_thenContextSet() throws Exception {
        TimeUnit.SECONDS.sleep(2);

        System.out.println("++whenTestStarted_thenContextSet");
        assertNotNull(
                "The application context should have been set due to ApplicationContextAware semantics.",
                this.applicationContext);
        System.out.println("--whenTestStarted_thenContextSet");
    }
    @Test
    public void whenTestStarted_thenBeanInitialized() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("++whenTestStarted_thenBeanInitialized");

        assertTrue(
                "This test bean should have been initialized due to InitializingBean semantics.",
                this.beanInitialized);
        System.out.println("++whenTestStarted_thenBeanInitialized");

    }
    @Test
    public void contextLoads() {
    }

    @Test
    public void testParamQA(){

        assertThat(a, equalTo(expected));

    }

}
