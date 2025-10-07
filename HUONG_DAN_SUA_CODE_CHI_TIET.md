# 📝 HƯỚNG DẪN SỬA CODE CHI TIẾT (TỪNG BƯỚC)

## 🎯 **BẠN SẼ LÀM GÌ:**

Sửa **1 FILE DUY NHẤT:** `ServerManager.java`

---

## 📂 **BƯỚC 1: MỞ FILE**

1. Mở **NetBeans** (hoặc editor code của bạn)
2. Vào: `src` → `nro` → `models` → `server` → **`ServerManager.java`**
3. File sẽ mở ra

**HOẶC** mở bằng Notepad++:
- Right-click file `ServerManager.java`
- Open With → Notepad++

---

## ✂️ **BƯỚC 2: TÌM VÀ SỬA METHOD `activeCommandLine()`**

### **2a. TÌM METHOD:**

Nhấn **Ctrl + F** (Find)

Tìm text: `private static void activeCommandLine()`

Bạn sẽ thấy đoạn code này (khoảng **dòng 293-324**):

```java
private static void activeCommandLine() {
    Scanner sc = new Scanner(System.in);
    while (true) {
        String line = sc.nextLine();
        switch (line) {
            case "bt":
                Maintenance.gI().startSeconds(5);
                break;
            case "bat":
                AutoMaintenance.AutoMaintenance = true;
                System.out.println("Đã bật chế độ bảo trì tự động.");
                break;
            case "tat":
                AutoMaintenance.AutoMaintenance = false;
                System.out.println("Đã tắt chế độ bảo trì tự động.");
                break;
            case "run":
            try {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "run.bat");
                pb.inheritIO();
                pb.start();
                System.out.println("Đã chạy run.bat");
            } catch (IOException e) {
                System.out.println("Lỗi khi chạy run.bat: " + e.getMessage());
            }
            break;
            default:
                System.out.println("Lệnh không hợp lệ.");
                break;
        }
    }
}
```

---

### **2b. XÓA TOÀN BỘ METHOD CŨ:**

**Chọn (bôi đen) từ dòng:**
```java
private static void activeCommandLine() {
```

**Đến dòng:**
```java
    }  // ← Dấu ngoặc đóng cuối cùng của method này
```

**Nhấn Delete** để xóa hết!

---

### **2c. DÁN CODE MỚI VÀO:**

**Copy TOÀN BỘ đoạn code dưới đây** và paste vào chỗ vừa xóa:

```java
    private static void activeCommandLine() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            showAdminMenu();
            String line = sc.nextLine().trim();
            
            switch (line) {
                case "1": // Bảo trì 20s
                    startMaintenance20s(sc);
                    break;
                    
                case "2": // Đá all player
                    kickAllPlayers(sc);
                    break;
                    
                case "3": // Thay đổi EXP
                    changeExpRate(sc);
                    break;
                    
                case "4": // Thông tin server
                    showServerInfo(sc);
                    break;
                    
                case "5": // Bảo trì ngay (lệnh cũ)
                    Maintenance.gI().startSeconds(5);
                    break;
                    
                case "0": // Thoát
                    System.out.println("\n[!] Đang thoát...");
                    System.exit(0);
                    break;
                    
                // Lệnh text cũ (backward compatible)
                case "bt":
                    Maintenance.gI().startSeconds(5);
                    break;
                case "bat":
                    AutoMaintenance.AutoMaintenance = true;
                    System.out.println("Đã bật chế độ bảo trì tự động.");
                    break;
                case "tat":
                    AutoMaintenance.AutoMaintenance = false;
                    System.out.println("Đã tắt chế độ bảo trì tự động.");
                    break;
                case "run":
                    try {
                        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "run.bat");
                        pb.inheritIO();
                        pb.start();
                        System.out.println("Đã chạy run.bat");
                    } catch (IOException e) {
                        System.out.println("Lỗi khi chạy run.bat: " + e.getMessage());
                    }
                    break;
                    
                case "menu":
                case "help":
                case "":
                    // Hiện lại menu
                    break;
                    
                default:
                    System.out.println("[!] Lệnh không hợp lệ! Nhập 'menu' để xem lại.");
                    break;
            }
        }
    }
```

---

## ➕ **BƯỚC 3: THÊM CÁC METHOD MỚI**

**Kéo xuống cuối file**, tìm dòng:

```java
}  // ← Dấu ngoặc đóng cuối cùng của class ServerManager
```

**TRƯỚC dấu ngoặc đóng đó**, thêm **TOÀN BỘ** code sau:

```java
    // ========================================================
    // HIỂN THỊ MENU ADMIN
    // ========================================================
    private static void showAdminMenu() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║         🔧 ADMIN PANEL - NRO SERVER 🔧        ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║  Server: " + NAME + "                         ");
        System.out.println("║  EXP Rate: x" + Manager.RATE_EXP_SERVER + "                                  ");
        System.out.println("║  Players Online: " + Client.gI().getPlayers().size() + "/" + Manager.MAX_PLAYER + "                        ");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║  [1] ⏰ Bảo trì 20s (Countdown)               ║");
        System.out.println("║  [2] 👢 Đá all player (Ngay lập tức)          ║");
        System.out.println("║  [3] ⭐ Thay đổi EXP Server                    ║");
        System.out.println("║  [4] 📊 Thông tin Server                       ║");
        System.out.println("║  [5] ⚡ Bảo trì ngay (5s countdown)            ║");
        System.out.println("║  [0] ❌ Thoát                                  ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.print("👉 Nhập lựa chọn: ");
    }
    
    // ========================================================
    // CHỨC NĂNG 1: BẢO TRÌ 20S
    // ========================================================
    private static void startMaintenance20s(Scanner sc) {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│  ⚠️  XÁC NHẬN BẢO TRÌ 20 GIÂY  ⚠️   │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ • Server sẽ countdown từ 20→1 giây  │");
        System.out.println("│ • Thông báo gửi cho tất cả player   │");
        System.out.println("│ • Server tự động tắt sau 20s        │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("❓ Bạn có chắc chắn? (y/n): ");
        
        String confirm = sc.nextLine().trim().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            System.out.println("\n✅ Đã kích hoạt bảo trì 20 giây!");
            System.out.println("⏰ Countdown bắt đầu...\n");
            Maintenance.gI().startSeconds(20);
        } else {
            System.out.println("\n❌ Đã hủy bảo trì!");
        }
    }
    
    // ========================================================
    // CHỨC NĂNG 2: ĐÁ ALL PLAYER
    // ========================================================
    private static void kickAllPlayers(Scanner sc) {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│  ⚠️  XÁC NHẬN ĐÁ ALL PLAYER  ⚠️     │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ • Tất cả player bị kick NGAY        │");
        System.out.println("│ • Server tắt NGAY LẬP TỨC           │");
        System.out.println("│ • KHÔNG CÓ COUNTDOWN!               │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("❓ Bạn có chắc chắn? (y/n): ");
        
        String confirm = sc.nextLine().trim().toLowerCase();
        if (confirm.equals("y") || confirm.equals("yes")) {
            System.out.println("\n✅ Đang kick all players...");
            System.out.println("⚡ Server sẽ tắt ngay!");
            Maintenance.gI().startImmediately();
        } else {
            System.out.println("\n❌ Đã hủy!");
        }
    }
    
    // ========================================================
    // CHỨC NĂNG 3: THAY ĐỔI EXP
    // ========================================================
    private static void changeExpRate(Scanner sc) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     ⭐ THAY ĐỔI EXP SERVER ⭐          ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  EXP hiện tại: x" + Manager.RATE_EXP_SERVER + "                       ");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  Chọn hệ số EXP mới:                   ║");
        System.out.println("║                                        ║");
        System.out.println("║  [1] x1    [2] x2    [3] x5            ║");
        System.out.println("║  [4] x10   [5] x20   [6] x30           ║");
        System.out.println("║  [7] x40   [8] x50                     ║");
        System.out.println("║                                        ║");
        System.out.println("║  [0] Quay lại                          ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("👉 Nhập lựa chọn: ");
        
        String choice = sc.nextLine().trim();
        int[] expRates = {0, 1, 2, 5, 10, 20, 30, 40, 50};
        
        try {
            int index = Integer.parseInt(choice);
            if (index == 0) {
                System.out.println("\n❌ Đã hủy!");
                return;
            }
            
            if (index >= 1 && index <= 8) {
                int oldRate = Manager.RATE_EXP_SERVER;
                int newRate = expRates[index];
                
                Manager.RATE_EXP_SERVER = (byte) newRate;
                
                System.out.println("\n✅ Đã thay đổi EXP thành công!");
                System.out.println("📊 Từ: x" + oldRate + " → x" + newRate);
                System.out.println("⚡ Áp dụng NGAY cho tất cả player!");
                
                // Gửi thông báo cho tất cả player
                nro.models.services.Service.gI().sendThongBaoAllPlayer(
                    "🎉 THÔNG BÁO 🎉\n"
                    + "EXP server đã thay đổi!\n"
                    + "Từ x" + oldRate + " → x" + newRate + "\n"
                    + "Chúc các bạn luyện cấp vui vẻ!");
                
                Logger.log(Logger.YELLOW, 
                    "[ADMIN] Đã đổi EXP từ x" + oldRate + " → x" + newRate);
            } else {
                System.out.println("\n❌ Lựa chọn không hợp lệ!");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Vui lòng nhập số!");
        }
    }
    
    // ========================================================
    // CHỨC NĂNG 4: THÔNG TIN SERVER
    // ========================================================
    private static void showServerInfo(Scanner sc) {
        int playerCount = Client.gI().getPlayers().size();
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       📊 THÔNG TIN SERVER 📊           ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  Tên Server: " + NAME + "              ");
        System.out.println("║  IP: " + IP + "                        ");
        System.out.println("║  Port: " + PORT + "                         ");
        System.out.println("║  ─────────────────────────────────     ║");
        System.out.println("║  Players Online: " + playerCount + "/" + Manager.MAX_PLAYER + "              ");
        System.out.println("║  Max/IP: " + Manager.MAX_PER_IP + "                         ");
        System.out.println("║  EXP Rate: x" + Manager.RATE_EXP_SERVER + "                         ");
        System.out.println("║  ─────────────────────────────────     ║");
        System.out.println("║  Status: " + (Maintenance.isRunning ? "Đang bảo trì" : "Hoạt động") + "       ");
        System.out.println("║  Uptime: " + timeStart + "             ");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n📌 Nhấn Enter để quay lại menu...");
        sc.nextLine();
    }
```

---

## 💾 **BƯỚC 3: LƯU FILE**

Nhấn **Ctrl + S** để lưu file!

**Kiểm tra:**
- File đã lưu (không còn dấu `*` ở tên file)
- Không có lỗi đỏ trong code

---

## 🔨 **BƯỚC 4: BUILD PROJECT**

### **Cách 1: Dùng NetBeans (DỄ NHẤT)**

1. Right-click vào **project name** (trong Projects panel)
2. Chọn **Clean and Build**
3. Đợi build xong (thấy "BUILD SUCCESSFUL")

---

### **Cách 2: Dùng CMD**

```bash
# Mở CMD
# Vào thư mục project
cd "E:\Source NRO by me\Threading"

# Tắt server (nếu đang chạy)
taskkill /F /IM java.exe

# Build
ant clean
ant jar
```

**Nếu thành công:**
```
BUILD SUCCESSFUL
Total time: 10 seconds
```

---

## ▶️ **BƯỚC 5: CHẠY SERVER**

```bash
run.bat
```

**Console sẽ hiện:**

```
[Loading logs...]
✓ Active Port 14445

╔════════════════════════════════════════════════╗
║         🔧 ADMIN PANEL - NRO SERVER 🔧        ║
╠════════════════════════════════════════════════╣
║  Server: Ngọc Rồng Online                      ║
║  EXP Rate: x1                                  ║
║  Players Online: 0/1000                        ║
╠════════════════════════════════════════════════╣
║  [1] ⏰ Bảo trì 20s (Countdown)               ║
║  [2] 👢 Đá all player (Ngay lập tức)          ║
║  [3] ⭐ Thay đổi EXP Server                    ║
║  [4] 📊 Thông tin Server                       ║
║  [5] ⚡ Bảo trì ngay (5s countdown)            ║
║  [0] ❌ Thoát                                  ║
╚════════════════════════════════════════════════╝
👉 Nhập lựa chọn: _
```

**→ THÀNH CÔNG!** 🎉

Bây giờ bạn có thể:
- Nhập `1` → Bảo trì 20s
- Nhập `2` → Kick all
- Nhập `3` → Đổi EXP

---

## 🎬 **DEMO SỬ DỤNG:**

### **Test 1: Đổi EXP lên x50**

```
Console đang chờ nhập
    ↓
Gõ: 3
Nhấn: Enter
    ↓
Menu EXP hiện
    ↓
Gõ: 8 (x50)
Nhấn: Enter
    ↓
✅ EXP đã đổi thành x50!
Tất cả player nhận thông báo!
```

---

## 🆘 **NẾU GẶP LỖI:**

### **Lỗi build: "cannot find symbol"**

**Có thể thiếu import!**

Kiểm tra đầu file `ServerManager.java` có đủ imports này không:

```java
import java.util.Scanner;
import nro.models.services.Service;
import nro.models.utils.Logger;
```

Nếu thiếu, thêm vào!

---

### **Lỗi: Menu không hiện**

**Nguyên nhân:** Build chưa thành công

**Fix:**
1. Xóa folder `build` và `dist`
2. Build lại: `ant clean && ant jar`
3. Chạy `run.bat`

---

### **Lỗi: Nhập số không hoạt động**

**Nguyên nhân:** Code paste sai vị trí

**Fix:**
- Đảm bảo paste code ở **ĐÚNG VỊ TRÍ**
- Đảm bảo **DẤU NGOẶC NHỌN `{}`** đúng
- Build lại

---

## 📋 **CHECKLIST:**

### **Code:**
- [ ] Mở file `ServerManager.java`
- [ ] Tìm method `activeCommandLine()`
- [ ] Xóa method cũ
- [ ] Paste code mới vào
- [ ] Thêm 4 method mới vào cuối file
- [ ] Lưu file (Ctrl+S)

### **Build:**
- [ ] Tắt server
- [ ] Clean and Build (NetBeans)
- [ ] Thấy "BUILD SUCCESSFUL"

### **Run:**
- [ ] Chạy `run.bat`
- [ ] Thấy menu console hiện
- [ ] Test nhập số

---

## 🎉 **HOÀN THÀNH!**

Chỉ cần:
1. **Copy-paste code** theo hướng dẫn trên
2. **Build** lại
3. **Chạy** server
4. **Enjoy** menu console đẹp!

**Dễ dàng, không cần hiểu code!** 😊

---

**FILE TIẾP THEO: Xem `CODE_FULL_SERVERMANAGER.txt` để có TOÀN BỘ CODE HOÀN CHỈNH!**
