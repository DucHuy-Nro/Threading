package nro.models.npc_list;

import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.services.Service;
import nro.models.server.Maintenance;
import nro.models.server.Manager;
import nro.models.utils.Logger;

/**
 * 🔧 ADMIN PANEL - Quản trị server
 * 
 * Chức năng:
 * 1. Bảo trì 20s với countdown
 * 2. Đá all player (bảo trì tức khắc)
 * 3. Thay đổi EXP server (x1 - x50)
 * 
 * @author Admin Tool
 */
public class AdminPanel extends Npc {

    public AdminPanel(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            // ======================================
            // KIỂM TRA ADMIN
            // ======================================
            if (!player.isAdmin()) {
                createOtherMenu(player, 0, 
                    "⛔ TRUY CẬP BỊ TỪ CHỐI ⛔\n"
                    + "Bạn không có quyền sử dụng panel này!",
                    "Đóng");
                return;
            }
            
            // ======================================
            // MENU ADMIN CHÍNH
            // ======================================
            createOtherMenu(player, 0, 
                "🔧 ADMIN PANEL 🔧\n"
                + "Xin chào Admin " + player.name + "!\n"
                + "─────────────────────\n"
                + "EXP hiện tại: x" + Manager.RATE_EXP_SERVER + "\n"
                + "Chọn chức năng:",
                
                "⏰ Bảo trì\n20s",      // Option 0
                "👢 Đá all\nplayer",    // Option 1
                "⭐ Thay đổi\nEXP",     // Option 2
                "📊 Thông tin\nserver", // Option 3
                "Đóng");                // Option 4
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            // Kiểm tra admin
            if (!player.isAdmin()) {
                Service.gI().sendThongBao(player, "Bạn không có quyền!");
                return;
            }
            
            switch (player.idMark.getIndexMenu()) {
                // ==========================================
                // MENU CHÍNH (indexMenu = 0)
                // ==========================================
                case 0:
                    switch (select) {
                        case 0: // ⏰ Bảo trì 20s
                            // Xác nhận trước khi bảo trì
                            createOtherMenu(player, 1,
                                "⚠️ XÁC NHẬN BẢO TRÌ ⚠️\n"
                                + "Server sẽ bảo trì sau 20 giây!\n"
                                + "─────────────────────\n"
                                + "• Countdown sẽ hiện mỗi giây\n"
                                + "• Tất cả player sẽ nhận thông báo\n"
                                + "• Server sẽ tự động tắt sau 20s\n"
                                + "\n❓ Bạn có chắc chắn?",
                                "✅ Đồng ý",
                                "❌ Hủy");
                            break;
                            
                        case 1: // 👢 Đá all player
                            // Xác nhận trước khi kick all
                            createOtherMenu(player, 2,
                                "⚠️ XÁC NHẬN ĐÁ ALL PLAYER ⚠️\n"
                                + "Server sẽ bảo trì NGAY LẬP TỨC!\n"
                                + "─────────────────────\n"
                                + "• Tất cả player bị kick ngay\n"
                                + "• Server tắt ngay lập tức\n"
                                + "• KHÔNG CÓ COUNTDOWN!\n"
                                + "\n❓ Bạn có chắc chắn?",
                                "✅ Đồng ý",
                                "❌ Hủy");
                            break;
                            
                        case 2: // ⭐ Thay đổi EXP
                            showExpMenu(player);
                            break;
                            
                        case 3: // 📊 Thông tin server
                            showServerInfo(player);
                            break;
                            
                        case 4: // Đóng
                            // Không làm gì
                            break;
                    }
                    break;
                
                // ==========================================
                // XÁC NHẬN BẢO TRÌ 20S (indexMenu = 1)
                // ==========================================
                case 1:
                    if (select == 0) { // Đồng ý
                        startMaintenance20s(player);
                    } else {
                        Service.gI().sendThongBao(player, "Đã hủy bảo trì!");
                    }
                    break;
                
                // ==========================================
                // XÁC NHẬN ĐÁ ALL PLAYER (indexMenu = 2)
                // ==========================================
                case 2:
                    if (select == 0) { // Đồng ý
                        kickAllPlayers(player);
                    } else {
                        Service.gI().sendThongBao(player, "Đã hủy!");
                    }
                    break;
                
                // ==========================================
                // MENU CHỌN EXP (indexMenu = 3)
                // ==========================================
                case 3:
                    handleExpChange(player, select);
                    break;
            }
        }
    }
    
    // ======================================================
    // CHỨC NĂNG 1: BẢO TRÌ 20S
    // ======================================================
    private void startMaintenance20s(Player player) {
        try {
            // Log admin action
            Logger.log(Logger.RED, 
                "[ADMIN ACTION] " + player.name + " đã bật bảo trì 20 giây!");
            
            // Gửi thông báo cho admin
            Service.gI().sendThongBao(player, 
                "✅ Đã kích hoạt bảo trì 20 giây!\n"
                + "Countdown sẽ bắt đầu ngay...");
            
            // Gửi thông báo toàn server
            Service.gI().sendThongBaoAllPlayer(
                "⚠️ THÔNG BÁO BẢO TRÌ ⚠️\n"
                + "Server sẽ bảo trì sau 20 giây!\n"
                + "Hãy thoát game để tránh mất dữ liệu!");
            
            // Kích hoạt countdown 20 giây
            Maintenance.gI().startSeconds(20);
            
        } catch (Exception e) {
            Logger.error("Lỗi khi bảo trì: " + e.getMessage());
            Service.gI().sendThongBao(player, "❌ Lỗi: " + e.getMessage());
        }
    }
    
    // ======================================================
    // CHỨC NĂNG 2: ĐÁ ALL PLAYER
    // ======================================================
    private void kickAllPlayers(Player player) {
        try {
            // Log admin action
            Logger.log(Logger.RED, 
                "[ADMIN ACTION] " + player.name + " đã đá all player!");
            
            // Gửi thông báo cho admin trước
            Service.gI().sendThongBao(player, 
                "✅ Đang kick all player...\n"
                + "Server sẽ tắt ngay!");
            
            // Gửi thông báo toàn server
            Service.gI().sendThongBaoAllPlayer(
                "⚠️ BẢO TRÌ KHẨN CẤP ⚠️\n"
                + "Server sẽ bảo trì NGAY LẬP TỨC!");
            
            // Đợi 2 giây cho player nhận thông báo
            Thread.sleep(2000);
            
            // Kick all players và tắt server
            Maintenance.gI().startImmediately();
            
        } catch (Exception e) {
            Logger.error("Lỗi khi kick all: " + e.getMessage());
            Service.gI().sendThongBao(player, "❌ Lỗi: " + e.getMessage());
        }
    }
    
    // ======================================================
    // CHỨC NĂNG 3: HIỆN MENU EXP
    // ======================================================
    private void showExpMenu(Player player) {
        createOtherMenu(player, 3,
            "⭐ CHỈNH EXP SERVER ⭐\n"
            + "EXP hiện tại: x" + Manager.RATE_EXP_SERVER + "\n"
            + "─────────────────────\n"
            + "Chọn hệ số EXP mới:",
            
            "x1",   // Option 0
            "x2",   // Option 1
            "x5",   // Option 2
            "x10",  // Option 3
            "x20",  // Option 4
            "x30",  // Option 5
            "x40",  // Option 6
            "x50",  // Option 7
            "⬅️ Quay lại"); // Option 8
    }
    
    // ======================================================
    // CHỨC NĂNG 4: XỬ LÝ THAY ĐỔI EXP
    // ======================================================
    private void handleExpChange(Player player, int select) {
        // Map select → EXP rate
        int[] expRates = {1, 2, 5, 10, 20, 30, 40, 50};
        
        if (select == 8) { // Quay lại
            openBaseMenu(player);
            return;
        }
        
        if (select >= 0 && select < expRates.length) {
            int newRate = expRates[select];
            int oldRate = Manager.RATE_EXP_SERVER;
            
            // Thay đổi EXP rate
            Manager.RATE_EXP_SERVER = (byte) newRate;
            
            // Log admin action
            Logger.log(Logger.YELLOW, 
                "[ADMIN ACTION] " + player.name + 
                " đã đổi EXP từ x" + oldRate + " → x" + newRate);
            
            // Thông báo cho admin
            Service.gI().sendThongBao(player, 
                "✅ Đã đổi EXP thành công!\n"
                + "Từ: x" + oldRate + " → x" + newRate);
            
            // Thông báo toàn server
            Service.gI().sendThongBaoAllPlayer(
                "🎉 THÔNG BÁO VÀNG 🎉\n"
                + "EXP server đã thay đổi!\n"
                + "Từ x" + oldRate + " → x" + newRate + "\n"
                + "Chúc các bạn luyện cấp vui vẻ!");
            
            // Quay lại menu chính
            openBaseMenu(player);
        }
    }
    
    // ======================================================
    // HIỆN THÔNG TIN SERVER
    // ======================================================
    private void showServerInfo(Player player) {
        int playerCount = nro.models.server.Client.gI().getPlayers().size();
        int maxPlayer = Manager.MAX_PLAYER;
        
        createOtherMenu(player, 0,
            "📊 THÔNG TIN SERVER 📊\n"
            + "─────────────────────\n"
            + "• Tên: " + Manager.SERVER + "\n"
            + "• Player online: " + playerCount + "/" + maxPlayer + "\n"
            + "• EXP rate: x" + Manager.RATE_EXP_SERVER + "\n"
            + "• Port: " + nro.models.server.ServerManager.PORT + "\n"
            + "• Status: " + (Maintenance.isRunning ? "Đang bảo trì" : "Hoạt động") + "\n"
            + "─────────────────────",
            "OK");
    }
}
