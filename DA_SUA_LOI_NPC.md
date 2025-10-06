# ✅ ĐÃ SỬA LỖI NPC INDEX OUT OF BOUNDS

## 🐛 VẤN ĐỀ BẠN GẶP PHẢI:

**Lỗi:**
```
java.lang.IndexOutOfBoundsException: Index 111 out of bounds for length 94
```

**Nguyên nhân:**
- Code dùng **ArrayList** để lưu NPC templates
- Load tuần tự: `NPC_TEMPLATES.add(npcTemp)`
- Nhưng truy cập bằng **ID**: `NPC_TEMPLATES.get(tempId)`

**Vấn đề:**
```
Database IDs:    0, 1, 2, ..., 84, 103, 104, ..., 110, 111
ArrayList Index: 0, 1, 2, ..., 84,  85,  86, ...,  92,  93

get(111) → BOOM! (chỉ có 94 phần tử, index 0-93)
```

---

## ✅ ĐÃ SỬA:

**File:** `src/nro/models/server/Manager.java`

### **Thay đổi 1: Đổi từ ArrayList sang HashMap**

```java
// TRƯỚC (SAI):
public static final List<NpcTemplate> NPC_TEMPLATES = new ArrayList<>();

// SAU (ĐÚNG):
public static final Map<Integer, NpcTemplate> NPC_TEMPLATES = new HashMap<>();
```

### **Thay đổi 2: Dùng put() thay vì add()**

```java
// TRƯỚC (SAI):
NPC_TEMPLATES.add(npcTemp);

// SAU (ĐÚNG):
NPC_TEMPLATES.put((int) npcTemp.id, npcTemp);
```

---

## 🚀 CÁCH CHẠY:

### **1. Compile lại project:**

```bash
# Trên Windows:
ant clean
ant jar

# Hoặc build trong NetBeans:
# Right-click project → Clean and Build
```

### **2. Chạy server:**

```bash
# Windows
run.bat

# Linux
java -jar dist/NgocRongOnline.jar
```

### **3. Kiểm tra log:**

Nếu thành công, bạn sẽ thấy:
```
Successfully loaded npc template (94)
```

Và **KHÔNG CÒN** lỗi `IndexOutOfBoundsException`! ✅

---

## 📋 NPC TEMPLATE CỦA BẠN:

Bạn đã thêm vào database:
```sql
-- npc_template
111  SGohan  1761  1764  1765  15538

-- map_template (map ID 5 - Đảo Kamê)
NPC tại tọa độ: [111, 1418, 456]
```

Bây giờ NPC này sẽ hoạt động bình thường! 🎉

---

## ⚠️ LƯU Ý QUAN TRỌNG:

### **1. Cần thêm code xử lý cho NPC mới:**

NPC ID 111 hiện tại sẽ dùng **default handler** (không làm gì).

Nếu muốn NPC có chức năng, bạn cần:

#### **a) Thêm constant trong `ConstNpc.java`:**
```java
public static final byte SGOHAN = 111;
```

#### **b) Thêm case trong `NpcFactory.java`:**
```java
case ConstNpc.SGOHAN ->
    new SGohan(mapId, status, cx, cy, tempId, avatar);
```

#### **c) Tạo class NPC mới `src/nro/models/npc_list/SGohan.java`:**
```java
package nro.models.npc_list;

import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.services.Service;

public class SGohan extends Npc {

    public SGohan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            createOtherMenu(player, 0, 
                "Xin chào " + player.name + "!\nTa là SGohan, con có cần giúp gì không?",
                "Hướng dẫn", "Đóng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (select) {
                case 0: // Hướng dẫn
                    Service.gI().sendThongBao(player, 
                        "Đây là NPC SGohan mới!");
                    break;
            }
        }
    }
}
```

### **2. Nếu không thêm code:**

NPC sẽ hiển thị trên map nhưng khi click vào:
- Không có menu
- Không có dialog
- Chỉ đứng im ở đó

---

## 🎯 TÓM TẮT:

✅ **ĐÃ SỬA:** Đổi ArrayList → HashMap  
✅ **COMPILE:** Chạy `ant jar` hoặc Clean & Build  
✅ **RESTART:** Chạy lại server  
✅ **TEST:** Vào game, đến map "Đảo Kamê" (ID 5), kiểm tra NPC  

**→ NPC ID 111 bây giờ sẽ hiển thị và hoạt động!** 🎉

---

## 📞 NẾU VẪN CÓ LỖI:

1. **Kiểm tra database:**
   ```sql
   SELECT * FROM npc_template WHERE id = 111;
   SELECT * FROM map_template WHERE id = 5;
   ```

2. **Kiểm tra log server** khi start

3. **Đảm bảo đã compile lại** sau khi sửa code

4. **Restart server hoàn toàn**

---

Chúc bạn thành công! 🚀
