package emu.grasscutter.game.quest.exec;

import emu.grasscutter.GameConstants;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.AvatarSkillDepotData;
import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.ElementType;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValue;
import emu.grasscutter.game.quest.enums.QuestTrigger;
import emu.grasscutter.game.quest.handlers.QuestExecHandler;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketAbilityChangeNotify;
import emu.grasscutter.server.packet.send.PacketAvatarFightPropNotify;
import emu.grasscutter.server.packet.send.PacketAvatarSkillDepotChangeNotify;

import java.util.Arrays;

@QuestValue(QuestTrigger.QUEST_EXEC_CHANGE_AVATAR_ELEMET)
public class ExecChangeAvatarElement extends QuestExecHandler {
    @Override
    public boolean execute(GameQuest quest, QuestData.QuestExecParam condition, String... paramStr) {
        var param = Arrays.stream(paramStr)
            .filter(i -> !i.isBlank())
            .mapToInt(Integer::parseInt)
            .toArray();
        int mainCharacterId = quest.getOwner().getMainCharacterId();
        Player player = quest.getOwner();
        GameSession session = player.getSession();
        Avatar avatar = player.getAvatars().getAvatarById(mainCharacterId);
        int skillDepotId = ElementType.getTypeByValue(param[0]).getDepotValue();
        switch (mainCharacterId) {
            case GameConstants.MAIN_CHARACTER_MALE -> skillDepotId += 500;
            case GameConstants.MAIN_CHARACTER_FEMALE -> skillDepotId += 700;
            default -> {
                return false;
            }
        }
        AvatarSkillDepotData skillDepot = GameData.getAvatarSkillDepotDataMap().get(skillDepotId);
        if (skillDepot == null || skillDepot.getId() == avatar.getSkillDepotId()) {
            return false;
        }

        // Set skill depot
        avatar.setSkillDepotData(skillDepot);
        EntityAvatar entity = avatar.getAsEntity();
        session.send(new PacketAvatarSkillDepotChangeNotify(avatar));
        session.send(new PacketAbilityChangeNotify(entity));
        session.send(new PacketAvatarFightPropNotify(avatar));
        avatar.save();
        return true;
    }
}
