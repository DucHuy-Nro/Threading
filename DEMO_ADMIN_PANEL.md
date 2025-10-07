# 🎮 DEMO ADMIN PANEL - CÁCH SỬ DỤNG

## 📺 **DEMO TỪNG CHỨC NĂNG:**

---

## ⏰ **CHỨC NĂNG 1: BẢO TRÌ 20S**

### **Bước 1: Mở Admin Panel**
```
Click vào NPC Admin Panel
→ Menu hiện ra
```

### **Bước 2: Chọn "Bảo trì 20s"**
```
┌─────────────────────────────────┐
│      🔧 ADMIN PANEL 🔧          │
├─────────────────────────────────┤
│ Xin chào Admin blackgoku!       │
│ ─────────────────────           │
│ EXP hiện tại: x1                │
│ Chọn chức năng:                 │
│                                 │
│ [⏰ Bảo trì 20s]     ← CLICK    │
│ [👢 Đá all player]              │
│ [⭐ Thay đổi EXP]                │
│ [Đóng]                          │
└─────────────────────────────────┘
```

### **Bước 3: Xác nhận**
```
┌─────────────────────────────────┐
│    ⚠️ XÁC NHẬN BẢO TRÌ ⚠️      │
├─────────────────────────────────┤
│ Server sẽ bảo trì sau 20 giây!  │
│ ─────────────────────           │
│ • Countdown mỗi giây            │
│ • Tất cả player nhận thông báo  │
│ • Server tắt sau 20s            │
│                                 │
│ ❓ Bạn có chắc chắn?            │
│                                 │
│ [✅ Đồng ý]     ← CLICK         │
│ [❌ Hủy]                         │
└─────────────────────────────────┘
```

### **Bước 4: Countdown bắt đầu**
```
┌─────────────────────────────────┐
│ TẤT CẢ PLAYER NHẬN THÔNG BÁO:   │
├─────────────────────────────────┤
│ Giây 20: "Server sẽ bảo trì sau 20 giây"
│ Giây 19: "Server sẽ bảo trì sau 19 giây"
│ Giây 18: "Server sẽ bảo trì sau 18 giây"
│ ...
│ Giây 3: "Server sẽ bảo trì sau 3 giây"
│ Giây 2: "Server sẽ bảo trì sau 2 giây"
│ Giây 1: "Server sẽ bảo trì sau 1 giây"
│ → SERVER TẮT!
└─────────────────────────────────┘
```

**Console server hiển thị:**
```
[ADMIN ACTION] blackgoku đã bật bảo trì 20 giây!
Hệ thống sẽ bảo trì sau 20 giây
Hệ thống sẽ bảo trì sau 19 giây
...
BẢO TRÌ BẮT ĐẦU
SUCCESSFULLY MAINTENANCE!
```

---

## 👢 **CHỨC NĂNG 2: ĐÁ ALL PLAYER**

### **Bước 1: Chọn "Đá all player"**
```
┌─────────────────────────────────┐
│      🔧 ADMIN PANEL 🔧          │
├─────────────────────────────────┤
│ [⏰ Bảo trì 20s]                │
│ [👢 Đá all player]   ← CLICK   │
│ [⭐ Thay đổi EXP]                │
└─────────────────────────────────┘
```

### **Bước 2: Xác nhận**
```
┌─────────────────────────────────┐
│  ⚠️ XÁC NHẬN ĐÁ ALL PLAYER ⚠️  │
├─────────────────────────────────┤
│ Server sẽ bảo trì NGAY!         │
│ ─────────────────────           │
│ • Tất cả player bị kick ngay    │
│ • Server tắt ngay lập tức       │
│ • KHÔNG CÓ COUNTDOWN!           │
│                                 │
│ ❓ Bạn có chắc chắn?            │
│                                 │
│ [✅ Đồng ý]     ← CLICK         │
│ [❌ Hủy]                         │
└─────────────────────────────────┘
```

### **Bước 3: Kick ngay lập tức**
```
Tất cả player nhận: "⚠️ BẢO TRÌ KHẨN CẤP ⚠️"
    ↓ (2 giây)
→ Tất cả bị kick
→ SERVER TẮT!
```

**Console server:**
```
[ADMIN ACTION] blackgoku đã đá all player!
BẮT ĐẦU BẢO TRÌ NGAY
SUCCESSFULLY MAINTENANCE!
```

---

## ⭐ **CHỨC NĂNG 3: THAY ĐỔI EXP**

### **Bước 1: Chọn "Thay đổi EXP"**
```
┌─────────────────────────────────┐
│      🔧 ADMIN PANEL 🔧          │
├─────────────────────────────────┤
│ [⏰ Bảo trì 20s]                │
│ [👢 Đá all player]              │
│ [⭐ Thay đổi EXP]    ← CLICK    │
└─────────────────────────────────┘
```

### **Bước 2: Chọn hệ số EXP**
```
┌─────────────────────────────────┐
│     ⭐ CHỈNH EXP SERVER ⭐      │
├─────────────────────────────────┤
│ EXP hiện tại: x1                │
│ ─────────────────────           │
│ Chọn hệ số EXP mới:             │
│                                 │
│ [x1]  [x2]  [x5]  [x10]         │
│ [x20] [x30] [x40] [x50]         │
│                                 │
│ [⬅️ Quay lại]                   │
└─────────────────────────────────┘

VD: Click [x10]
```

### **Bước 3: Áp dụng ngay!**
```
Admin nhận thông báo:
┌─────────────────────────────────┐
│ ✅ Đã đổi EXP thành công!       │
│ Từ: x1 → x10                    │
└─────────────────────────────────┘

Tất cả player nhận:
┌─────────────────────────────────┐
│      🎉 THÔNG BÁO VÀNG 🎉       │
│ ─────────────────────           │
│ EXP server đã thay đổi!         │
│ Từ x1 → x10                     │
│ Chúc các bạn luyện cấp vui vẻ!  │
└─────────────────────────────────┘
```

### **Bước 4: Test ngay!**
```
VÍ DỤ:
- Trước: Giết mob → Nhận 100 EXP
- Sau khi đổi x10: Giết mob → Nhận 1000 EXP ✅

KHÔNG CẦN RESTART SERVER!
Áp dụng NGAY cho tất cả player!
```

**Console server:**
```
[ADMIN ACTION] blackgoku đã đổi EXP từ x1 → x10
```

---

## 📊 **CHỨC NĂNG 4: THÔNG TIN SERVER**

### **Chọn "Thông tin server"**
```
┌─────────────────────────────────┐
│    📊 THÔNG TIN SERVER 📊       │
├─────────────────────────────────┤
│ • Tên: 1                        │
│ • Player online: 15/1000        │
│ • EXP rate: x10                 │
│ • Port: 14445                   │
│ • Status: Hoạt động             │
│ ─────────────────────           │
│                                 │
│ [OK]                            │
└─────────────────────────────────┘
```

---

## 🎯 **USE CASES THỰC TẾ:**

### **Use case 1: Event x2 EXP cuối tuần**
```
Thứ 6 20h:
1. Mở Admin Panel
2. Chọn "Thay đổi EXP"
3. Chọn "x2"
4. Thông báo: "🎉 CUỐI TUẦN X2 EXP!"

Chủ nhật 23h:
1. Chọn "Thay đổi EXP"
2. Chọn "x1"
3. Thông báo: "Event x2 EXP đã kết thúc!"
```

### **Use case 2: Bảo trì khẩn cấp**
```
Phát hiện bug nghiêm trọng:
1. Mở Admin Panel
2. Chọn "Đá all player"
3. Xác nhận
4. Server tắt ngay
5. Fix bug
6. Restart server
```

### **Use case 3: Bảo trì có lịch**
```
Thông báo trước cho player:
1. Chat global: "Server sẽ bảo trì lúc 23h"
2. 22h55: Mở Admin Panel
3. Chọn "Bảo trì 20s"
4. Player có 20s để chuẩn bị
5. Server tắt đúng giờ
```

---

## 📸 **SCREENSHOT MẪU (Text version):**

### **Player bình thường click NPC:**
```
╔═════════════════════════════════╗
║  ⛔ TRUY CẬP BỊ TỪ CHỐI ⛔      ║
╠═════════════════════════════════╣
║ Bạn không có quyền sử dụng      ║
║ panel này!                      ║
║                                 ║
║          [Đóng]                 ║
╚═════════════════════════════════╝
```

### **Admin click NPC:**
```
╔═════════════════════════════════╗
║      🔧 ADMIN PANEL 🔧          ║
╠═════════════════════════════════╣
║ Xin chào Admin blackgoku!       ║
║ ─────────────────────           ║
║ EXP hiện tại: x10               ║
║ Chọn chức năng:                 ║
║                                 ║
║    [⏰ Bảo trì 20s]              ║
║    [👢 Đá all player]            ║
║    [⭐ Thay đổi EXP]             ║
║    [📊 Thông tin server]         ║
║    [Đóng]                       ║
╚═════════════════════════════════╝
```

---

## 🔒 **BẢO MẬT:**

### **Các lớp bảo vệ:**

1. ✅ **Kiểm tra isAdmin()** - Chỉ admin mới dùng được
2. ✅ **Xác nhận 2 lần** - Tránh bấm nhầm
3. ✅ **Log actions** - Theo dõi ai làm gì
4. ✅ **Thông báo rõ ràng** - Player biết trước

### **Log file sẽ ghi:**
```
[2025-10-06 14:30:00] [ADMIN ACTION] blackgoku đã bật bảo trì 20 giây!
[2025-10-06 14:32:15] [ADMIN ACTION] blackgoku đã đổi EXP từ x1 → x10
[2025-10-06 15:00:00] [ADMIN ACTION] blackgoku đã đá all player!
```

---

## 💡 **TIPS & TRICKS:**

### **1. Thêm nhiều tùy chọn EXP:**

Sửa file `AdminPanel.java`, dòng ~160:

```java
private void showExpMenu(Player player) {
    createOtherMenu(player, 3,
        "⭐ CHỈNH EXP SERVER ⭐\n"
        + "EXP hiện tại: x" + Manager.RATE_EXP_SERVER,
        
        "x1", "x2", "x3", "x4", "x5",     // 0-4
        "x10", "x15", "x20", "x25",       // 5-8
        "x30", "x50", "x100",             // 9-11
        "⬅️ Quay lại");                   // 12
}

// Và sửa array:
int[] expRates = {1, 2, 3, 4, 5, 10, 15, 20, 25, 30, 50, 100};
```

### **2. Thêm countdown tùy chỉnh:**

```java
case 0: // Bảo trì
    createOtherMenu(player, 5,
        "Chọn thời gian bảo trì:",
        "10 giây",   // select 0 → 10s
        "20 giây",   // select 1 → 20s
        "60 giây",   // select 2 → 60s
        "5 phút",    // select 3 → 300s
        "Hủy");

// Xử lý:
case 5:
    int[] times = {10, 20, 60, 300};
    if (select < times.length) {
        Maintenance.gI().startSeconds(times[select]);
    }
    break;
```

### **3. Thêm chức năng restart server:**

```java
case 4: // Restart server
    createOtherMenu(player, 6,
        "⚠️ RESTART SERVER ⚠️\n"
        + "Server sẽ restart sau 30 giây!",
        "Đồng ý", "Hủy");
    break;

// Xử lý:
case 6:
    if (select == 0) {
        Service.gI().sendThongBaoAllPlayer(
            "Server sẽ restart sau 30 giây!");
        Maintenance.gI().startSeconds(30);
    }
    break;
```

---

## 🎓 **HIỂU CÁCH HOẠT ĐỘNG:**

### **1. Bảo trì hoạt động như nào?**

```java
Maintenance.gI().startSeconds(20)
    ↓
Thread mới chạy (không block main thread)
    ↓
Loop 20 lần:
  for (i = 20; i > 0; i--) {
      sendThongBaoAllPlayer("Còn " + i + " giây");
      sleep(1000); // Đợi 1 giây
  }
    ↓
ServerManager.gI().close()
  → Client.gI().close() // Kick all
  → Save data
  → System.exit(0) // Tắt server
```

### **2. EXP rate hoạt động như nào?**

```java
// Khi player giết mob:
NPoint.java hoặc PlayerService.java:

long baseExp = mob.getExp(); // VD: 100 EXP
long finalExp = baseExp * Manager.RATE_EXP_SERVER;
//                        ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
//                        Nếu = 10 → 100 * 10 = 1000 EXP

player.nPoint.addExp(finalExp); // Cộng EXP
```

**Thay đổi `RATE_EXP_SERVER` → Áp dụng NGAY cho tất cả!**

### **3. Kick all hoạt động như nào?**

```java
Maintenance.gI().startImmediately()
    ↓
ServerManager.gI().close()
    ↓
Client.gI().close()
    ↓
for (Player p : allPlayers) {
    kickSession(p.session); // Kick từng player
    saveData(p);            // Lưu data
}
    ↓
System.exit(0) // Tắt JVM
```

---

## 📋 **CHECKLIST CÀI ĐẶT:**

- [ ] Tạo file `AdminPanel.java` ✅ (Đã có)
- [ ] Thêm constant trong `ConstNpc.java`
- [ ] Import trong `NpcFactory.java`
- [ ] Thêm case trong `NpcFactory.java`
- [ ] Thêm NPC vào `npc_template` (SQL)
- [ ] Thêm NPC vào `map_template` (SQL)
- [ ] Set admin cho account (SQL)
- [ ] Build project
- [ ] Restart server
- [ ] Test 3 chức năng

---

## ✅ **KẾT QUẢ MONG ĐỢI:**

Sau khi cài xong:

✅ NPC hiển thị ở map đã chọn  
✅ Admin click → Thấy menu đầy đủ  
✅ Player thường → "Không có quyền"  
✅ Bảo trì 20s → Countdown mỗi giây  
✅ Đá all → Kick ngay lập tức  
✅ Đổi EXP → Áp dụng real-time  
✅ Log admin actions đầy đủ  

---

**CHÚC BẠN THÀNH CÔNG!** 🎉🔧
