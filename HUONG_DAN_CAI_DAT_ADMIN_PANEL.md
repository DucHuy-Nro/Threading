# 🔧 HƯỚNG DẪN CÀI ĐẶT ADMIN PANEL (CHI TIẾT)

## 📋 **TỔNG QUAN:**

Admin Panel có 3 chức năng chính:
1. ⏰ **Bảo trì 20s** - Countdown từ 20→1 giây, thông báo mỗi giây
2. 👢 **Đá all player** - Kick tất cả ngay lập tức  
3. ⭐ **Thay đổi EXP** - x1, x2, x5, x10, x20, x30, x40, x50

---

## 🎯 **CÀI ĐẶT (5 BƯỚC ĐƠN GIẢN):**

### **BƯỚC 1: THÊM CONSTANT**

**File:** `src/nro/models/consts/ConstNpc.java`

**Tìm dòng cuối cùng của phần NPC IDs (khoảng dòng 156), thêm:**

```java
public static final byte XE_NUOC_MIA = 84;
public static final byte ADMIN_PANEL = 85;  // ← THÊM DÒNG NÀY
//----------------------index menu------------------------------------------
```

---

### **BƯỚC 2: ĐĂNG KÝ NPC TRONG FACTORY**

**File:** `src/nro/models/npc/NpcFactory.java`

**2a. Thêm import (dòng ~95):**

Tìm phần imports, thêm:

```java
import nro.models.npc_list.Berry;
import nro.models.npc_list.ChiChi;
import nro.models.npc_list.AdminPanel;  // ← THÊM DÒNG NÀY
```

**2b. Thêm case xử lý (dòng ~237):**

Tìm dòng:
```java
case ConstNpc.BERRY ->
    new Berry(mapId, status, cx, cy, tempId, avatar);
```

Thêm SAU dòng đó:

```java
case ConstNpc.BERRY ->
    new Berry(mapId, status, cx, cy, tempId, avatar);
case ConstNpc.ADMIN_PANEL ->                              // ← THÊM
    new AdminPanel(mapId, status, cx, cy, tempId, avatar); // ← DÒNG NÀY
default ->
```

---

### **BƯỚC 3: THÊM VÀO DATABASE**

**Mở Navicat:**

**3a. Table `npc_template` - Thêm NPC:**

```sql
INSERT INTO `npc_template` VALUES (
    85,                     -- id
    'Admin Panel',          -- NAME
    18,                     -- head (dùng sprite Ông Gohan)
    19,                     -- body
    20,                     -- leg
    349                     -- avatar
);
```

**3b. Table `map_template` - Đặt NPC vào map:**

**Chọn map muốn đặt NPC, ví dụ Map ID 5 (Đảo Kamê):**

1. Mở Navicat → Table `map_template`
2. Tìm dòng `id = 5`
3. Sửa cột `data` (cột thứ 11)
4. Thêm `[85, 500, 300]` vào cuối mảng

**VD hiện tại:**
```
[[39,984,408],[13,1068,408],[21,1205,408]]
```

**Sửa thành:**
```
[[39,984,408],[13,1068,408],[21,1205,408],[85,500,300]]
                                           ↑↑↑↑↑↑↑↑↑↑↑↑↑
                                      [npcId, x, y]
```

**HOẶC chạy SQL:**

```sql
-- Xem data hiện tại của map 5
SELECT data FROM map_template WHERE id = 5;

-- Copy kết quả, thêm [85,500,300] vào cuối, paste lại
```

---

### **BƯỚC 4: BUILD & RUN**

```bash
# 1. Build project
ant clean && ant jar

# 2. Run server
run.bat
```

**Nếu Build lỗi:**
- Tắt server trước (Ctrl+C hoặc taskkill)
- Chạy lại: `ant clean && ant jar`

---

### **BƯỚC 5: TEST IN-GAME**

1. **Login vào game**
2. **Đi đến Map ID 5** (Đảo Kamê)
3. **Tìm NPC** tại tọa độ (500, 300)
4. **Click vào NPC**

**Nếu là Admin:**
```
🔧 ADMIN PANEL 🔧
Xin chào Admin [tên]!
─────────────────────
EXP hiện tại: x1

[⏰ Bảo trì 20s]
[👢 Đá all player]
[⭐ Thay đổi EXP]
[📊 Thông tin server]
[Đóng]
```

**Nếu KHÔNG phải Admin:**
```
⛔ TRUY CẬP BỊ TỪ CHỐI ⛔
Bạn không có quyền sử dụng panel này!

[Đóng]
```

---

## ⚙️ **CÁCH SET ADMIN:**

### **Cách 1: Trong Database (KHUYẾN NGHỊ)**

```sql
-- Mở Navicat → Table account
-- Tìm account của bạn, sửa:

UPDATE account 
SET is_admin = 1 
WHERE username = 'your_username';

-- Logout và login lại game
```

### **Cách 2: Kiểm tra code Player:**

**File:** `src/nro/models/player/Player.java`

```java
public boolean isAdmin() {
    return this.session.isAdmin; 
    // hoặc: return this.isAdmin;
    // (tùy version)
}
```

---

## 🎮 **CÁCH SỬ DỤNG:**

### **1. Bảo trì 20s:**

```
Click "⏰ Bảo trì 20s"
    ↓
Xác nhận "✅ Đồng ý"
    ↓
Countdown bắt đầu:
  - 20s: "Server sẽ bảo trì sau 20 giây"
  - 19s: "Server sẽ bảo trì sau 19 giây"
  - ...
  - 1s: "Server sẽ bảo trì sau 1 giây"
    ↓
Server tắt tự động!
```

**Tất cả player nhận thông báo ĐỒNG THỜI mỗi giây!**

---

### **2. Đá all player:**

```
Click "👢 Đá all player"
    ↓
Xác nhận "✅ Đồng ý"
    ↓
Server gửi thông báo: "BẢO TRÌ KHẨN CẤP"
    ↓
Đợi 2 giây
    ↓
Kick tất cả player
    ↓
Server tắt ngay!
```

**KHÔNG CÓ COUNTDOWN - Tắt ngay lập tức!**

---

### **3. Thay đổi EXP:**

```
Click "⭐ Thay đổi EXP"
    ↓
Menu hiện:
  [x1] [x2] [x5] [x10]
  [x20] [x30] [x40] [x50]
    ↓
Chọn x10
    ↓
EXP server = x10 NGAY LẬP TỨC!
    ↓
Thông báo toàn server:
  "EXP đã thay đổi từ x1 → x10"
    ↓
Player giết mob ngay lúc đó sẽ nhận x10 EXP!
```

**KHÔNG CẦN RESTART SERVER!**

---

## 🔍 **TROUBLESHOOTING:**

### **Lỗi 1: NPC không hiện**

**Nguyên nhân:** 
- Chưa thêm vào `map_template`
- Tọa độ sai

**Fix:**
```sql
-- Kiểm tra map_template
SELECT id, NAME, data 
FROM map_template 
WHERE data LIKE '%[85%';

-- Nếu không có kết quả → Chưa thêm NPC vào map
```

---

### **Lỗi 2: Click NPC không có gì**

**Nguyên nhân:**
- Chưa đăng ký trong `NpcFactory`
- Build chưa thành công

**Fix:**
1. Kiểm tra `NpcFactory.java` có `case ConstNpc.ADMIN_PANEL`
2. Build lại: `ant clean && ant jar`
3. Restart server

---

### **Lỗi 3: "Bạn không có quyền"**

**Nguyên nhân:**
- Account chưa set admin

**Fix:**
```sql
UPDATE account 
SET is_admin = 1 
WHERE username = 'your_username';
```

Logout và login lại!

---

### **Lỗi 4: Bảo trì không hoạt động**

**Nguyên nhân:**
- `Maintenance.isRunning = true` (đang bảo trì rồi)

**Fix:**
```sql
-- Restart server
-- Hoặc check log xem có lỗi gì
```

---

### **Lỗi 5: EXP không đổi**

**Nguyên nhân:**
- Code tính EXP không dùng `Manager.RATE_EXP_SERVER`

**Kiểm tra:**
```java
// File: NPoint.java hoặc PlayerService.java
// Tìm đoạn code tính EXP, phải có:

long exp = baseExp * Manager.RATE_EXP_SERVER;
```

Nếu không có → Thêm vào!

---

## 📊 **KIỂM TRA CODE HOẠT ĐỘNG:**

### **Test 1: Kiểm tra Bảo trì 20s**

```java
// Trong console, bạn sẽ thấy:
[ADMIN ACTION] Admin_Name đã bật bảo trì 20 giây!
Hệ thống sẽ bảo trì sau 20 giây
Hệ thống sẽ bảo trì sau 19 giây
...
BẢO TRÌ BẮT ĐẦU
SUCCESSFULLY MAINTENANCE!
```

### **Test 2: Kiểm tra Kick All**

```java
// Trong console:
[ADMIN ACTION] Admin_Name đã đá all player!
BẮT ĐẦU BẢO TRÌ NGAY
SUCCESSFULLY MAINTENANCE!
```

### **Test 3: Kiểm tra EXP**

```java
// Trong console:
[ADMIN ACTION] Admin_Name đã đổi EXP từ x1 → x10

// Trong game:
// Giết 1 con mob cho 100 EXP
// Nhận được: 100 * 10 = 1000 EXP ✅
```

---

## 💡 **MẸO HAY:**

### **1. Đặt NPC ở map riêng:**

```sql
-- Tạo map admin riêng (map ID 999)
-- Chỉ admin mới vào được
```

### **2. Thêm xác nhận 2 lần:**

```java
// Với chức năng nguy hiểm, thêm confirm lần 2
if (select == 0) {
    createOtherMenu(player, 4,
        "BẠN CHẮC CHẮN 100% CHƯA?",
        "Có", "Không");
}
```

### **3. Log chi tiết:**

```java
Logger.log(Logger.RED, 
    "[" + TimeUtil.getTimeNow() + "] " +
    "Admin " + player.name + " (ID: " + player.id + ") " +
    "đã thay đổi EXP thành x" + newRate);
```

---

## 📋 **CHECKLIST CÀI ĐẶT:**

- [ ] Thêm `ADMIN_PANEL = 85` vào `ConstNpc.java`
- [ ] Import `AdminPanel` trong `NpcFactory.java`
- [ ] Thêm `case ConstNpc.ADMIN_PANEL` trong `NpcFactory.java`
- [ ] Thêm NPC vào `npc_template` (ID 85)
- [ ] Thêm NPC vào `map_template` (map 5, tọa độ 500,300)
- [ ] Build: `ant clean && ant jar`
- [ ] Set admin: `UPDATE account SET is_admin = 1`
- [ ] Restart server: `run.bat`
- [ ] Test in-game: Đi map 5, click NPC

---

## 🎉 **HOÀN THÀNH!**

Sau khi làm xong 5 bước trên, bạn sẽ có:

✅ NPC Admin Panel hoạt động đầy đủ  
✅ Bảo trì 20s với countdown  
✅ Kick all players tức khắc  
✅ Thay đổi EXP real-time (không cần restart)  
✅ Có xác nhận trước khi thực hiện  
✅ Log admin actions  
✅ Chỉ admin mới dùng được  

---

**CHÚC BẠN THÀNH CÔNG!** 🚀
