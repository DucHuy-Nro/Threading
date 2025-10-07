package nro.models.npc_list;

import nro.models.consts.ConstNpc;
import nro.models.map.Zone;
import nro.models.map.service.MapService;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.services.Service;

/**
 * NPC Đường Tăng - Ngũ Hành Sơn
 */
public class DuongTang extends Npc {

    public DuongTang(int mapId, int status, int cx, int cy, int tempId, int avatar) {
        super(mapId, status, cx, cy, tempId, avatar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (!canOpenNpc(player)) {
            return;
        }

        // Ở Làng Aru (map 0)
        if (this.mapId == 0) {
            createOtherMenu(player, ConstNpc.BASE_MENU,
                "|7|NGŨ HÀNH SƠN\n"
                + "|2|A mi phò phò, thí chủ hãy giúp giải cứu đồ đệ của bần tăng "
                + "đang bị phong ấn tại Ngũ Hành Sơn.\n"
                + "|3|Tại đây có nhiều quái vật mạnh và phần thưởng hấp dẫn!",
                "Đi Ngũ Hành Sơn", "Hướng dẫn", "Từ chối");
        }
        // Ở Ngũ Hành Sơn (map 122, 123, 124)
        else if (this.mapId >= 122 && this.mapId <= 124) {
            createOtherMenu(player, ConstNpc.BASE_MENU,
                "A mi phò phò!\n"
                + "Thí chủ hãy thu thập bùa 'Giải Khai Phong Ấn'.\n"
                + "Cần mỗi loại 10 cái để giải cứu Ngộ Không.",
                "Đổi đào chín", "Giải phong ấn", "Về Làng Aru", "Đóng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (!canOpenNpc(player)) {
            return;
        }

        // Xử lý tại Làng Aru
        if (this.mapId == 0 && player.iDMark.isBaseMenu()) {
            switch (select) {
                case 0: // Đi Ngũ Hành Sơn
                    goToNguHanhSon(player);
                    break;
                case 1: // Hướng dẫn
                    Service.getInstance().sendThongBao(player,
                        "HƯỚNG DẪN NGŨ HÀNH SƠN:\n"
                        + "• Đánh quái để nhặt bùa GIẢI, KHAI, PHONG, ẤN\n"
                        + "• Thu thập mỗi loại 10 cái\n"
                        + "• Đổi đào xanh → đào chín (10 đào xanh = 1 đào chín)\n"
                        + "• Mang bùa đến gặp Đường Tăng để giải phong ấn\n"
                        + "• Phần thưởng: Trang phục Ngộ Không, Gậy Như Ý...");
                    break;
            }
        }
        // Xử lý tại Ngũ Hành Sơn
        else if (this.mapId >= 122 && this.mapId <= 124 && player.iDMark.isBaseMenu()) {
            switch (select) {
                case 0: // Đổi đào chín
                    Service.getInstance().sendThongBao(player, 
                        "Chức năng đổi đào đang được phát triển!");
                    break;
                    
                case 1: // Giải phong ấn
                    Service.getInstance().sendThongBao(player,
                        "Chức năng giải phong ấn đang được phát triển!");
                    break;
                    
                case 2: // Về Làng Aru
                    veNha(player);
                    break;
            }
        }
    }

    private void goToNguHanhSon(Player player) {
        try {
            Zone zone = MapService.gI().getZoneJoinByMapIdAndZoneId(player, 124, 0);
            if (zone != null) {
                player.location.x = 100;
                player.location.y = 360;
                MapService.gI().goToMap(player, zone);
                Service.getInstance().clearMap(player);
                zone.mapInfo(player);
                player.zone.loadAnotherToMe(player);
                player.zone.load_Me_To_Another(player);
                Service.getInstance().sendThongBao(player, "Chào mừng đến Ngũ Hành Sơn!");
            } else {
                Service.getInstance().sendThongBao(player, "Không thể vào Ngũ Hành Sơn lúc này!");
            }
        } catch (Exception e) {
            Service.getInstance().sendThongBao(player, "Lỗi khi vào Ngũ Hành Sơn!");
        }
    }

    private void veNha(Player player) {
        try {
            Zone zone = MapService.gI().getZoneJoinByMapIdAndZoneId(player, 0, 0);
            if (zone != null) {
                player.location.x = 600;
                player.location.y = 432;
                MapService.gI().goToMap(player, zone);
                Service.getInstance().clearMap(player);
                zone.mapInfo(player);
                player.zone.loadAnotherToMe(player);
                player.zone.load_Me_To_Another(player);
                Service.getInstance().sendThongBao(player, "Đã về Làng Aru!");
            }
        } catch (Exception e) {
            Service.getInstance().sendThongBao(player, "Lỗi khi về Làng Aru!");
        }
    }
}
