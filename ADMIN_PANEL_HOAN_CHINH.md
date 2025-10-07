# 🎮 ADMIN PANEL - HƯỚNG DẪN HOÀN CHỈNH

## 🎯 **CHỨC NĂNG:**

✅ **Bảo trì 20s** - Countdown mỗi giây, gửi thông báo cho tất cả  
✅ **Đá all player** - Kick tất cả ngay lập tức  
✅ **Thay đổi EXP** - x1, x2, x5, x10, x20, x30, x40, x50  
✅ **Thông tin server** - Xem player online, EXP rate...  
✅ **Bảo mật** - Chỉ admin mới dùng được  
✅ **Xác nhận** - Tránh bấm nhầm  

---

## 📋 **CÀI ĐẶT (5 BƯỚC):**

---

### **BƯỚC 1️⃣: SỬA FILE ConstNpc.java**

**📁 File:** `src/nro/models/consts/ConstNpc.java`  
**📍 Dòng:** ~156-157

**TÌM:**
```java
    public static final byte XE_NUOC_MIA = 84;
    //----------------------index menu------------------------------------------
```

**SỬA THÀNH:**
```java
    public static final byte XE_NUOC_MIA = 84;
    public static final byte ADMIN_PANEL = 85;  // ← THÊM DÒNG NÀY
    //----------------------index menu------------------------------------------
```

---

### **BƯỚC 2️⃣: SỬA FILE NpcFactory.java**

**📁 File:** `src/nro/models/npc/NpcFactory.java`

**2a. THÊM IMPORT (dòng ~95):**

**TÌM:**
```java
import nro.models.npc_list.Berry;
import nro.models.npc_list.ChiChi;
```

**THÊM SAU:**
```java
import nro.models.npc_list.Berry;
import nro.models.npc_list.ChiChi;
import nro.models.npc_list.AdminPanel;  // ← THÊM DÒNG NÀY
```

**2b. THÊM CASE (dòng ~237-240):**

**TÌM:**
```java
                case ConstNpc.BERRY ->
                    new Berry(mapId, status, cx, cy, tempId, avatar);
                default ->
```

**SỬA THÀNH:**
```java
                case ConstNpc.BERRY ->
                    new Berry(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.ADMIN_PANEL ->                              // ← THÊM
                    new AdminPanel(mapId, status, cx, cy, tempId, avatar); // ← DÒNG NÀY
                default ->
```

---

### **BƯỚC 3️⃣: THÊM VÀO DATABASE**

**Mở Navicat:**

**3a. Table `npc_template` - Insert:**

Click **Insert** → Nhập:
```
id:     85
NAME:   Admin Panel
head:   18
body:   19
leg:    20
avatar: 349
```

**HOẶC chạy SQL:**
```sql
INSERT INTO `npc_template` VALUES (85, 'Admin Panel', 18, 19, 20, 349);
```

**3b. Table `map_template` - Thêm NPC vào map:**

1. Tìm **Map ID 5** (Đảo Kamê)
2. Cột **`data`** (cột thứ 11)
3. Double-click để sửa
4. Thêm **`[85,500,300]`** vào cuối

**VD:**
```
TRƯỚC: [[39,984,408],[13,1068,408],[21,1205,408]]

SAU:   [[39,984,408],[13,1068,408],[21,1205,408],[85,500,300]]
```

**⚠️ CHÚ Ý:** Phải có dấu phẩy `,` giữa các phần tử!

---

### **BƯỚC 4️⃣: SET ADMIN CHO ACCOUNT**

**Table `account`:**

```sql
-- Xem account của bạn
SELECT id, username, is_admin FROM account;

-- Set làm admin (thay 'admin' bằng username của bạn)
UPDATE account 
SET is_admin = 1 
WHERE username = 'admin';

-- Kiểm tra
SELECT username, is_admin FROM account WHERE is_admin = 1;
```

---

### **BƯỚC 5️⃣: BUILD & RUN**

```bash
# 1. TẮT SERVER (nếu đang chạy)
# Ctrl+C trong CMD
# HOẶC:
taskkill /F /IM java.exe

# 2. BUILD
ant clean
ant jar

# 3. RUN
run.bat
```

**Nếu Build lỗi:**
- Kiểm tra lại 3 files đã sửa đúng chưa
- Xem phần [TROUBLESHOOTING] bên dưới

---

## 🎮 **CÁCH SỬ DỤNG:**

### **1️⃣ Vào Admin Panel:**

```
1. Login game (với account is_admin = 1)
2. Đi đến Map ID 5 (Đảo Kamê)
3. Tìm NPC Admin Panel tại tọa độ (500, 300)
4. Click vào NPC
```

**Nếu là Admin → Thấy:**
```
🔧 ADMIN PANEL 🔧
Xin chào Admin [tên]!
─────────────────────
EXP hiện tại: x1
Chọn chức năng:

[⏰ Bảo trì 20s]
[👢 Đá all player]
[⭐ Thay đổi EXP]
[📊 Thông tin server]
[Đóng]
```

---

### **2️⃣ Bảo trì 20s:**

```
Click "⏰ Bảo trì 20s"
    ↓
XÁC NHẬN BẢO TRÌ
Server sẽ bảo trì sau 20 giây!
• Countdown sẽ hiện mỗi giây
• Tất cả player sẽ nhận thông báo
• Server sẽ tự động tắt sau 20s

❓ Bạn có chắc chắn?
[✅ Đồng ý] [❌ Hủy]
    ↓
Click "✅ Đồng ý"
    ↓
COUNTDOWN BẮT ĐẦU:
  - Giây 20: "Server sẽ bảo trì sau 20 giây..."
  - Giây 19: "Server sẽ bảo trì sau 19 giây..."
  - ...
  - Giây 1:  "Server sẽ bảo trì sau 1 giây..."
  - Giây 0:  Server tắt!
```

**Tất cả player nhận thông báo ĐỒNG THỜI mỗi giây!**

---

### **3️⃣ Đá all player:**

```
Click "👢 Đá all player"
    ↓
XÁC NHẬN ĐÁ ALL PLAYER
Server sẽ bảo trì NGAY LẬP TỨC!
• Tất cả player bị kick ngay
• Server tắt ngay lập tức
• KHÔNG CÓ COUNTDOWN!

❓ Bạn có chắc chắn?
[✅ Đồng ý] [❌ Hủy]
    ↓
Click "✅ Đồng ý"
    ↓
NGAY LẬP TỨC:
  - Gửi thông báo: "BẢO TRÌ KHẨN CẤP"
  - Đợi 2 giây
  - Kick tất cả player
  - Server tắt!
```

**KHÔNG có countdown - Tắt NGAY!**

---

### **4️⃣ Thay đổi EXP:**

```
Click "⭐ Thay đổi EXP"
    ↓
MENU CHỌN EXP:
⭐ CHỈNH EXP SERVER ⭐
EXP hiện tại: x1
─────────────────────
Chọn hệ số EXP mới:

[x1]  [x2]  [x5]  [x10]
[x20] [x30] [x40] [x50]
[⬅️ Quay lại]
    ↓
VD: Click "x10"
    ↓
✅ EXP đã đổi từ x1 → x10!
    ↓
Thông báo toàn server:
"🎉 THÔNG BÁO VÀNG 🎉
EXP server đã thay đổi!
Từ x1 → x10
Chúc các bạn luyện cấp vui vẻ!"
    ↓
Tất cả player giết mob ngay lúc đó
sẽ nhận x10 EXP!
```

**KHÔNG CẦN RESTART SERVER!** ⚡

---

### **5️⃣ Xem thông tin server:**

```
Click "📊 Thông tin server"
    ↓
📊 THÔNG TIN SERVER 📊
─────────────────────
• Tên: 1
• Player online: 15/1000
• EXP rate: x10
• Port: 14445
• Status: Hoạt động
─────────────────────

[OK]
```

---

## 🛡️ **BẢO MẬT:**

### **1. Kiểm tra Admin tự động:**
```java
if (!player.isAdmin()) {
    // Hiện: "⛔ TRUY CẬP BỊ TỪ CHỐI ⛔"
    return;
}
```

### **2. Xác nhận trước khi thực hiện:**
- Bảo trì → Xác nhận 1 lần
- Kick all → Xác nhận 1 lần
- Tránh bấm nhầm!

### **3. Log tất cả hành động:**
```java
Logger.log(Logger.RED, "[ADMIN ACTION] Admin_Name đã ...");
```

**→ Có thể track admin làm gì!**

---

## 📊 **CẤU TRÚC FILE:**

```
Threading/
├── src/nro/models/
│   ├── consts/
│   │   └── ConstNpc.java            ← SỬA: Thêm ADMIN_PANEL = 85
│   ├── npc/
│   │   └── NpcFactory.java          ← SỬA: Import + case
│   └── npc_list/
│       └── AdminPanel.java          ← MỚI: Code admin panel
└── sql/
    └── (Thêm vào database qua Navicat)
```

---

## ⚡ **TÓM TẮT NHANH:**

### **Cần sửa:**
1. ✅ `ConstNpc.java` - 1 dòng
2. ✅ `NpcFactory.java` - 1 import + 2 dòng
3. ✅ `AdminPanel.java` - Đã tạo sẵn (copy vào)
4. ✅ Database - 2 bảng (Navicat)
5. ✅ Set admin - 1 câu SQL

### **Build & Run:**
```bash
taskkill /F /IM java.exe
ant clean && ant jar
run.bat
```

### **Test:**
- Vào map 5
- Click NPC Admin Panel
- Test 3 chức năng

---

## 🎉 **KẾT QUẢ:**

Admin Panel hoạt động đầy đủ với:
- ⏰ Countdown bảo trì 20s ✅
- 👢 Kick all ngay lập tức ✅
- ⭐ Đổi EXP x1-x50 real-time ✅
- 🛡️ Chỉ admin dùng được ✅
- 📝 Log tất cả actions ✅

---

## 📁 **FILES ĐÃ TẠO:**

| File | Mô tả |
|------|-------|
| ✅ `AdminPanel.java` | **CODE CHÍNH** - Copy vào npc_list/ |
| ✅ `HUONG_DAN_CAI_DAT_ADMIN_PANEL.md` | Hướng dẫn từng bước |
| ✅ `CAI_DAT_ADMIN_PANEL.sql` | SQL commands |
| ✅ `CODE_SNIPPET_ADMIN_PANEL.md` | Code snippets |
| ✅ `PHAN_TICH_ADMIN_PANEL.md` | Phân tích hệ thống |
| ✅ `ADMIN_PANEL_HOAN_CHINH.md` | **ĐỌC FILE NÀY** |

---

## 🚀 **BẮT ĐẦU NGAY:**

### **3 PHÚT ĐỂ HOÀN THÀNH:**

1. **Mở 2 files code** (1 phút):
   - `ConstNpc.java` → Thêm 1 dòng
   - `NpcFactory.java` → Thêm 1 import + 2 dòng

2. **Mở Navicat** (1 phút):
   - Thêm vào `npc_template`
   - Sửa `map_template`
   - Set `is_admin = 1`

3. **Build & Test** (1 phút):
   - `ant clean && ant jar`
   - `run.bat`
   - Vào game test

**→ XONG! Có Admin Panel rồi!** 🎉

---

## 💡 **DEMO VIDEO (Text):**

```
=== TEST BẢO TRÌ 20S ===

Admin vào game
  ↓
Đi map 5, click NPC "Admin Panel"
  ↓
Menu hiện: [⏰ Bảo trì 20s] [👢 Đá all] [⭐ EXP] [Đóng]
  ↓
Click "⏰ Bảo trì 20s"
  ↓
Xác nhận: "Server sẽ bảo trì sau 20 giây!"
  ↓
Click "✅ Đồng ý"
  ↓
TẤT CẢ PLAYER THẤY (mỗi giây):
  "Hệ thống sẽ bảo trì sau 20 giây..."
  "Hệ thống sẽ bảo trì sau 19 giây..."
  ...
  "Hệ thống sẽ bảo trì sau 1 giây..."
  ↓
Server tắt tự động!

=== TEST THAY ĐỔI EXP ===

Click "⭐ Thay đổi EXP"
  ↓
Menu: [x1] [x2] [x5] [x10] [x20] [x30] [x40] [x50]
  ↓
Click "x10"
  ↓
Thông báo: "EXP đã đổi từ x1 → x10"
  ↓
Player A giết mob:
  - Mob cho 100 EXP base
  - Nhận được: 100 × 10 = 1000 EXP ✅
  ↓
NGAY LẬP TỨC, không cần restart!
```

---

## ❓ **FAQ:**

**Q: NPC không hiện trong game?**
- Kiểm tra database có NPC ID 85 chưa
- Kiểm tra map_template có `[85,500,300]` chưa
- Restart server chưa?

**Q: Click NPC báo "Không có quyền"?**
- Check: `SELECT is_admin FROM account WHERE username = 'your_name'`
- Phải = 1 mới là admin
- Logout và login lại sau khi sửa!

**Q: Bảo trì không hoạt động?**
- Xem log server có lỗi gì
- Check `Maintenance.isRunning` có đang `true` không

**Q: EXP không đổi?**
- Kiểm tra code tính EXP có dùng `Manager.RATE_EXP_SERVER` không
- Thử kill mob xem EXP nhận được

**Q: Có thể thêm chức năng khác không?**
- Có! Sửa file `AdminPanel.java`
- Thêm option mới vào menu
- Thêm case xử lý

---

## 🎨 **TÙY CHỈNH:**

### **Thay đổi tọa độ NPC:**

```sql
-- Sửa [85,500,300] thành [85,x,y]
-- VD: [85,1000,400] → NPC ở (1000, 400)
```

### **Đặt NPC ở map khác:**

```sql
-- Xem tất cả map
SELECT id, NAME FROM map_template ORDER BY id;

-- Chọn map (VD: map 48 - Nhà Goku)
-- Sửa data của map 48, thêm [85,x,y]
```

### **Thay đổi thời gian countdown:**

```java
// File: AdminPanel.java
// Tìm dòng:
Maintenance.gI().startSeconds(20);

// Đổi 20 thành số khác (VD: 30, 60...)
Maintenance.gI().startSeconds(30); // 30 giây
```

### **Thêm hệ số EXP khác:**

```java
// File: AdminPanel.java - method showExpMenu()
// Thêm option:

"x1", "x2", "x5", "x10",
"x20", "x30", "x40", "x50",
"x100",  // ← Thêm x100
"⬅️ Quay lại"

// Trong method handleExpChange():
int[] expRates = {1, 2, 5, 10, 20, 30, 40, 50, 100}; // ← Thêm 100
```

---

## 🎯 **CHECKLIST CUỐI CÙNG:**

### **Code:**
- [ ] `ConstNpc.java` có `ADMIN_PANEL = 85`
- [ ] `NpcFactory.java` có import `AdminPanel`
- [ ] `NpcFactory.java` có case `ConstNpc.ADMIN_PANEL`
- [ ] File `AdminPanel.java` tồn tại trong `npc_list/`

### **Database:**
- [ ] Table `npc_template` có NPC ID 85
- [ ] Table `map_template` (map 5) có `[85,500,300]`
- [ ] Table `account` có `is_admin = 1`

### **Build & Run:**
- [ ] Server tắt hoàn toàn
- [ ] Build thành công (no errors)
- [ ] Server chạy OK
- [ ] Log hiện: "Successfully loaded npc template..."

### **Test:**
- [ ] Vào game với account admin
- [ ] Thấy NPC Admin Panel
- [ ] Click → Thấy menu
- [ ] Test bảo trì 20s → OK
- [ ] Test đổi EXP → OK

---

## 🎊 **HOÀN THÀNH!**

Bạn sẽ có Admin Panel hoàn chỉnh với đầy đủ chức năng quản trị server!

**3 CHỨC NĂNG CHÍNH:**
- ⏰ Bảo trì có countdown
- 👢 Kick all players
- ⭐ Thay đổi EXP real-time

**Chúc bạn thành công!** 🚀
