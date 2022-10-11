package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.CreateEntityInfoOuterClass;
import emu.grasscutter.net.proto.QuestCreateEntityRspOuterClass;

public class PacketQuestCreateEntityRsp extends BasePacket {
    public PacketQuestCreateEntityRsp(int parentQuestID, int questId, int entity_id, CreateEntityInfoOuterClass.CreateEntityInfo info, Boolean isRewind) {
        super(PacketOpcodes.QuestCreateEntityRsp);
        QuestCreateEntityRspOuterClass.QuestCreateEntityRsp.Builder builder = QuestCreateEntityRspOuterClass.QuestCreateEntityRsp.newBuilder();
        builder.setEntityId(entity_id).setEntity(info).setQuestId(questId).setParentQuestId(parentQuestID).setRetcode(0).setIsRewind(isRewind).setRetcode(0);
        this.setData(builder);

    }
}
