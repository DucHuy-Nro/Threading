# 🎮 BẮT ĐẦU TẠI ĐÂY - HƯỚNG DẪN NHANH

## 🎯 **BẠN ĐANG CÓ GÌ:**

✅ **NPC SGohan (ID 80)** - Đã tạo xong, bán Tuyệt Kỹ  
⏳ **Admin Panel (ID 85)** - Chuẩn bị tạo (3 phút)  

---

## 🚀 **CÀI ĐẶT ADMIN PANEL (3 PHÚT):**

### **📍 BƯỚC 1: CHẠY SQL (30 giây)**

1. Mở **Navicat**
2. Mở file **`RUN_THIS_SQL.sql`**
3. Copy toàn bộ → Paste vào Navicat
4. **Thay `'admin'` thành username của bạn** (dòng 48)
5. Click **Run** ▶️

**⚠️ CHÚ Ý:** Phần thêm NPC vào map phải SỬA THỦ CÔNG!

**Cách sửa:**
- Table `map_template` → Tìm map ID 5
- Cột `data` → Double-click
- Thêm `[85,500,300]` vào cuối
- VD: `[[39,984,408],[80,1418,456]]` → `[[39,984,408],[80,1418,456],[85,500,300]]`
- Save

---

### **📍 BƯỚC 2: SỬA CODE (1 phút)**

**File 1:** `src/nro/models/consts/ConstNpc.java`

**Tìm dòng 156, THÊM:**
```java
public static final byte ADMIN_PANEL = 85;
```

---

**File 2:** `src/nro/models/npc/NpcFactory.java`

**Thêm import (dòng ~95):**
```java
import nro.models.npc_list.AdminPanel;
```

**Thêm case (dòng ~239):**
```java
case ConstNpc.ADMIN_PANEL ->
    new AdminPanel(mapId, status, cx, cy, tempId, avatar);
```

**⚠️ PHẢI THÊM TRƯỚC `default ->`!**

---

### **📍 BƯỚC 3: BUILD (30 giây)**

```bash
# TẮT SERVER TRƯỚC!
taskkill /F /IM java.exe

# BUILD
ant clean
ant jar
```

---

### **📍 BƯỚC 4: RUN & TEST (1 phút)**

```bash
# RUN SERVER
run.bat

# TRONG GAME:
# 1. Login (account admin)
# 2. Đi map 5 (Đảo Kamê)
# 3. Tìm NPC:
#    - SGohan tại (1418, 456)
#    - Admin Panel tại (500, 300)
# 4. Click Admin Panel → Test!
```

---

## ✅ **SAU KHI XONG:**

Bạn sẽ có **Admin Panel** với:

⏰ **Bảo trì 20s** - Countdown từng giây
```
20s → 19s → ... → 1s → Server tắt
Tất cả player nhận thông báo real-time!
```

👢 **Đá all player** - Kick tức khắc
```
Click → Confirm → Server tắt ngay!
```

⭐ **Thay đổi EXP** - x1 đến x50
```
x1 → x10 → Player nhận x10 EXP NGAY!
Không cần restart!
```

---

## 📁 **CẤU TRÚC FILES:**

```
📖 ĐỌC ĐẦU TIÊN:
   ⭐ BAT_DAU_O_DAY.md              ← Đang đọc file này!
   ⭐ ADMIN_PANEL_HOAN_CHINH.md     ← Hướng dẫn chi tiết

📝 HƯỚNG DẪN:
   ✅ HUONG_DAN_CAI_DAT_ADMIN_PANEL.md
   ✅ CODE_SNIPPET_ADMIN_PANEL.md
   ✅ PHAN_TICH_ADMIN_PANEL.md

💾 SQL:
   ⭐ RUN_THIS_SQL.sql              ← Chạy file này!
   ✅ CAI_DAT_ADMIN_PANEL.sql

💻 CODE:
   ⭐ AdminPanel.java                ← Code chính (đã tạo sẵn)
   ✅ SGohan.java                    ← NPC shop (đã có)

📚 TỔNG HỢP:
   ✅ TONG_KET_CUOI_CUNG.md
```

---

## ⚡ **QUICK START (3 LỆNH):**

```bash
# 1. Chạy SQL (trong Navicat):
#    - Mở RUN_THIS_SQL.sql
#    - Thay 'admin' thành username của bạn
#    - Sửa map_template thủ công (thêm [85,500,300])
#    - Run

# 2. Sửa code:
#    - ConstNpc.java: Thêm 1 dòng
#    - NpcFactory.java: Thêm 1 import + 2 dòng

# 3. Build & Run:
taskkill /F /IM java.exe && ant clean && ant jar && run.bat
```

---

## 📞 **NẾU GẶP LỖI:**

### **Lỗi Build:**
- Đọc: `FIX_LOI_SHOP.md`
- Đọc: `CODE_SNIPPET_ADMIN_PANEL.md`

### **Lỗi NPC không hiện:**
- Check: `RUN_THIS_SQL.sql` đã chạy chưa?
- Check: Map data đã thêm `[85,500,300]` chưa?

### **Lỗi "Không có quyền":**
- Check: `is_admin = 1` chưa?
- Logout và login lại chưa?

---

## 🎊 **CHÚC MỪNG!**

Sau 3 phút, bạn sẽ có Admin Panel đầy đủ tính năng!

**BẮT ĐẦU NGAY:**

1. 📖 **Đọc file này** ✅
2. ⭐ **Chạy `RUN_THIS_SQL.sql`**
3. ✏️ **Sửa 2 files code**
4. 🔨 **Build & Run**
5. 🎮 **Enjoy!**

---

**CHÚC BẠN THÀNH CÔNG!** 🚀
