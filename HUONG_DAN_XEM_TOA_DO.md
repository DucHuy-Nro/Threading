# 📍 HƯỚNG DẪN XEM TỌA ĐỘ

## ✅ **ĐÃ THÊM CHỨC NĂNG XEM TỌA ĐỘ!**

---

## 🎯 **MỤC ĐÍCH:**

Giúp bạn xem tọa độ hiện tại để:
- ✅ Đặt NPC ở vị trí mong muốn
- ✅ Tạo waypoint
- ✅ Debug map
- ✅ Thiết kế bản đồ

---

## 📁 **FILE ĐÃ TẠO:**

### **1. KhuVuc.java**
```
Vị trí: src/nro/models/npc_list/KhuVuc.java
Chức năng: NPC hiển thị tọa độ khi click
```

### **2. NpcFactory.java (đã sửa)**
```
Đã thêm:
  - Import KhuVuc
  - Case ConstNpc.KHU_VUC
```

---

## 🎮 **CÁCH SỬ DỤNG:**

### **CÁCH 1: Click NPC "Khu vực" (ID = 6)**

1. **NPC "Khu vực" đã CÓ SẴN trong hầu hết các map!**
   - Làng Aru có NPC Khu vực
   - Làng Mori có NPC Khu vực
   - Làng Kakarot có NPC Khu vực
   - ...

2. **Click vào NPC "Khu vực"**
   
3. **Thông tin hiển thị:**
   ```
   🗺️ THÔNG TIN VỊ TRÍ
   ━━━━━━━━━━━━━━━━━━━
   📍 Map ID: 0
   🔢 Zone ID: 0
   📐 Tọa độ X: 432
   📐 Tọa độ Y: 336
   ━━━━━━━━━━━━━━━━━━━
   💡 Sử dụng để đặt NPC hoặc teleport!
   ```

4. **Ghi lại tọa độ để sử dụng!**

---

## 📝 **SỬ DỤNG TỌA ĐỘ:**

### **1. Thêm NPC vào map:**

**Ví dụ:** Thêm Đường Tăng vào Làng Aru tại tọa độ X=432, Y=336

**Trong SQL:**
```sql
-- Map ID = 0 (Làng Aru)
-- Tìm dòng map_template với id = 0
-- Thêm [49,432,336,4544] vào array NPCs

UPDATE map_template 
SET npcs = '[[7,228,432],[67,590,432],[74,426,432],[49,432,336,4544]]'
WHERE id = 0;
```

**Format:** `[NPC_ID, X, Y, Avatar]`

---

### **2. Tạo waypoint trong map:**

**Trong SQL map_template:**
```sql
-- Ví dụ tạo cửa từ Map 0 → Map 124 tại tọa độ 432, 336
"[\"Đi Ngũ Hành Sơn\",432,336,456,360,0,0,124,100,360]"
```

**Format:** 
```
[Tên, X_start, Y_start, X_end, Y_end, type, icon, targetMapId, targetX, targetY]
```

---

## 🏷️ **THÔNG TIN NPC KHU VỰC:**

```sql
NPC ID: 6
Tên: "Khu vực"
Template trong npc_template:
  - id: 6
  - name: 'Khu vực'
  - avatar: -1, -1, -1, 0
```

**NPC này đã CÓ SẴN trong database của bạn!**

---

## 🔨 **BUILD & TEST:**

```bash
# Build
ant clean && ant jar

# Chạy server
run.bat
```

### **Test:**
1. Vào game
2. Tìm NPC "Khu vực" (thường ở giữa làng)
3. Click vào
4. Xem tọa độ hiện tại!

---

## 💡 **MẸO HAY:**

### **Tìm tọa độ chính xác:**

1. **Đứng ở vị trí muốn đặt NPC**
2. **Click NPC "Khu vực" gần nhất**
3. **Ghi lại tọa độ X, Y**
4. **Sử dụng tọa độ đó để thêm NPC**

---

### **Tọa độ thường dùng:**

**Làng Aru (Map 0):**
- Giữa làng: X=432, Y=336
- Gần nhà Gôhan: X=300, Y=432
- Bên phải: X=600, Y=432

**Làng Mori (Map 7):**
- Giữa làng: X=480, Y=408

**Làng Kakarot (Map 14):**
- Giữa làng: X=540, Y=408

---

## 📊 **FORMAT TỌA ĐỘ TRONG SQL:**

### **Trong bảng `map_template`:**

```sql
-- Cột npcs:
'[[NPC_ID, X, Y, Avatar], [NPC_ID, X, Y, Avatar], ...]'

-- Ví dụ:
'[[7,228,432], [49,432,336,4544], [67,590,432]]'
```

### **Trong bảng `npc` (nếu có):**
```sql
INSERT INTO npc VALUES 
(null, map_id, zone_id, status, x, y, npc_template_id);

-- Ví dụ:
INSERT INTO npc VALUES 
(null, 0, 0, 0, 432, 336, 49);
```

---

## ⚠️ **LƯU Ý:**

### **1. NPC "Khu vực" không bán hàng:**
- Chỉ hiển thị thông tin
- Không có chức năng khác

### **2. Tọa độ có thể thay đổi:**
- Khi di chuyển
- Mỗi lần click NPC sẽ show tọa độ MỚI NHẤT

### **3. Tọa độ hợp lệ:**
- X: 0 → chiều rộng map
- Y: 0 → chiều cao map
- Thường: 0-1800 (X), 0-600 (Y)

---

## 🎉 **HOÀN THÀNH!**

Bây giờ bạn có thể:
- ✅ Xem tọa độ bất kỳ lúc nào
- ✅ Đặt NPC chính xác
- ✅ Thiết kế map dễ dàng

**BUILD & TEST NGAY!** 🚀

---

## 📖 **VÍ DỤ THỰC TẾ:**

### **Thêm NPC Đường Tăng vào Làng Aru:**

**Bước 1:** Vào game, đứng ở vị trí muốn đặt NPC

**Bước 2:** Click NPC "Khu vực"
```
Tọa độ X: 500
Tọa độ Y: 432
```

**Bước 3:** Mở Navicat, vào bảng `map_template`, map ID = 0

**Bước 4:** Sửa cột `npcs`, thêm:
```sql
'[[7,228,432],[67,590,432],[74,426,432],[49,500,432,4544]]'
     ↑ Thêm NPC Đường Tăng (ID 49) tại X=500, Y=432
```

**Bước 5:** Save, restart server

**Bước 6:** NPC Đường Tăng xuất hiện tại vị trí đó! ✅

---

**CHÚC BẠN THIẾT KẾ MAP VUI VẺ!** 🎨
