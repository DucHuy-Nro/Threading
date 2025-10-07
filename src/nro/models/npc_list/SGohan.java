package nro.models.npc_list;

import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.services.InventoryService;
import nro.models.services.Service;
import nro.models.skill.Skill;
import nro.models.utils.SkillUtil;

/**
 * NPC SGohan - Bán Skill 9 (Tuyệt kỹ) bằng Đá Ngũ Sắc
 * 
 * @author Assistant
 */
public class SGohan extends Npc {

    // ID item Đá Ngũ Sắc
    private static final short DA_NGU_SAC = 674;
    
    // Giá skill theo cấp (đá ngũ sắc)
    private static final int[] SKILL_PRICES = {
        1,      // Cấp 1:  1 đá
        100,    // Cấp 2:  100 đá
        200,    // Cấp 3:  200 đá
        500,    // Cấp 4:  500 đá
        1000,   // Cấp 5:  1000 đá
        1500,   // Cấp 6:  1500 đá
        2000,   // Cấp 7:  2000 đá
        2500,   // Cấp 8:  2500 đá
        3000,   // Cấp 9:  3000 đá
        4000    // Cấp 10: 4000 đá
    };

    public SGohan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            // Xác định tên skill theo gender
            String skillName = getSkillNameByGender(player);
            
            createOtherMenu(player, 0, 
                "Xin chào " + player.name + "!\n"
                + "Ta là SGohan, chuyên dạy tuyệt kỹ.\n\n"
                + "🌟 MUA TUYỆT KỸ BẰNG ĐÁ NGŨ SẮC 🌟\n"
                + "━━━━━━━━━━━━━━━━━━━\n"
                + "Tuyệt kỹ của con: " + skillName + "\n"
                + "Cấp hiện tại: " + getCurrentSkillPoint(player) + "/10\n"
                + "━━━━━━━━━━━━━━━━━━━\n"
                + "💎 Đá ngũ sắc: " + getDaNguSacCount(player) + "\n\n"
                + "Chọn cấp muốn mua:",
                "Cấp 1-5", 
                "Cấp 6-10",
                "Đóng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (!canOpenNpc(player)) {
            return;
        }

        switch (player.idMark.getIndexMenu()) {
            case 0: // Menu chính
                switch (select) {
                    case 0: // Cấp 1-5
                        showSkillLevelMenu(player, 1, 5);
                        break;
                    case 1: // Cấp 6-10
                        showSkillLevelMenu(player, 6, 10);
                        break;
                    case 2: // Đóng
                        break;
                }
                break;
                
            case 1: // Menu cấp 1-5
                if (select >= 0 && select <= 4) {
                    buySkill(player, select + 1);
                } else if (select == 5) {
                    openBaseMenu(player);
                }
                break;
                
            case 2: // Menu cấp 6-10
                if (select >= 0 && select <= 4) {
                    buySkill(player, select + 6);
                } else if (select == 5) {
                    openBaseMenu(player);
                }
                break;
        }
    }

    // Hiển thị menu các cấp skill
    private void showSkillLevelMenu(Player player, int fromLevel, int toLevel) {
        String skillName = getSkillNameByGender(player);
        int currentLevel = getCurrentSkillPoint(player);
        int daNguSac = getDaNguSacCount(player);
        
        StringBuilder menu = new StringBuilder();
        menu.append("🌟 MUA TUYỆT KỸ: ").append(skillName).append("\n");
        menu.append("━━━━━━━━━━━━━━━━━━━\n");
        menu.append("📊 Cấp hiện tại: ").append(currentLevel).append("/10\n");
        menu.append("💎 Đá ngũ sắc: ").append(daNguSac).append("\n");
        menu.append("━━━━━━━━━━━━━━━━━━━\n\n");
        
        for (int i = fromLevel; i <= toLevel; i++) {
            menu.append("Cấp ").append(i).append(": ");
            
            if (i <= currentLevel) {
                menu.append("✅ Đã học\n");
            } else {
                menu.append(SKILL_PRICES[i - 1]).append(" đá");
                if (daNguSac >= SKILL_PRICES[i - 1]) {
                    menu.append(" ✅\n");
                } else {
                    menu.append(" ❌\n");
                }
            }
        }
        
        int menuIndex = (fromLevel == 1) ? 1 : 2;
        createOtherMenu(player, menuIndex, menu.toString(),
            "Cấp " + fromLevel,
            "Cấp " + (fromLevel + 1),
            "Cấp " + (fromLevel + 2),
            "Cấp " + (fromLevel + 3),
            "Cấp " + (fromLevel + 4),
            "Quay lại");
    }

    // Mua skill
    private void buySkill(Player player, int level) {
        // Lấy skill hiện tại
        int skillId = getSkillIdByGender(player);
        Skill currentSkill = SkillUtil.getSkillbyId(player, skillId);
        int currentLevel = (currentSkill != null && currentSkill.point > 0) ? currentSkill.point : 0;
        
        // Kiểm tra đã học cấp này chưa
        if (currentLevel >= level) {
            Service.gI().sendThongBao(player, 
                "❌ Bạn đã học cấp " + level + " rồi!\n"
                + "Cấp hiện tại: " + currentLevel);
            return;
        }
        
        // Kiểm tra phải học tuần tự
        if (level > currentLevel + 1) {
            Service.gI().sendThongBao(player, 
                "❌ Bạn phải học tuần tự!\n"
                + "Học cấp " + (currentLevel + 1) + " trước.");
            return;
        }
        
        // Kiểm tra đá ngũ sắc
        int price = SKILL_PRICES[level - 1];
        Item daNguSac = InventoryService.gI().findItemBag(player, DA_NGU_SAC);
        
        if (daNguSac == null || daNguSac.quantity < price) {
            int missing = price - (daNguSac != null ? daNguSac.quantity : 0);
            Service.gI().sendThongBao(player, 
                "❌ Không đủ Đá Ngũ Sắc!\n\n"
                + "Cần: " + price + " đá\n"
                + "Có: " + (daNguSac != null ? daNguSac.quantity : 0) + " đá\n"
                + "Thiếu: " + missing + " đá");
            return;
        }
        
        // Trừ đá ngũ sắc
        InventoryService.gI().subQuantityItemsBag(player, daNguSac, price);
        InventoryService.gI().sendItemBags(player);
        
        // Học skill (tự động lên cấp)
        Skill newSkill = SkillUtil.createSkill(skillId, level);
        if (newSkill != null) {
            SkillUtil.setSkill(player, newSkill);
            
            // Thông báo thành công
            Service.gI().sendThongBao(player, 
                "✅ HỌC TUYỆT KỸ THÀNH CÔNG!\n\n"
                + "🌟 " + getSkillNameByGender(player) + "\n"
                + "📈 Cấp: " + currentLevel + " → " + level + "\n"
                + "💎 Đã trừ: " + price + " Đá Ngũ Sắc\n"
                + "💰 Còn lại: " + getDaNguSacCount(player) + " đá\n\n"
                + "Hãy vào Bảng Kỹ Năng để xem!");
            
            // Hiệu ứng học skill
            try {
                npcChat(player, "Chúc mừng con đã thành thạo hơn!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Service.gI().sendThongBao(player, "❌ Có lỗi khi học skill!");
        }
        
        // Quay lại menu
        openBaseMenu(player);
    }

    // Lấy skill ID theo gender
    private int getSkillIdByGender(Player player) {
        switch (player.gender) {
            case 0: return Skill.SUPER_KAME;       // Trái Đất
            case 1: return Skill.MA_PHONG_BA;      // Namec
            case 2: return Skill.LIEN_HOAN_CHUONG; // Xayda
            default: return Skill.SUPER_KAME;
        }
    }

    // Lấy tên skill theo gender
    private String getSkillNameByGender(Player player) {
        switch (player.gender) {
            case 0: return "Super Kamejoko";
            case 1: return "Ma Phong Ba";
            case 2: return "Ca Đíc Liên Hoàn Chưởng";
            default: return "Tuyệt Kỹ";
        }
    }

    // Lấy cấp skill hiện tại
    private int getCurrentSkillPoint(Player player) {
        int skillId = getSkillIdByGender(player);
        Skill skill = SkillUtil.getSkillbyId(player, skillId);
        return (skill != null && skill.point > 0) ? skill.point : 0;
    }

    // Đếm số đá ngũ sắc
    private int getDaNguSacCount(Player player) {
        Item item = InventoryService.gI().findItemBag(player, DA_NGU_SAC);
        return (item != null) ? item.quantity : 0;
    }
}
