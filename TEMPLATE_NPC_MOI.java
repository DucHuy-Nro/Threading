package nro.models.npc_list;

import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.services.Service;

/**
 * Template tạo NPC mới nhanh
 * 
 * CÁCH DÙNG:
 * 1. Copy file này
 * 2. Đổi tên class thành tên NPC của bạn (VD: ShopBanGao)
 * 3. Sửa menu và chức năng bên trong
 * 4. Thêm vào NpcFactory.java
 */
public class TEMPLATE_NPC_MOI extends Npc {

    public TEMPLATE_NPC_MOI(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            // ==================================================
            // MENU CHÍNH - Sửa phần này
            // ==================================================
            createOtherMenu(player, 0, 
                "Xin chào " + player.name + "!\n"
                + "Đây là menu của NPC mới",  // ← Sửa lời chào
                
                "Option 1",     // ← Sửa tên các option
                "Option 2", 
                "Option 3",
                "Đóng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (player.idMark.getIndexMenu()) {
                case 0: // Menu chính
                    switch (select) {
                        // ==================================================
                        // XỬ LÝ TỪNG OPTION - Sửa phần này
                        // ==================================================
                        
                        case 0: // Option 1
                            Service.gI().sendThongBao(player, 
                                "Bạn chọn option 1!");
                            // TODO: Thêm code xử lý
                            break;
                            
                        case 1: // Option 2
                            Service.gI().sendThongBao(player, 
                                "Bạn chọn option 2!");
                            // TODO: Thêm code xử lý
                            break;
                            
                        case 2: // Option 3
                            Service.gI().sendThongBao(player, 
                                "Bạn chọn option 3!");
                            // TODO: Thêm code xử lý
                            break;
                            
                        case 3: // Đóng
                            // Không làm gì
                            break;
                    }
                    break;
            }
        }
    }
}

// ==================================================
// CÁC VÍ DỤ CODE THƯỜNG DÙNG:
// ==================================================

// 1. MỞ SHOP:
/*
import nro.models.shop.ShopService;

case 0:
    ShopService.gI().openShopNormal(player, 
        "Cửa hàng", 
        Shop.BUNMA_SHOP,  // ID shop trong DB
        -1, null, 0, 0);
    break;
*/

// 2. DỊCH CHUYỂN MAP:
/*
import nro.models.map.service.ChangeMapService;

case 0:
    ChangeMapService.gI().changeMap(player, 
        5,      // Map ID
        -1,     // Zone ID (-1 = auto)
        100,    // X coordinate
        200);   // Y coordinate
    break;
*/

// 3. KIỂM TRA VÀ TRỪ TIỀN:
/*
case 0:
    if (player.inventory.gold >= 10000) {
        player.inventory.gold -= 10000;
        Service.gI().sendMoney(player);
        Service.gI().sendThongBao(player, "Mua thành công!");
    } else {
        Service.gI().sendThongBao(player, "Không đủ vàng!");
    }
    break;
*/

// 4. KIỂM TRA VÀ TRỪ NGỌC:
/*
case 0:
    if (player.inventory.gem >= 50) {
        player.inventory.gem -= 50;
        Service.gI().sendMoney(player);
        Service.gI().sendThongBao(player, "Mua thành công!");
    } else {
        Service.gI().sendThongBao(player, "Không đủ ngọc!");
    }
    break;
*/

// 5. TẶNG VẬT PHẨM:
/*
import nro.models.item.Item;
import nro.models.services.ItemService;
import nro.models.services.InventoryService;

case 0:
    Item item = ItemService.gI().createNewItem((short) 457); // 457 = Đậu thần
    item.quantity = 10;
    InventoryService.gI().addItemBag(player, item);
    InventoryService.gI().sendItemBags(player);
    Service.gI().sendThongBao(player, "Nhận được 10 đậu thần!");
    break;
*/

// 6. MENU PHỤ (NHIỀU TẦNG):
/*
case 0: // Option mở menu phụ
    createOtherMenu(player, 1,  // indexMenu = 1
        "Đây là menu phụ",
        "Sub option 1",
        "Sub option 2",
        "Quay lại");
    break;

// Thêm case xử lý menu phụ:
case 1: // Menu phụ (indexMenu = 1)
    switch (select) {
        case 0: // Sub option 1
            Service.gI().sendThongBao(player, "Sub 1");
            break;
        case 1: // Sub option 2
            Service.gI().sendThongBao(player, "Sub 2");
            break;
        case 2: // Quay lại
            openBaseMenu(player); // Quay về menu chính
            break;
    }
    break;
*/
