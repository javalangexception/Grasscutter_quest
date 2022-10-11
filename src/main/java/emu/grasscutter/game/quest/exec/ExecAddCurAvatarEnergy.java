package emu.grasscutter.game.quest.exec;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValue;
import emu.grasscutter.game.quest.enums.QuestTrigger;
import emu.grasscutter.game.quest.handlers.QuestExecHandler;
import emu.grasscutter.net.proto.PropChangeReasonOuterClass;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;


@QuestValue(QuestTrigger.QUEST_EXEC_ADD_CUR_AVATAR_ENERGY)
public class ExecAddCurAvatarEnergy extends QuestExecHandler {
    @Override
    public boolean execute(GameQuest quest, QuestData.QuestExecParam condition, String... paramStr) {
       quest.getOwner().getTeamManager().getCurrentAvatarEntity().addEnergy(9999, PropChangeReasonOuterClass.PropChangeReason.PROP_CHANGE_REASON_FINISH_QUEST);
        return true;
    }
}
