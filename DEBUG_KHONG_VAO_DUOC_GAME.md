# 🔍 DEBUG: KHÔNG VÀO ĐƯỢC GAME

## ❓ **CÂU HỎI QUAN TRỌNG:**

Hãy trả lời các câu hỏi sau để tìm nguyên nhân:

### **1. Server có chạy được không?**
- [ ] Server **BẬT ĐƯỢC** nhưng client không kết nối được
- [ ] Server **BỊ CRASH** ngay khi start
- [ ] Server **KHÔNG CHẠY** được (lỗi ngay)

### **2. Lỗi gì hiện ra?**
- [ ] "Không thể kết nối đến server"
- [ ] "Server đang bảo trì"  
- [ ] Màn hình đen / treo
- [ ] Lỗi khác: _____________

### **3. Log server hiển thị gì?**
Hãy check file log hoặc console khi start server!

---

## 🔧 **CÁC NGUYÊN NHÂN THƯỜNG GẶP:**

### **A. Server bị crash khi load NPC templates**

**Triệu chứng:** Server tắt ngay sau khi start

**Nguyên nhân có thể:**
- Lỗi trong code `DataGame.java`
- HashMap order khác với client expect

**Cách fix:**

Có thể client mong đợi NPC templates theo **THỨ TỰ CỐ ĐỊNH**, nhưng HashMap không đảm bảo order!

**Thử fix 1: Dùng LinkedHashMap thay vì HashMap**

```java
// File: src/nro/models/server/Manager.java
// Dòng 86

// TRƯỚC:
public static final Map<Integer, NpcTemplate> NPC_TEMPLATES = new HashMap<>();

// SAU:
public static final Map<Integer, NpcTemplate> NPC_TEMPLATES = new LinkedHashMap<>();
```

**Import thêm:**
```java
import java.util.LinkedHashMap;
```

---

### **B. Client cache data cũ**

**Triệu chứng:** Client không load data mới

**Cách fix:**
1. **Xóa cache client:**
   - Folder `RES/` trong thư mục game
   - File cache (nếu có)
   
2. **Restart client hoàn toàn**

3. **Thử client khác / máy khác**

---

### **C. Thứ tự NPC bị lộn xộn**

**Triệu chứng:** Game load nhưng bị lỗi khi hiển thị NPC

**Nguyên nhân:** Client cần NPC templates theo thứ tự ID tăng dần

**Cách fix: Sắp xếp lại khi gửi cho client**

```java
// File: src/nro/models/data/DataGame.java
// Dòng 111-118

msg.writer().writeByte(Manager.NPC_TEMPLATES.size());

// SẮP XẾP THEO ID TRƯỚC KHI GỬI:
List<NpcTemplate> sortedNpcs = new ArrayList<>(Manager.NPC_TEMPLATES.values());
sortedNpcs.sort((a, b) -> Byte.compare(a.id, b.id));

for (NpcTemplate temp : sortedNpcs) {
    msg.writer().writeUTF(temp.name);
    msg.writer().writeShort(temp.head);
    msg.writer().writeShort(temp.body);
    msg.writer().writeShort(temp.leg);
    msg.writer().writeByte(0);
}
```

**Import thêm:**
```java
import java.util.ArrayList;
import java.util.List;
```

---

### **D. Lỗi NullPointerException**

**Triệu chứng:** Server crash với NPE

**Nguyên nhân:** Truy cập NPC không tồn tại

**Cách fix:** Thêm null check trong NpcFactory

```java
// File: src/nro/models/npc/NpcFactory.java
// Dòng 107

public static Npc createNPC(int mapId, int status, int cx, int cy, int tempId) {
    NpcTemplate template = Manager.NPC_TEMPLATES.get(tempId);
    
    // THÊM NULL CHECK:
    if (template == null) {
        Logger.error("NPC template không tồn tại: " + tempId);
        return null;
    }
    
    int avatar = template.avatar;
    // ... code tiếp theo
}
```

---

## 🚨 **CÁCH DEBUG NHANH:**

### **Bước 1: Kiểm tra log server**

Tìm file log hoặc console output, tìm các từ khóa:
- `Exception`
- `Error`
- `NullPointerException`
- `IndexOutOfBounds` (nếu vẫn còn)
- `NPC`

### **Bước 2: Test server standalone**

```bash
# Chạy server và xem log
run.bat

# Hoặc
java -jar dist/NgocRongOnline.jar
```

Nếu server chạy được, sẽ thấy:
```
✓ Successfully loaded npc template (94)
✓ Successfully loaded map template (...)
✓ Active Port 14445
```

### **Bước 3: Test với client cũ**

Nếu server chạy OK, thử:
1. Xóa cache client
2. Login lại
3. Xem lỗi gì

---

## 🔄 **FIX KHẨN CẤP: ROLLBACK**

Nếu cần khôi phục ngay:

### **Cách 1: Đổi về ArrayList (tạm thời)**

```java
// Manager.java - Dòng 86
public static final List<NpcTemplate> NPC_TEMPLATES = new ArrayList<>();

// Manager.java - Dòng 743
NPC_TEMPLATES.add(npcTemp);

// DataGame.java - Dòng 112
for (NpcTemplate temp : Manager.NPC_TEMPLATES) {

// NpcFactory.java - Dòng 107
int avatar = Manager.NPC_TEMPLATES.get(tempId).avatar;
// → SẼ LỖI VỚI ID 111! Phải xóa NPC ID 111 trong DB
```

**NHƯNG:** Vẫn bị lỗi IndexOutOfBounds với NPC ID 111!

### **Cách 2: Xóa NPC ID 111 khỏi database (tạm thời)**

```sql
-- Xóa khỏi npc_template
DELETE FROM npc_template WHERE id = 111;

-- Sửa map_template - xóa [111,1418,456]
-- Dùng Navicat sửa cột data của map ID 5
```

---

## ✅ **GIẢI PHÁP ĐỀ XUẤT:**

Hãy thử **THEO THỨ TỰ:**

1. **Kiểm tra log server** (quan trọng nhất!)
2. **Dùng LinkedHashMap** thay HashMap
3. **Sắp xếp NPC theo ID** khi gửi client
4. **Xóa cache client**
5. Nếu vẫn lỗi → Gửi log cho tôi!

---

## 📋 **CHECKLIST:**

- [ ] Server start được không?
- [ ] Có lỗi gì trong log?
- [ ] Client cache đã xóa chưa?
- [ ] Thử LinkedHashMap chưa?
- [ ] Thử sắp xếp NPC chưa?

---

**HÃY GỬI LOG SERVER CHO TÔI ĐỂ BIẾT CHÍNH XÁC LỖI GÌ!** 🔍
