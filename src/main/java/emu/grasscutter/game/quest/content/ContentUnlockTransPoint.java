package emu.grasscutter.game.quest.content;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValue;
import emu.grasscutter.game.quest.enums.QuestTrigger;
import emu.grasscutter.game.quest.handlers.QuestBaseHandler;

@QuestValue(QuestTrigger.QUEST_CONTENT_UNLOCK_TRANS_POINT)
public class ContentUnlockTransPoint extends QuestBaseHandler {
    @Override
    public boolean execute(GameQuest quest, QuestData.QuestCondition condition, String paramStr, int... params) {
        if (params[0]==-1) {
            if(quest.getOwner().getUnlockedScenePoints(condition.getParam()[0]).contains(condition.getParam()[1]))
                return true;
            else {
                return false;
            }
        }
        return condition.getParam()[0] == params[0] && condition.getParam()[1] == params[1];
    }
}
