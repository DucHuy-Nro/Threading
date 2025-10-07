# ⚡ HƯỚNG DẪN NHANH - ADMIN PANEL (5 PHÚT)

## 🎯 **BẠN SẼ CÓ GÌ:**

1. ⏰ **Bảo trì 20s** - Countdown hiện mỗi giây
2. 👢 **Đá all player** - Kick tất cả ngay
3. ⭐ **Đổi EXP** - x1 đến x50 (không cần restart!)

---

## 🚀 **5 BƯỚC ĐƠN GIẢN:**

### **BƯỚC 1: SỬA CODE (3 DÒNG)**

#### **File: `ConstNpc.java` (dòng 157)**
```java
public static final byte ADMIN_PANEL = 85;  // ← Thêm dòng này
```

#### **File: `NpcFactory.java` (dòng 95)**
```java
import nro.models.npc_list.AdminPanel;  // ← Thêm dòng này
```

#### **File: `NpcFactory.java` (dòng 240)**
```java
case ConstNpc.ADMIN_PANEL ->                              // ← Thêm
    new AdminPanel(mapId, status, cx, cy, tempId, avatar); // ← 2 dòng
```

**File `AdminPanel.java` ĐÃ TẠO SẴN RỒI!** ✅

---

### **BƯỚC 2: THÊM VÀO DATABASE (2 LỆNH SQL)**

**Mở Navicat, chạy:**

```sql
-- 1. Thêm NPC template
INSERT INTO `npc_template` VALUES (85, 'Admin Panel', 18, 19, 20, 349);

-- 2. Kiểm tra map 5
SELECT id, NAME, data FROM map_template WHERE id = 5;
```

**Sau đó SỬA THỦ CÔNG cột `data`:**
- Double-click vào cột `data`
- Thêm `,[85,500,300]` vào cuối (trước dấu `]` cuối cùng)

**VD:**
```
TỪ: [[39,984,408],[13,1068,408],[21,1205,408]]
THÀNH: [[39,984,408],[13,1068,408],[21,1205,408],[85,500,300]]
```

---

### **BƯỚC 3: SET ADMIN**

```sql
-- Thay 'admin' thành username của bạn
UPDATE account SET is_admin = 1 WHERE username = 'admin';
```

---

### **BƯỚC 4: BUILD**

```bash
# Tắt server trước!
taskkill /F /IM java.exe

# Build
ant clean && ant jar
```

---

### **BƯỚC 5: RUN & TEST**

```bash
# Chạy server
run.bat

# Test in-game:
# 1. Login
# 2. Đi map Đảo Kamê (Map 5)
# 3. Tìm NPC tại (500, 300)
# 4. Click NPC → Menu admin hiện! ✅
```

---

## 🎮 **DEMO CHỨC NĂNG:**

### **📹 Bảo trì 20s:**
```
Click "⏰ Bảo trì 20s" → Xác nhận
→ Thông báo xuất hiện:
   "Server sẽ bảo trì sau 20 giây"
   "Server sẽ bảo trì sau 19 giây"
   ...
   "Server sẽ bảo trì sau 1 giây"
→ Server tắt!
```

### **📹 Đá all player:**
```
Click "👢 Đá all player" → Xác nhận
→ "BẢO TRÌ KHẨN CẤP!"
→ Server tắt ngay!
```

### **📹 Thay đổi EXP:**
```
Click "⭐ Thay đổi EXP"
→ Menu: [x1] [x2] [x5] ... [x50]
→ Chọn x10
→ Thông báo: "EXP đã đổi thành x10"
→ Giết mob ngay lúc đó → Nhận x10 EXP!
```

---

## 📋 **CHECKLIST NHANH:**

```
□ Sửa ConstNpc.java (1 dòng)
□ Sửa NpcFactory.java (2 chỗ)
□ Chạy SQL thêm NPC (1 lệnh)
□ Sửa map_template (thủ công)
□ Set admin (1 lệnh SQL)
□ Build project (ant clean && ant jar)
□ Run server (run.bat)
□ Test in-game
```

**TỔNG: ~5 phút!** ⏱️

---

## ❓ **FAQ:**

**Q: NPC không hiện?**
- Kiểm tra `npc_template` có ID 85 chưa
- Kiểm tra `map_template` đã thêm [85,x,y] chưa
- Restart server chưa?

**Q: Click NPC không có gì?**
- Build thành công chưa?
- File `AdminPanel.java` có tồn tại không?
- Check log có lỗi gì?

**Q: "Bạn không có quyền"?**
- Chạy SQL: `UPDATE account SET is_admin = 1 WHERE username = 'your_username'`
- Logout và login lại!

**Q: EXP không đổi?**
- Check log xem có lỗi không
- Thử logout/login lại
- Giết mob để test

---

## 🎉 **KẾT QUẢ:**

Sau 5 phút, bạn sẽ có:
- ✅ Admin Panel hoàn chỉnh
- ✅ 3 chức năng đầy đủ
- ✅ Menu đẹp, dễ dùng
- ✅ An toàn (chỉ admin)
- ✅ Có xác nhận
- ✅ Real-time (không restart)

---

**BẮT ĐẦU NGAY! CHỈ 5 PHÚT!** 🚀
