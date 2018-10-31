package com.sigalhu.jse.spring.mockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.AopTestUtils;

/**
 * @author huxujun
 * @date 2018/10/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class HouseTest {

    @InjectMocks
    @Autowired
    private House house;

    @Autowired
    private AnotherHouse anotherHouse;

    @InjectMocks
    private AnotherHouse targetHouse;

    @Spy
    @Autowired
    private Pet dog;

    @Mock(name = "cat")
    private Pet mockCat;

    @Mock(name = "pig")
    private Pet mockPig;

    @Before
    public void setUp() {
        targetHouse = AopTestUtils.getTargetObject(anotherHouse);
        MockitoAnnotations.initMocks(this);

        Mockito.doReturn("Spy Dog bark: wow wow wow!")
                .when(dog).bark();
        Mockito.doReturn("Mock Cat bark: meow meow meow!")
                .when(mockCat).bark();
        Mockito.doReturn("Mock Pig bark: grunt grunt grunt!")
                .when(mockPig).bark();
    }

    @After
    public void tearDown() {
        Mockito.verify(dog).bark();
        Mockito.verify(mockCat, Mockito.times(2)).bark();
        Mockito.verify(mockPig).bark();
    }

    @Test
    public void petBark() {
        house.petBark();
        System.out.println("==============================");
        Mockito.reset(dog);
        house.petBark();
        System.out.println("==============================");
        anotherHouse.petBark();
    }
}