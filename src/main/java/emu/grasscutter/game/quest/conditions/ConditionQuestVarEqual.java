package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.quest.GameMainQuest;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValue;
import emu.grasscutter.game.quest.enums.QuestTrigger;
import emu.grasscutter.game.quest.handlers.QuestBaseHandler;

@QuestValue(QuestTrigger.QUEST_COND_QUEST_VAR_EQUAL)
public class ConditionQuestVarEqual extends QuestBaseHandler {

    @Override
    public boolean execute(GameQuest quest, QuestData.QuestCondition condition, String paramStr, int... params) {
        boolean result=true;
        try {

            int[] questVars = quest.getMainQuest().getQuestVars();
            int[] param = condition.getParam();
            for(int i=0;i<param.length;i++){
                if (questVars[i]!=param[i]) {
                    result=false;
                    break;
                }
            }

        }
        catch (Exception e){
            result=false;
        }
        return result;
    }
}
