# 📊 PHÂN TÍCH HỆ THỐNG - TẠO ADMIN PANEL

## 🎯 **YÊU CẦU:**

1. ⏰ **Bảo trì 20s** - Countdown mỗi giây
2. 👢 **Đá all player** - Kick tất cả ngay lập tức  
3. ⭐ **Thay đổi EXP** - Nhân x1 đến x50

---

## 🔍 **PHÂN TÍCH HỆ THỐNG HIỆN CÓ:**

### **1. HỆ THỐNG BẢO TRÌ (Maintenance)**

**File:** `src/nro/models/server/Maintenance.java`

```java
public class Maintenance extends Thread {
    public static boolean isRunning = false;
    
    // CHỨC NĂNG SẴN CÓ:
    
    // 1. Bảo trì sau X giây
    public void startSeconds(int seconds) {
        this.timeInSeconds = seconds;
        this.start(); // Chạy thread countdown
    }
    
    // 2. Bảo trì ngay lập tức
    public void startImmediately() {
        ServerManager.gI().close(); // Đóng server ngay
    }
    
    // 3. Countdown loop (chạy trong thread)
    @Override
    public void run() {
        while (timeInSeconds > 0) {
            sendRemainingTime(); // Gửi thông báo
            Thread.sleep(1000);  // Đợi 1 giây
            timeInSeconds--;
        }
        ServerManager.gI().close(); // Hết giờ → đóng server
    }
    
    // 4. Gửi thông báo cho tất cả player
    private void sendRemainingTime() {
        String msg = "Hệ thống sẽ bảo trì sau " + timeInSeconds + " giây";
        Service.gI().sendThongBaoAllPlayer(msg);
    }
}
```

**✅ KẾT LUẬN:**
- Đã có sẵn method `startSeconds(20)` → Dùng luôn!
- Đã có countdown tự động → Không cần code thêm!

---

### **2. HỆ THỐNG KICK PLAYER**

**File:** `src/nro/models/server/Client.java`

```java
public class Client {
    // Danh sách tất cả players online
    private List<Player> players = new ArrayList<>();
    
    // CHỨC NĂNG SẴN CÓ:
    
    // 1. Kick 1 player
    public void kickSession(MySession session) {
        // Đóng session của player
    }
    
    // 2. Kick tất cả players
    public void close() {
        for (Player pl : players) {
            this.kickSession(pl.getSession()); // Kick từng player
        }
    }
}
```

**✅ KẾT LUẬN:**
- Đã có method `close()` kick all → Dùng luôn!
- HOẶC gọi `Maintenance.gI().startImmediately()`

---

### **3. HỆ THỐNG EXP RATE**

**File:** `src/nro/models/server/Manager.java`

```java
public class Manager {
    // Biến global điều chỉnh EXP
    public static byte RATE_EXP_SERVER = 1; // Mặc định x1
    
    // Load từ Config.properties khi start server
    RATE_EXP_SERVER = properties.get("server.expserver");
}
```

**File:** `Config.properties`
```properties
server.expserver=1  # x1 EXP
```

**Cách EXP được tính:**
```java
// Khi player giết mob/boss:
long expGained = baseExp * Manager.RATE_EXP_SERVER;
//                         ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
//                         Nhân với hệ số này!
```

**✅ KẾT LUẬN:**
- Chỉ cần đổi `Manager.RATE_EXP_SERVER = X`
- Không cần restart server!
- Áp dụng NGAY cho tất cả player!

---

## 🎨 **THIẾT KẾ ADMIN PANEL:**

### **Menu NPC:**

```
┌─────────────────────────────────┐
│      🔧 ADMIN PANEL 🔧          │
├─────────────────────────────────┤
│ Xin chào Admin!                 │
│ Chọn chức năng:                 │
│                                 │
│ [Bảo trì 20s]   ← Chức năng 1  │
│ [Đá all player] ← Chức năng 2  │
│ [Thay đổi EXP]  ← Chức năng 3  │
│ [Đóng]                          │
└─────────────────────────────────┘
```

### **Menu EXP:**

```
┌─────────────────────────────────┐
│      ⭐ CHỈNH EXP SERVER ⭐     │
├─────────────────────────────────┤
│ Chọn hệ số EXP:                 │
│                                 │
│ [x1]  [x2]   [x5]   [x10]      │
│ [x20] [x30]  [x40]  [x50]      │
│                                 │
│ [Quay lại]                      │
└─────────────────────────────────┘
```

---

## 🔧 **CÁC CLASS CẦN TẠO/SỬA:**

### **1. NPC Admin Panel** (MỚI)
- File: `src/nro/models/npc_list/AdminPanel.java`
- Hiển thị menu admin
- Xử lý 3 chức năng

### **2. Constant** (SỬA)
- File: `src/nro/models/consts/ConstNpc.java`
- Thêm: `ADMIN_PANEL = 85` (hoặc ID khác)

### **3. NPC Factory** (SỬA)
- File: `src/nro/models/npc/NpcFactory.java`
- Đăng ký NPC mới

### **4. Database** (SỬA)
- Table: `npc_template`
- Thêm NPC admin
- Table: `map_template`
- Đặt NPC ở map nào đó

---

## 📝 **FLOW HOẠT ĐỘNG:**

### **Chức năng 1: Bảo trì 20s**
```
Admin click "Bảo trì 20s"
    ↓
Gọi: Maintenance.gI().startSeconds(20)
    ↓
Countdown tự động:
  - 20 giây: "Server sẽ bảo trì sau 20 giây"
  - 19 giây: "Server sẽ bảo trì sau 19 giây"
  - ...
  - 1 giây: "Server sẽ bảo trì sau 1 giây"
    ↓
Hết giờ → ServerManager.gI().close()
    ↓
Server tắt!
```

### **Chức năng 2: Đá all player**
```
Admin click "Đá all player"
    ↓
Gọi: Maintenance.gI().startImmediately()
    ↓
Kick tất cả player
    ↓
Server tắt ngay!
```

### **Chức năng 3: Thay đổi EXP**
```
Admin click "Thay đổi EXP"
    ↓
Hiện menu: x1, x2, x5, x10, x20, x30, x40, x50
    ↓
Admin chọn x10
    ↓
Set: Manager.RATE_EXP_SERVER = 10
    ↓
Gửi thông báo: "EXP server đã đổi thành x10"
    ↓
Tất cả player giết mob sẽ nhận x10 EXP ngay!
```

---

## ⚠️ **LƯU Ý AN TOÀN:**

### **1. Kiểm tra Admin:**
```java
if (!player.isAdmin()) {
    Service.gI().sendThongBao(player, "Bạn không có quyền!");
    return;
}
```

### **2. Xác nhận trước khi bảo trì:**
```java
case 0: // Bảo trì 20s
    createOtherMenu(player, 1, 
        "⚠️ XÁC NHẬN BẢO TRÌ ⚠️\n"
        + "Server sẽ bảo trì sau 20 giây!\n"
        + "Tất cả player sẽ bị kick!\n"
        + "Bạn có chắc chắn?",
        "Đồng ý", "Hủy");
    break;
```

### **3. Log admin actions:**
```java
Logger.log(Logger.RED, "[ADMIN] " + player.name + " đã bật bảo trì 20s");
```

---

## 📊 **ƯU ĐIỂM THIẾT KẾ NÀY:**

✅ **Dùng code có sẵn** - Không reinvent the wheel  
✅ **An toàn** - Có kiểm tra admin  
✅ **Dễ hiểu** - Code đơn giản, rõ ràng  
✅ **Không cần restart** - EXP đổi ngay lập tức  
✅ **Có xác nhận** - Tránh bấm nhầm  

---

**TIẾP THEO: Tạo code hoàn chỉnh!** 🚀
