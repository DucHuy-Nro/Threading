package nro.models.npc_list;

import nro.models.consts.ConstNpc;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.services.Service;

/**
 * NPC Khu Vực - Hiển thị tọa độ
 */
public class KhuVuc extends Npc {

    public KhuVuc(int mapId, int status, int cx, int cy, int tempId, int avatar) {
        super(mapId, status, cx, cy, tempId, avatar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            String toaDo = "🗺️ THÔNG TIN VỊ TRÍ\n"
                + "━━━━━━━━━━━━━━━━━━━\n"
                + "📍 Map ID: " + player.zone.map.mapId + "\n"
                + "🔢 Zone ID: " + player.zone.zoneId + "\n"
                + "📐 Tọa độ X: " + player.location.x + "\n"
                + "📐 Tọa độ Y: " + player.location.y + "\n"
                + "━━━━━━━━━━━━━━━━━━━\n"
                + "💡 Sử dụng để đặt NPC hoặc teleport!";
            
            createOtherMenu(player, ConstNpc.BASE_MENU,
                toaDo,
                "Xem lại", "Đóng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.idMark.isBaseMenu()) {
                if (select == 0) {
                    // Xem lại tọa độ
                    openBaseMenu(player);
                }
            }
        }
    }
}
