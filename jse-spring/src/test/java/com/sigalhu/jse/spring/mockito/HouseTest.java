package com.sigalhu.jse.spring.mockito;

import com.sun.scenario.effect.impl.prism.PrImage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2018/10/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class HouseTest {

    @Autowired
    private House house;

    @Autowired
    private AnotherHouse anotherHouse;

    @Autowired
    private Pet dog;

    @Spy
    private Pet spyDog = dog;

    @Mock
    private Pet mockCat;

    @Mock
    private Pet mockPig;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.doReturn("Spy Dog bark: wow wow wow!")
                .when(spyDog).bark();
        Mockito.doReturn("Mock Cat bark: meow meow meow!")
                .when(mockCat).bark();
        Mockito.doReturn("Mock Pig bark: grunt grunt grunt!")
                .when(mockPig).bark();

        AnotherHouse targetHouse = AopTestUtils.getTargetObject(anotherHouse);
        ReflectionTestUtils.setField(house, "dog", spyDog);
        ReflectionTestUtils.setField(house, "cat", mockCat);
        ReflectionTestUtils.setField(targetHouse, "pig", mockPig);
    }

    @After
    public void tearDown() {
        Mockito.verify(spyDog).bark();
        Mockito.verify(mockCat).bark();
        Mockito.verify(mockPig).bark();
    }

    @Test
    public void petBark() {
        house.petBark();
        anotherHouse.petBark();
    }
}