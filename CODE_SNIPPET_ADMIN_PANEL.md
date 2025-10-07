# 📝 CODE SNIPPET - ADMIN PANEL

## 🔧 **CÁC FILE CẦN SỬA:**

---

## **FILE 1: ConstNpc.java**

**Đường dẫn:** `src/nro/models/consts/ConstNpc.java`

**Tìm dòng ~156, THÊM:**

```java
    public static final byte XE_NUOC_MIA = 84;
    public static final byte ADMIN_PANEL = 85;  // ← THÊM DÒNG NÀY
    //----------------------index menu------------------------------------------
```

---

## **FILE 2: NpcFactory.java**

**Đường dẫn:** `src/nro/models/npc/NpcFactory.java`

### **2a. THÊM IMPORT (dòng ~95):**

```java
import nro.models.npc_list.Berry;
import nro.models.npc_list.ChiChi;
import nro.models.npc_list.DrMyuu;
import nro.models.npc_list.DuaHau;
import nro.models.npc_list.RuongSuuTam;
import nro.models.npc_list.ToriBot;
import nro.models.npc_list.SGohan;
import nro.models.npc_list.AdminPanel;  // ← THÊM DÒNG NÀY
```

### **2b. THÊM CASE (dòng ~239):**

**Tìm:**
```java
                case ConstNpc.BERRY ->
                    new Berry(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.SGOHAN ->
                    new SGohan(mapId, status, cx, cy, tempId, avatar);
                default ->
```

**Thêm TRƯỚC `default ->`:**

```java
                case ConstNpc.BERRY ->
                    new Berry(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.SGOHAN ->
                    new SGohan(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.ADMIN_PANEL ->                              // ← THÊM
                    new AdminPanel(mapId, status, cx, cy, tempId, avatar); // ← DÒNG NÀY
                default ->
```

---

## **FILE 3: AdminPanel.java**

**Đường dẫn:** `src/nro/models/npc_list/AdminPanel.java`

**File này ĐÃ TẠO SẴN RỒI!** ✅

Bạn không cần sửa gì, chỉ cần đảm bảo file tồn tại tại:
```
src/nro/models/npc_list/AdminPanel.java
```

---

## 📊 **CHECKLIST TRƯỚC KHI BUILD:**

Kiểm tra từng file:

### **✅ ConstNpc.java:**
```java
// Phải có dòng này:
public static final byte ADMIN_PANEL = 85;
```

### **✅ NpcFactory.java:**
```java
// Phải có import:
import nro.models.npc_list.AdminPanel;

// Phải có case:
case ConstNpc.ADMIN_PANEL ->
    new AdminPanel(mapId, status, cx, cy, tempId, avatar);
```

### **✅ AdminPanel.java:**
```java
// File phải tồn tại tại:
// src/nro/models/npc_list/AdminPanel.java

// Class phải có:
public class AdminPanel extends Npc {
    // ... code
}
```

---

## 🚀 **BUILD COMMANDS:**

```bash
# Windows CMD:
cd "E:\Source NRO by me\Threading"
ant clean
ant jar

# Nếu server đang chạy:
taskkill /F /IM java.exe
ant clean && ant jar
run.bat
```

---

## 🎮 **TEST SCRIPT:**

### **Test 1: Kiểm tra NPC hiển thị**
```
1. Login game
2. Đi map ID 5 (Đảo Kamê)
3. Tìm NPC tại (500, 300)
4. Click NPC
   → Nếu admin: Thấy menu ✅
   → Nếu không: "Truy cập bị từ chối" ✅
```

### **Test 2: Kiểm tra Bảo trì 20s**
```
1. Click NPC → "Bảo trì 20s"
2. Confirm "Đồng ý"
3. Thông báo phải xuất hiện:
   - "Server sẽ bảo trì sau 20 giây"
   - "Server sẽ bảo trì sau 19 giây"
   - ...
4. Sau 20s → Server tắt ✅
```

### **Test 3: Kiểm tra EXP**
```
1. Click NPC → "Thay đổi EXP"
2. Chọn "x10"
3. Thông báo: "EXP đã thay đổi từ x1 → x10"
4. Giết 1 con mob
   → EXP nhận được phải x10 ✅
```

---

## ⚠️ **LƯU Ý:**

### **Khi thay đổi EXP:**
- ✅ Áp dụng NGAY cho tất cả player
- ✅ KHÔNG cần restart server
- ✅ KHÔNG lưu vào database (reset khi restart)

### **Nếu muốn lưu EXP rate:**
```java
// Thêm vào code sau khi đổi EXP:
Config.properties.setProperty("server.expserver", String.valueOf(newRate));
// Lưu file Config.properties
```

---

**CODE ĐÃ HOÀN CHỈNH! CHỈ CẦN COPY-PASTE VÀ BUILD!** ✅
