// ============================================
// CODE SỬA LỖI - LINH HOẠT HƠN
// Thay thế hàm buySkill9() cũ bằng hàm này
// ============================================

/**
 * Mua skill 9 (Tuyệt kỹ) từ shop - Tự động học skill
 * PHIÊN BẢN SỬA LỖI - Linh hoạt với mọi item ID
 */
private void buySkill9(Player player, ItemShop is) {
    int itemTempId = is.temp.id;
    int itemGender = is.temp.gender;
    String itemName = is.temp.name;
    
    // Debug: In ra thông tin item
    System.out.println("DEBUG buySkill9:");
    System.out.println("- Item ID: " + itemTempId);
    System.out.println("- Item Gender: " + itemGender);
    System.out.println("- Item Name: " + itemName);
    System.out.println("- Player Gender: " + player.gender);
    
    // Xác định skill ID theo GENDER của item
    int skillId = -1;
    String skillName = "";
    
    switch (itemGender) {
        case 0: // Trái Đất
            skillId = 24; // Skill.SUPER_KAME
            skillName = "Super Kamejoko";
            if (player.gender != 0) {
                Service.gI().sendThongBao(player, "❌ Skill này chỉ dành cho Trái Đất!");
                return;
            }
            break;
            
        case 1: // Namec
            skillId = 26; // Skill.MA_PHONG_BA
            skillName = "Ma Phong Ba";
            if (player.gender != 1) {
                Service.gI().sendThongBao(player, "❌ Skill này chỉ dành cho Namec!");
                return;
            }
            break;
            
        case 2: // Xayda
            skillId = 25; // Skill.LIEN_HOAN_CHUONG
            skillName = "Ca Đíc Liên Hoàn Chưởng";
            if (player.gender != 2) {
                Service.gI().sendThongBao(player, "❌ Skill này chỉ dành cho Xayda!");
                return;
            }
            break;
            
        default:
            Service.gI().sendThongBao(player, 
                "❌ Item không hợp lệ!\n"
                + "Gender: " + itemGender);
            return;
    }
    
    // Lấy cấp skill từ TÊN item
    // Tên dạng: "Sách tuyệt kỹ 1", "Tuyệt kỹ Cađíc LH chưởng cấp 2", v.v.
    int targetLevel = 1;
    
    try {
        // Tách số từ tên
        String[] parts = itemName.split(" ");
        String lastPart = parts[parts.length - 1];
        
        // Thử parse số cuối cùng
        targetLevel = Integer.parseInt(lastPart);
        
        // Kiểm tra cấp hợp lệ (1-10)
        if (targetLevel < 1 || targetLevel > 10) {
            Service.gI().sendThongBao(player, 
                "❌ Cấp skill không hợp lệ: " + targetLevel);
            return;
        }
    } catch (NumberFormatException e) {
        // Nếu không parse được, mặc định cấp 1
        Service.gI().sendThongBao(player, 
            "⚠️ Không thể xác định cấp skill từ tên!\n"
            + "Tên item: " + itemName + "\n"
            + "Mặc định: Cấp 1");
        targetLevel = 1;
    }
    
    System.out.println("- Target Level: " + targetLevel);
    System.out.println("- Skill ID: " + skillId);
    
    // Lấy skill hiện tại
    Skill currentSkill = SkillUtil.getSkillbyId(player, skillId);
    int currentLevel = (currentSkill != null && currentSkill.point > 0) ? currentSkill.point : 0;
    
    System.out.println("- Current Level: " + currentLevel);
    
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
        return;
    }
    
    // Học skill
    Skill newSkill = SkillUtil.createSkill(skillId, targetLevel);
    if (newSkill != null) {
        SkillUtil.setSkill(player, newSkill);
        
        // Thông báo thành công
        Service.gI().sendThongBao(player, 
            "✅ HỌC TUYỆT KỸ THÀNH CÔNG!\n"
            + "━━━━━━━━━━━━━━━━━━━\n"
            + "🌟 " + skillName + "\n"
            + "📈 Cấp độ: " + currentLevel + " → " + targetLevel + "\n"
            + "💥 Dame: +" + (targetLevel * 50 + 500) + "%\n"
            + "━━━━━━━━━━━━━━━━━━━\n\n"
            + "Hãy vào Bảng Kỹ Năng để xem!");
        
        System.out.println("✅ Học skill thành công!");
        
        // Refresh
        InventoryService.gI().sendItemBags(player);
        Service.gI().sendMoney(player);
    } else {
        Service.gI().sendThongBao(player, 
            "❌ CÓ LỖI KHI HỌC SKILL!\n\n"
            + "Skill ID: " + skillId + "\n"
            + "Level: " + targetLevel + "\n\n"
            + "Vui lòng liên hệ Admin!");
        System.out.println("❌ SkillUtil.createSkill trả về null!");
    }
}
