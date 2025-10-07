# 👶 HƯỚNG DẪN TỪNG BƯỚC - DÀNH CHO NGƯỜI MỚI

## 🎯 **MỤC TIÊU:**

Tạo NPC Admin Panel với 3 chức năng:
- ⏰ Bảo trì 20s (countdown)
- 👢 Đá all player  
- ⭐ Đổi EXP x1-x50

---

## 📚 **CHUẨN BỊ:**

Bạn cần có:
- ✅ NetBeans (hoặc IDE Java)
- ✅ Navicat (hoặc công cụ quản lý MySQL)
- ✅ Source code NRO
- ✅ Database đã import

---

## 🚀 **CÀI ĐẶT - 5 BƯỚC:**

---

### **BƯỚC 1/5: THÊM FILE JAVA MỚI** ⭐

**1.1. Mở NetBeans**

**1.2. Tìm folder:**
```
Source Packages
  └─ nro.models.npc_list
```

**1.3. Right-click vào `npc_list` → New → Java Class**

**1.4. Đặt tên:** `AdminPanel`

**1.5. Click Finish**

**1.6. XÓA HẾT CODE MẶC ĐỊNH trong file mới tạo**

**1.7. Copy toàn bộ code từ file:**
```
AdminPanel.java (trong workspace)
```

**1.8. Paste vào file vừa tạo**

**1.9. Save (Ctrl+S)**

✅ **XONG BƯỚC 1!**

---

### **BƯỚC 2/5: SỬA FILE ConstNpc.java** 📝

**2.1. Trong NetBeans, mở file:**
```
Source Packages
  └─ nro.models.consts
      └─ ConstNpc.java
```

**2.2. Tìm dòng (khoảng dòng 156):**
```java
public static final byte XE_NUOC_MIA = 84;
//----------------------index menu------------------------------------------
```

**2.3. Thêm 1 dòng giữa 2 dòng trên:**
```java
public static final byte XE_NUOC_MIA = 84;
public static final byte ADMIN_PANEL = 85;  // ← THÊM DÒNG NÀY
//----------------------index menu------------------------------------------
```

**2.4. Save (Ctrl+S)**

✅ **XONG BƯỚC 2!**

---

### **BƯỚC 3/5: SỬA FILE NpcFactory.java** 🏭

**3.1. Trong NetBeans, mở file:**
```
Source Packages
  └─ nro.models.npc
      └─ NpcFactory.java
```

**3.2. PHẦN A - Thêm import:**

Tìm dòng (khoảng dòng 89-95):
```java
import nro.models.npc_list.Berry;
import nro.models.npc_list.ChiChi;
```

Thêm dòng:
```java
import nro.models.npc_list.Berry;
import nro.models.npc_list.AdminPanel;  // ← THÊM DÒNG NÀY
import nro.models.npc_list.ChiChi;
```

**3.3. PHẦN B - Thêm case xử lý:**

Tìm dòng (khoảng dòng 237):
```java
case ConstNpc.BERRY ->
    new Berry(mapId, status, cx, cy, tempId, avatar);
default ->
```

Thêm 2 dòng GIỮA BERRY và DEFAULT:
```java
case ConstNpc.BERRY ->
    new Berry(mapId, status, cx, cy, tempId, avatar);
case ConstNpc.ADMIN_PANEL ->                              // ← THÊM
    new AdminPanel(mapId, status, cx, cy, tempId, avatar); // ← DÒNG NÀY
default ->
```

**3.4. Save (Ctrl+S)**

✅ **XONG BƯỚC 3!**

---

### **BƯỚC 4/5: THÊM VÀO DATABASE** 💾

**4.1. Mở Navicat**

**4.2. Kết nối database `ngocrong`**

**4.3. PHẦN A - Thêm NPC Template:**

- Click vào table `npc_template`
- Click nút **"+"** (Add record) hoặc chạy SQL:

```sql
INSERT INTO `npc_template` VALUES (
    85, 'Admin Panel', 18, 19, 20, 349
);
```

- Click **✓** (Save)

**4.4. PHẦN B - Thêm NPC vào Map:**

- Click vào table `map_template`
- Tìm dòng `id = 5` (Map Đảo Kamê)
- Double-click vào ô `data`
- Tìm cuối mảng, VD: `...[54,1292,408]]`
- Thêm `,[85,500,300]` VÀO TRƯỚC `]]`

**VÍ DỤ:**

**TỪ:**
```
[[39,984,408],[13,1068,408],[21,1205,408],[54,1292,408]]
```

**THÀNH:**
```
[[39,984,408],[13,1068,408],[21,1205,408],[54,1292,408],[85,500,300]]
                                                          ↑↑↑↑↑↑↑↑↑↑↑↑
                                                          THÊM PHẦN NÀY
```

- Click **✓** (Save)

**4.5. PHẦN C - Set Admin:**

- Click vào table `account`
- Tìm dòng username của bạn (VD: `admin`)
- Double-click vào ô `is_admin`
- Đổi từ `0` → `1`
- Click **✓** (Save)

✅ **XONG BƯỚC 4!**

---

### **BƯỚC 5/5: BUILD & RUN** 🚀

**5.1. TẮT SERVER** (nếu đang chạy)
```
- Vào cửa sổ CMD đang chạy server
- Nhấn Ctrl+C
- Hoặc đóng cửa sổ
```

**5.2. BUILD PROJECT**

**Trong NetBeans:**
- Click phải vào Project → **Clean and Build**
- Hoặc nhấn: **Shift + F11**
- Chờ build xong (10-30 giây)

**HOẶC dùng CMD:**
```bash
cd "E:\Source NRO by me\Threading"
ant clean
ant jar
```

**5.3. CHẠY SERVER**

Double-click file: `run.bat`

Hoặc CMD:
```bash
cd "E:\Source NRO by me\Threading"
run.bat
```

**5.4. Chờ server start:**

Trong console sẽ thấy:
```
✓ Successfully loaded npc template (...)
✓ Successfully loaded map template (...)
✓ Active Port 14445
```

**→ Server đã sẵn sàng!** ✅

---

## 🎮 **TEST IN-GAME:**

### **1. Login vào game** với account admin

### **2. Di chuyển đến Map ID 5** (Đảo Kamê)

### **3. Tìm NPC tại tọa độ (500, 300)**

### **4. Click vào NPC**

**Nếu thành công, sẽ thấy:**

```
┌─────────────────────────────┐
│   🔧 ADMIN PANEL 🔧         │
├─────────────────────────────┤
│ Xin chào Admin [tên]!       │
│ ─────────────────────       │
│ EXP hiện tại: x1            │
│ Chọn chức năng:             │
│                             │
│ [⏰ Bảo trì 20s]            │
│ [👢 Đá all player]          │
│ [⭐ Thay đổi EXP]           │
│ [📊 Thông tin server]       │
│ [Đóng]                      │
└─────────────────────────────┘
```

---

### **5. TEST TỪNG CHỨC NĂNG:**

#### **TEST 1: Thay đổi EXP** (an toàn nhất)

```
1. Click "⭐ Thay đổi EXP"
2. Chọn "x10"
3. Sẽ thấy thông báo: "EXP đã thay đổi từ x1 → x10"
4. Giết 1 con mob → Nhận x10 EXP!
```

#### **TEST 2: Bảo trì 20s** (cẩn thận!)

```
1. Click "⏰ Bảo trì 20s"
2. Click "✅ Đồng ý"
3. Thông báo sẽ hiện mỗi giây:
   - "Server sẽ bảo trì sau 20 giây"
   - "Server sẽ bảo trì sau 19 giây"
   - ...
4. Sau 20 giây → Server tắt!
```

⚠️ **CHÚ Ý:** Server sẽ TẮT THẬT! Chỉ test khi sẵn sàng!

#### **TEST 3: Đá all player** (NGUY HIỂM!)

```
1. Click "👢 Đá all player"
2. Click "✅ Đồng ý"
3. Server tắt NGAY LẬP TỨC!
```

⚠️ **CẢNH BÁO:** Server sẽ tắt ngay! Tất cả player bị kick!

---

## ❌ **KHI GẶP LỖI:**

### **Lỗi 1: "Cannot find symbol AdminPanel"**

**Nguyên nhân:** Chưa tạo file `AdminPanel.java`

**Fix:** Làm lại BƯỚC 1

---

### **Lỗi 2: "ADMIN_PANEL cannot be resolved"**

**Nguyên nhân:** Chưa thêm vào `ConstNpc.java`

**Fix:** Làm lại BƯỚC 2

---

### **Lỗi 3: NPC không hiện trong game**

**Nguyên nhân:** Chưa thêm vào database hoặc map

**Fix:** Làm lại BƯỚC 4

**Kiểm tra:**
```sql
-- Phải có kết quả
SELECT * FROM npc_template WHERE id = 85;

-- Phải có [85, trong data
SELECT * FROM map_template WHERE id = 5 AND data LIKE '%85%';
```

---

### **Lỗi 4: "Bạn không có quyền"**

**Nguyên nhân:** Account chưa set admin

**Fix:**
```sql
UPDATE account SET is_admin = 1 WHERE username = 'your_username';
```

Logout và login lại!

---

### **Lỗi 5: Build failed - "Unable to delete NgocRongOnline.jar"**

**Nguyên nhân:** Server đang chạy

**Fix:**
1. Tắt server (Ctrl+C)
2. Task Manager → End Task `java.exe`
3. Build lại

---

## 📋 **CHECKLIST HOÀN CHỈNH:**

### **Files:**
- [ ] `AdminPanel.java` - Đã tạo trong npc_list
- [ ] `ConstNpc.java` - Đã thêm ADMIN_PANEL = 85
- [ ] `NpcFactory.java` - Đã thêm import và case

### **Database:**
- [ ] `npc_template` - Có NPC ID 85
- [ ] `map_template` - Map 5 có [85,500,300]
- [ ] `account` - is_admin = 1

### **Build:**
- [ ] Server đã tắt
- [ ] Clean and Build thành công
- [ ] File .jar mới được tạo

### **Test:**
- [ ] Server chạy OK
- [ ] Login với account admin
- [ ] NPC hiển thị ở map 5
- [ ] Click NPC → Menu hiện
- [ ] Test đổi EXP → OK
- [ ] (Optional) Test bảo trì

---

## 🎉 **HOÀN THÀNH!**

Nếu tất cả checklist đều ✅ → **THÀNH CÔNG!**

Admin Panel của bạn đã sẵn sàng! 🔧

---

## 📞 **NẾU CẦN TRỢ GIÚP:**

**Copy lỗi và hỏi:**
- Lỗi build → Copy toàn bộ error message
- Lỗi runtime → Copy log server
- NPC không hiện → Chụp màn hình database

---

**CHÚC BẠN THÀNH CÔNG!** 🎊
