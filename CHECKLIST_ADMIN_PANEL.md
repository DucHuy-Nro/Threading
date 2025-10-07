# ✅ CHECKLIST CÀI ĐẶT ADMIN PANEL

## 📋 **LÀMTHEO THỨ TỰ NÀY:**

---

## 🗂️ **PHẦN 1: SỬA CODE (3 FILES)**

### ☑️ **1.1. File: ConstNpc.java**
**Đường dẫn:** `src/nro/models/consts/ConstNpc.java`

- [ ] Mở file
- [ ] Tìm dòng: `public static final byte XE_NUOC_MIA = 84;`
- [ ] Thêm dòng sau nó: `public static final byte ADMIN_PANEL = 85;`
- [ ] Save file

---

### ☑️ **1.2. File: NpcFactory.java**
**Đường dẫn:** `src/nro/models/npc/NpcFactory.java`

**Sửa 2 chỗ:**

- [ ] **Chỗ 1 - Import (dòng ~90):**
  - Tìm: `import nro.models.npc_list.Berry;`
  - Thêm sau: `import nro.models.npc_list.AdminPanel;`

- [ ] **Chỗ 2 - Case xử lý (dòng ~237):**
  - Tìm: `case ConstNpc.BERRY -> new Berry(...);`
  - Thêm sau: `case ConstNpc.ADMIN_PANEL -> new AdminPanel(mapId, status, cx, cy, tempId, avatar);`

- [ ] Save file

---

### ☑️ **1.3. File: AdminPanel.java**
**Đường dẫn:** `src/nro/models/npc_list/AdminPanel.java`

- [x] **Đã tạo sẵn!** Không cần làm gì ✅

---

## 💾 **PHẦN 2: SỬA DATABASE (3 BẢNG)**

### ☑️ **2.1. Mở Navicat**

- [ ] Mở Navicat
- [ ] Connect vào database `ngocrong`

---

### ☑️ **2.2. Table: npc_template**

- [ ] Mở table `npc_template`
- [ ] Click **"+"** (Add new row)
- [ ] Điền:
  ```
  id:     85
  NAME:   Admin Panel
  head:   18
  body:   19
  leg:    20
  avatar: 349
  ```
- [ ] Click **✓** (Save)

**HOẶC chạy SQL:**

- [ ] Tab Query
- [ ] Paste:
  ```sql
  INSERT INTO `npc_template` VALUES (85, 'Admin Panel', 18, 19, 20, 349);
  ```
- [ ] Run

---

### ☑️ **2.3. Table: map_template**

**Đặt NPC vào map 5 (Đảo Kamê):**

- [ ] Mở table `map_template`
- [ ] Tìm dòng `id = 5`
- [ ] Double-click cột `data`
- [ ] Tìm cuối mảng, VD: `...,[21,1205,408]]`
- [ ] Thêm: `,[85,500,300]` trước dấu `]]`
- [ ] Kết quả: `...,[21,1205,408],[85,500,300]]`
- [ ] Click ✓ Save

**Hoặc đặt vào map khác:**
- Map 48 (Nhà Kaio): Thêm `[85,600,400]`
- Map 0 (Làng Aru): Thêm `[85,300,200]`

---

### ☑️ **2.4. Table: account**

**Set admin cho tài khoản:**

- [ ] Mở table `account`
- [ ] Tìm dòng có `username` của bạn
- [ ] Double-click cột `is_admin`
- [ ] Đổi từ `0` → `1`
- [ ] Click ✓ Save

**HOẶC chạy SQL:**

- [ ] Paste:
  ```sql
  UPDATE account SET is_admin = 1 WHERE username = 'your_username';
  ```
- [ ] Đổi `your_username` thành username thật
- [ ] Run

---

## 🔨 **PHẦN 3: BUILD & RUN**

### ☑️ **3.1. Tắt server (nếu đang chạy)**

- [ ] Vào cửa sổ CMD đang chạy server
- [ ] Nhấn **Ctrl+C**
- [ ] Hoặc: `taskkill /F /IM java.exe`

---

### ☑️ **3.2. Build project**

**Trong NetBeans:**
- [ ] Right-click project
- [ ] Chọn **"Clean and Build"** (hoặc Shift+F11)
- [ ] Đợi build xong (thấy "BUILD SUCCESSFUL")

**Trong CMD:**
- [ ] Mở CMD tại thư mục project
- [ ] Chạy: `ant clean`
- [ ] Chạy: `ant jar`
- [ ] Kiểm tra: "BUILD SUCCESSFUL"

---

### ☑️ **3.3. Restart server**

- [ ] Chạy `run.bat`
- [ ] Đợi server start
- [ ] Kiểm tra log thấy:
  ```
  ✓ Successfully loaded npc template (...)
  ✓ Active Port 14445
  ```

---

## 🎮 **PHẦN 4: TEST IN-GAME**

### ☑️ **4.1. Login**

- [ ] Mở game client
- [ ] Login với account đã set admin
- [ ] Vào game thành công

---

### ☑️ **4.2. Đi đến map có NPC**

- [ ] Dịch chuyển đến **Map 5** (Đảo Kamê)
- [ ] Tìm NPC tại tọa độ **(500, 300)**
- [ ] Thấy NPC "Admin Panel"

---

### ☑️ **4.3. Test chức năng**

**Test menu chính:**
- [ ] Click vào NPC
- [ ] Thấy menu Admin Panel
- [ ] Có 4 options + nút Đóng

**Test Bảo trì 20s:**
- [ ] Click "⏰ Bảo trì 20s"
- [ ] Click "✅ Đồng ý"
- [ ] Thấy countdown xuất hiện
- [ ] Hủy test (restart server) hoặc để chạy hết

**Test Thay đổi EXP:**
- [ ] Click "⭐ Thay đổi EXP"
- [ ] Thấy menu x1, x2, x5...
- [ ] Chọn x10
- [ ] Thấy thông báo "EXP đã đổi thành x10"
- [ ] Giết 1 con mob
- [ ] Kiểm tra EXP nhận được (phải x10)

**Test Thông tin server:**
- [ ] Click "📊 Thông tin server"
- [ ] Thấy info: player online, EXP rate, port...

**Test với account thường:**
- [ ] Logout
- [ ] Login account không phải admin
- [ ] Click NPC
- [ ] Thấy: "⛔ TRUY CẬP BỊ TỪ CHỐI"

---

## ❌ **TROUBLESHOOTING:**

### **Vấn đề 1: Build lỗi**

```
Lỗi: cannot find symbol AdminPanel
```

**Fix:**
- [ ] Kiểm tra file `AdminPanel.java` có trong `src/nro/models/npc_list/`
- [ ] Kiểm tra import trong `NpcFactory.java`
- [ ] Build lại

---

### **Vấn đề 2: NPC không hiện**

**Fix:**
- [ ] Kiểm tra đã thêm vào `npc_template` chưa:
  ```sql
  SELECT * FROM npc_template WHERE id = 85;
  ```
- [ ] Kiểm tra đã thêm vào `map_template` chưa:
  ```sql
  SELECT data FROM map_template WHERE id = 5;
  ```
- [ ] Restart server
- [ ] Vào đúng map (Map 5)
- [ ] Đi đúng tọa độ (500, 300)

---

### **Vấn đề 3: "Không có quyền"**

**Fix:**
- [ ] Kiểm tra `is_admin` trong database:
  ```sql
  SELECT username, is_admin FROM account;
  ```
- [ ] Đảm bảo `is_admin = 1` cho account của bạn
- [ ] **Logout và login lại game!** (Quan trọng!)

---

### **Vấn đề 4: Click NPC không có menu**

**Fix:**
- [ ] Kiểm tra đã thêm case trong `NpcFactory.java`
- [ ] Kiểm tra constant `ADMIN_PANEL = 85`
- [ ] Build lại project
- [ ] Restart server

---

### **Vấn đề 5: Bảo trì không hoạt động**

**Fix:**
- [ ] Kiểm tra console có log gì
- [ ] Kiểm tra `Maintenance.isRunning` (nếu true thì đang bảo trì rồi)
- [ ] Restart server

---

### **Vấn đề 6: EXP không đổi**

**Fix:**
- [ ] Kiểm tra console có thông báo "đã đổi EXP"
- [ ] Kiểm tra biến `Manager.RATE_EXP_SERVER`
- [ ] Giết mob mới (không phải mob cũ)
- [ ] Check code tính EXP có dùng `RATE_EXP_SERVER` không

---

## 📊 **VERIFICATION (XÁC NHẬN):**

### **Database:**
```sql
-- Chạy query này để kiểm tra tất cả:

SELECT 'NPC Template' AS item, 
       CASE WHEN COUNT(*) > 0 THEN '✅' ELSE '❌' END AS status
FROM npc_template WHERE id = 85

UNION ALL

SELECT 'Admin Account',
       CASE WHEN COUNT(*) > 0 THEN '✅' ELSE '❌' END
FROM account WHERE is_admin = 1;

-- Kết quả phải:
-- NPC Template | ✅
-- Admin Account | ✅
```

### **Code:**
```bash
# Kiểm tra file tồn tại:
ls src/nro/models/npc_list/AdminPanel.java
# → Phải có file này

# Kiểm tra constant:
grep "ADMIN_PANEL = 85" src/nro/models/consts/ConstNpc.java
# → Phải có 1 dòng

# Kiểm tra import:
grep "import.*AdminPanel" src/nro/models/npc/NpcFactory.java
# → Phải có 1 dòng

# Kiểm tra case:
grep "case.*ADMIN_PANEL" src/nro/models/npc/NpcFactory.java
# → Phải có 1 dòng
```

---

## 🎉 **HOÀN THÀNH!**

Nếu tất cả ✅ trong checklist → **ADMIN PANEL ĐÃ SẴN SÀNG!**

Enjoy quản trị server! 🔧✨
