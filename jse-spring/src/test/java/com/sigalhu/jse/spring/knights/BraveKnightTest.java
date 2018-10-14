package com.sigalhu.jse.spring.knights;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author huxujun
 * @date 2018/8/6
 */
public class BraveKnightTest {

    @Test
    public void embarkOnQuest() {
        Quest mockQuest = Mockito.mock(Quest.class);
        BraveKnight knight = new BraveKnight(mockQuest);
        knight.embarkOnQuest();
        Mockito.verify(mockQuest, Mockito.times(1)).embark();
    }
}