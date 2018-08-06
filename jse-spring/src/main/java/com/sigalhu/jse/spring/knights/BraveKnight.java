package com.sigalhu.jse.spring.knights;

/**
 * @author huxujun
 * @date 2018/8/6
 */
public class BraveKnight implements Knight {
    private Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}
