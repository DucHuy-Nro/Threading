# 📝 CODE CẦN SỬA (COPY & PASTE)

## 🎯 **3 FILE CẦN SỬA:**

---

## ✏️ **FILE 1: ConstNpc.java**

**Đường dẫn:** `src/nro/models/consts/ConstNpc.java`

**Tìm dòng:**
```java
public static final byte XE_NUOC_MIA = 84;
//----------------------index menu------------------------------------------
```

**THÊM DÒNG NÀY giữa 2 dòng trên:**
```java
public static final byte XE_NUOC_MIA = 84;
public static final byte ADMIN_PANEL = 85;  // ← COPY DÒNG NÀY
//----------------------index menu------------------------------------------
```

---

## ✏️ **FILE 2: NpcFactory.java** 

**Đường dẫn:** `src/nro/models/npc/NpcFactory.java`

### **Sửa chỗ 1: Thêm import (dòng ~90-95)**

**Tìm:**
```java
import nro.models.npc_list.Berry;
import nro.models.npc_list.ChiChi;
import nro.models.npc_list.DrMyuu;
```

**THÊM DÒNG NÀY:**
```java
import nro.models.npc_list.Berry;
import nro.models.npc_list.ChiChi;
import nro.models.npc_list.AdminPanel;  // ← COPY DÒNG NÀY
import nro.models.npc_list.DrMyuu;
```

### **Sửa chỗ 2: Thêm case xử lý (dòng ~237-240)**

**Tìm:**
```java
                case ConstNpc.BERRY ->
                    new Berry(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.SGOHAN ->
                    new SGohan(mapId, status, cx, cy, tempId, avatar);
                default ->
```

**THÊM 2 DÒNG NÀY:**
```java
                case ConstNpc.BERRY ->
                    new Berry(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.ADMIN_PANEL ->                              // ← COPY
                    new AdminPanel(mapId, status, cx, cy, tempId, avatar); // ← DÒNG NÀY
                case ConstNpc.SGOHAN ->
                    new SGohan(mapId, status, cx, cy, tempId, avatar);
                default ->
```

---

## ✏️ **FILE 3: AdminPanel.java**

**Đường dẫn:** `src/nro/models/npc_list/AdminPanel.java`

**File này đã được tạo sẵn tại:**
```
/workspace/src/nro/models/npc_list/AdminPanel.java
```

**KHÔNG CẦN SỬA GÌ!** File đã hoàn chỉnh! ✅

---

## 📋 **TÓM TẮT NHANH:**

| File | Thêm gì | Dòng |
|------|---------|------|
| **ConstNpc.java** | `public static final byte ADMIN_PANEL = 85;` | ~157 |
| **NpcFactory.java** | `import nro.models.npc_list.AdminPanel;` | ~90 |
| **NpcFactory.java** | `case ConstNpc.ADMIN_PANEL → new AdminPanel(...)` | ~238 |
| **AdminPanel.java** | ✅ Đã tạo sẵn | - |

---

## 🚀 **SAU KHI SỬA CODE:**

```bash
# 1. Build
ant clean && ant jar

# 2. Run
run.bat

# 3. Test
# - Login với account admin
# - Đi map 5 (Đảo Kamê)
# - Click NPC tại (500, 300)
# - Sẽ thấy Admin Panel!
```

---

## ⚠️ **LƯU Ý:**

1. **NPC ID 85** - Nếu ID này đã dùng, đổi thành 86, 87, 88... (max 94)
2. **Tọa độ (500, 300)** - Có thể đổi tọa độ khác
3. **is_admin = 1** - Nhớ set admin cho account!

---

**BUILD VÀ ENJOY ADMIN PANEL!** 🎮✨
