package nro.models.npc_list;

import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.services.Service;
import nro.models.map.service.NpcService;

/**
 * NPC SGohan - ID: 111
 * 
 * @author Your Name
 */
public class SGohan extends Npc {

    public SGohan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            // Menu chính khi click vào NPC
            createOtherMenu(player, 0, 
                "Xin chào " + player.name + "!\n"
                + "Ta là SGohan, một chiến binh mạnh mẽ.\n"
                + "Con muốn làm gì nào?",
                "Hướng\ndẫn", 
                "Nhiệm\nvụ", 
                "Cửa\nhàng",
                "Đóng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (player.idMark.getIndexMenu()) {
                case 0: // Menu chính
                    switch (select) {
                        case 0: // Hướng dẫn
                            Service.gI().sendThongBao(player, 
                                "Đây là NPC SGohan!\n"
                                + "Bạn có thể thêm các chức năng tại đây.");
                            break;
                            
                        case 1: // Nhiệm vụ
                            createOtherMenu(player, 1,
                                "Ta có một số nhiệm vụ dành cho con.\n"
                                + "Con muốn nhận nhiệm vụ nào?",
                                "Nhiệm vụ 1",
                                "Nhiệm vụ 2",
                                "Quay lại");
                            break;
                            
                        case 2: // Cửa hàng
                            // TODO: Mở cửa hàng
                            Service.gI().sendThongBao(player, 
                                "Chức năng cửa hàng chưa được thêm!");
                            break;
                            
                        case 3: // Đóng
                            // Không làm gì
                            break;
                    }
                    break;
                    
                case 1: // Menu nhiệm vụ
                    switch (select) {
                        case 0: // Nhiệm vụ 1
                            Service.gI().sendThongBao(player, 
                                "Bạn đã nhận nhiệm vụ 1!");
                            break;
                            
                        case 1: // Nhiệm vụ 2
                            Service.gI().sendThongBao(player, 
                                "Bạn đã nhận nhiệm vụ 2!");
                            break;
                            
                        case 2: // Quay lại
                            openBaseMenu(player);
                            break;
                    }
                    break;
            }
        }
    }
}
