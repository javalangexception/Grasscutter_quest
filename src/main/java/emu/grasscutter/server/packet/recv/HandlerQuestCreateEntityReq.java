package emu.grasscutter.server.packet.recv;


import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.GadgetData;
import emu.grasscutter.data.excels.ItemData;
import emu.grasscutter.data.excels.MonsterData;
import emu.grasscutter.game.entity.EntityGadget;
import emu.grasscutter.game.entity.EntityItem;
import emu.grasscutter.game.entity.EntityMonster;
import emu.grasscutter.game.entity.GameEntity;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.QuestCreateEntityReqOuterClass;
import emu.grasscutter.net.proto.VectorOuterClass;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketQuestCreateEntityRsp;
import emu.grasscutter.utils.Position;

@Opcodes(PacketOpcodes.QuestCreateEntityReq)
public class HandlerQuestCreateEntityReq extends PacketHandler {
    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        QuestCreateEntityReqOuterClass.QuestCreateEntityReq req = QuestCreateEntityReqOuterClass.QuestCreateEntityReq.parseFrom(payload);
        Player player=session.getPlayer();
        VectorOuterClass.Vector vec = req.getEntity().getPos();
        Position pos = new Position(vec.getX(),vec.getY(), vec.getZ());
        GameEntity entity = null;
        switch (req.getEntity().getEntityCase()){
            case NPC_ID :
                break;
            case MONSTER_ID:
                MonsterData monsterData = GameData.getMonsterDataMap().get(req.getEntity().getMonsterId());
                entity=new EntityMonster(player.getScene(),monsterData,pos,req.getEntity().getLevel());
                break;
            case ITEM_ID:
                ItemData itemData = GameData.getItemDataMap().get(req.getEntity().getItemId());
                entity = new EntityItem(player.getScene(), null, itemData,pos
                    , 1, true);
                break;
            case GADGET_ID:
                GadgetData gadgetData = GameData.getGadgetDataMap().get(req.getEntity().getGadgetId());
                entity = new EntityGadget(session.getPlayer().getScene(),gadgetData.getId(),pos);
                break;
            case ENTITY_NOT_SET:
                break;
        }
        player.getScene().addEntity(entity);
        session.send(new PacketQuestCreateEntityRsp(req.getParentQuestId(),req.getQuestId(),entity.getId(),req.getEntity(),req.getIsRewind()));
    }
}
