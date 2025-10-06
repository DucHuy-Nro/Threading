package nro.models.npc_list;

import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.shop.ShopService;

/**
 * NPC CÓ SHOP MẪU - Copy file này để tạo NPC mới
 * 
 * CÁCH DÙNG:
 * 1. Copy file này
 * 2. Đổi tên class (VD: ShopGao, ShopVuKhi...)
 * 3. Sửa tên shop trong opendShop()
 * 4. Đăng ký trong NpcFactory.java
 */
public class NpcShopMau extends Npc {

    public NpcShopMau(int mapId, int status, int cx, int cy, int tempId, int avartar) {
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
                + "Cần mua gì không?",
                
                "Mở\nShop",     // ← Option 0: Mở shop
                "Đóng");        // ← Option 1: Đóng
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (select) {
                // ==================================================
                // XỬ LÝ OPTION - Sửa phần này
                // ==================================================
                
                case 0: // Mở shop
                    // ========================================
                    // DÙNG SHOP CÓ SẴN (CÁCH 1 - NHANH):
                    // ========================================
                    ShopService.gI().opendShop(player, "BUNMA", true);
                    //                                  ↑↑↑↑↑↑
                    // Đổi thành tên shop khác:
                    // - "BUNMA" = Shop Bulma
                    // - "DENDE" = Shop Dende  
                    // - "APPULE" = Shop Appule
                    // - "KARIN" = Shop Karin
                    // - "SANTA" = Shop Santa
                    // - "BILL" = Shop Bill
                    // - "QUY_LAO" = Shop Quy Lão
                    
                    // ========================================
                    // HOẶC DÙNG SHOP MỚI TẠO (CÁCH 2):
                    // ========================================
                    // ShopService.gI().opendShop(player, "SHOP_SGOHAN", true);
                    //                                     ↑↑↑↑↑↑↑↑↑↑↑
                    // Tên shop mới tạo trong database
                    break;
                    
                case 1: // Đóng
                    // Không làm gì
                    break;
            }
        }
    }
}

// ==================================================
// CÁC VÍ DỤ NÂNG CAO:
// ==================================================

// 1. NPC CÓ NHIỀU SHOP:
/*
@Override
public void openBaseMenu(Player player) {
    createOtherMenu(player, 0, 
        "Chọn shop nào?",
        "Shop\nvũ khí",
        "Shop\nphụ kiện",
        "Shop\nđặc biệt",
        "Đóng");
}

@Override
public void confirmMenu(Player player, int select) {
    switch (select) {
        case 0:
            ShopService.gI().opendShop(player, "BUNMA", true);
            break;
        case 1:
            ShopService.gI().opendShop(player, "SANTA", true);
            break;
        case 2:
            ShopService.gI().opendShop(player, "BILL", true);
            break;
    }
}
*/

// 2. SHOP CÓ ĐIỀU KIỆN:
/*
import nro.models.services.Service;
import nro.models.consts.ConstPlayer;

case 0:
    // Chỉ người Trái Đất mới mua được
    if (player.gender == ConstPlayer.TRAI_DAT) {
        ShopService.gI().opendShop(player, "BUNMA", true);
    } else {
        Service.gI().sendThongBao(player, 
            "Chỉ người Trái Đất mới mua được!");
    }
    break;
*/

// 3. SHOP YÊU CẦU SỨC MẠNH:
/*
import nro.models.services.Service;

case 0:
    if (player.nPoint.power >= 10000) {
        ShopService.gI().opendShop(player, "SHOP_VIP", true);
    } else {
        Service.gI().sendThongBao(player, 
            "Cần sức mạnh 10,000 để vào shop VIP!");
    }
    break;
*/

// 4. SHOP THEO GENDER:
/*
import nro.models.consts.ConstPlayer;

case 0:
    switch (player.gender) {
        case ConstPlayer.TRAI_DAT:
            ShopService.gI().opendShop(player, "BUNMA", true);
            break;
        case ConstPlayer.NAMEC:
            ShopService.gI().opendShop(player, "DENDE", true);
            break;
        case ConstPlayer.XAYDA:
            ShopService.gI().opendShop(player, "APPULE", true);
            break;
    }
    break;
*/
