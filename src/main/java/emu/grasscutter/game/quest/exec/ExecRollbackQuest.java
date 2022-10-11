package emu.grasscutter.game.quest.exec;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValue;
import emu.grasscutter.game.quest.enums.QuestTrigger;
import emu.grasscutter.game.quest.handlers.QuestExecHandler;
@QuestValue(QuestTrigger.QUEST_EXEC_ROLLBACK_QUEST)
public class ExecRollbackQuest extends QuestExecHandler {
    @Override
    public boolean execute(GameQuest quest, QuestData.QuestExecParam condition, String... paramStr) {
        int subId = Integer.parseInt(paramStr[0]);
        GameQuest gameQuest = quest.getOwner().getQuestManager().getQuestById(subId);
        if(gameQuest==null){
            return false;
        }
        gameQuest.start();
        quest.getOwner().getQuestManager().getAddToQuestListUpdateNotify().add(gameQuest);
        return true;
    }
}
