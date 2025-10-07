// ============================================
// CODE ĐỂ THÊM VÀO ShopService.java
// ============================================

// ============================================
// PHẦN 1: THÊM IMPORT (ở đầu file)
// ============================================
import nro.models.skill.Skill;
import nro.models.utils.SkillUtil;


// ============================================
// PHẦN 2: THÊM VÀO HÀM buyItem()
// Tìm dòng 710-714, THÊM SAU đoạn code:
// if (shop.typeShop == ShopService.KINANG_SHOP) {
//     learnKyNang(player, is);
//     return;
// }
// ============================================

// Shop Skill 9 (Tuyệt Kỹ) - Tự động học skill
if (shop.tagName != null && shop.tagName.equals("SHOP_TUYET_KY")) {
    buySkill9(player, is);
    return;
}


// ============================================
// PHẦN 3: THÊM HÀM MỚI (ở cuối class ShopService, trước dấu } cuối)
// ============================================

/**
 * Mua skill 9 (Tuyệt kỹ) từ shop - Tự động học skill
 * 
 * @param player Player mua skill
 * @param is ItemShop được mua
 */
private void buySkill9(Player player, ItemShop is) {
    int itemTempId = is.temp.id;
    
    int skillId = -1;
    int targetLevel = 1;
    int playerGender = player.gender;
    
    // Xác định skill ID và cấp theo item
    if (itemTempId == 1044 || (itemTempId >= 2001 && itemTempId <= 2009)) {
        // Trái Đất - Super Kamejoko
        skillId = 24; // Skill.SUPER_KAME
        if (itemTempId == 1044) {
            targetLevel = 1;
        } else {
            targetLevel = (itemTempId - 2001) + 2;
        }
        if (playerGender != 0) {
            Service.gI().sendThongBao(player, "❌ Skill này chỉ dành cho Trái Đất!");
            return;
        }
    } else if (itemTempId == 1211 || (itemTempId >= 2011 && itemTempId <= 2019)) {
        // Namec - Ma Phong Ba
        skillId = 26; // Skill.MA_PHONG_BA
        if (itemTempId == 1211) {
            targetLevel = 1;
        } else {
            targetLevel = (itemTempId - 2011) + 2;
        }
        if (playerGender != 1) {
            Service.gI().sendThongBao(player, "❌ Skill này chỉ dành cho Namec!");
            return;
        }
    } else if (itemTempId == 1212 || (itemTempId >= 2021 && itemTempId <= 2029)) {
        // Xayda - Ca Đíc Liên Hoàn Chưởng
        skillId = 25; // Skill.LIEN_HOAN_CHUONG
        if (itemTempId == 1212) {
            targetLevel = 1;
        } else {
            targetLevel = (itemTempId - 2021) + 2;
        }
        if (playerGender != 2) {
            Service.gI().sendThongBao(player, "❌ Skill này chỉ dành cho Xayda!");
            return;
        }
    } else {
        Service.gI().sendThongBao(player, "❌ Item không hợp lệ!");
        return;
    }
    
    // Lấy skill hiện tại
    Skill currentSkill = SkillUtil.getSkillbyId(player, skillId);
    int currentLevel = (currentSkill != null && currentSkill.point > 0) ? currentSkill.point : 0;
    
    // Check 1: Đã học cấp này chưa?
    if (currentLevel >= targetLevel) {
        Service.gI().sendThongBao(player, 
            "❌ BẠN ĐÃ HỌC CẤP NÀY!\n\n"
            + "📈 Cấp hiện tại: " + currentLevel + "\n"
            + "🎯 Cấp muốn mua: " + targetLevel + "\n\n"
            + "Hãy mua cấp cao hơn!");
        return;
    }
    
    // Check 2: Phải học tuần tự
    if (targetLevel > currentLevel + 1) {
        Service.gI().sendThongBao(player, 
            "❌ BẠN PHẢI HỌC TUẦN TỰ!\n\n"
            + "📈 Cấp hiện tại: " + currentLevel + "\n"
            + "🎯 Cấp tiếp theo: " + (currentLevel + 1) + "\n"
            + "❌ Cấp muốn mua: " + targetLevel + "\n\n"
            + "Hãy học cấp " + (currentLevel + 1) + " trước!");
        return;
    }
    
    // Check 3: Đủ tiền shop
    if (!subMoneyByItemShop(player, is)) {
        // Hàm subMoneyByItemShop đã thông báo lỗi
        return;
    }
    
    // Học skill
    Skill newSkill = SkillUtil.createSkill(skillId, targetLevel);
    if (newSkill != null) {
        SkillUtil.setSkill(player, newSkill);
        
        // Lấy tên skill
        String skillName = "";
        switch (skillId) {
            case 24: 
                skillName = "Super Kamejoko"; 
                break;
            case 25: 
                skillName = "Ca Đíc Liên Hoàn Chưởng"; 
                break;
            case 26: 
                skillName = "Ma Phong Ba"; 
                break;
        }
        
        // Thông báo thành công
        Service.gI().sendThongBao(player, 
            "✅ HỌC TUYỆT KỸ THÀNH CÔNG!\n"
            + "━━━━━━━━━━━━━━━━━━━\n"
            + "🌟 " + skillName + "\n"
            + "📈 Cấp độ: " + currentLevel + " → " + targetLevel + "\n"
            + "💥 Dame: +" + (targetLevel * 50 + 500) + "%\n"
            + "━━━━━━━━━━━━━━━━━━━\n\n"
            + "Hãy vào Bảng Kỹ Năng để xem!");
        
        // Refresh
        InventoryService.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
    } else {
        Service.gI().sendThongBao(player, 
            "❌ CÓ LỖI KHI HỌC SKILL!\n\n"
            + "Vui lòng liên hệ Admin!");
    }
}
