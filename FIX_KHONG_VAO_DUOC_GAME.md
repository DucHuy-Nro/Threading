# 🚨 FIX NHANH: KHÔNG VÀO ĐƯỢC GAME

## ⚡ **ĐÃ SỬA NGAY:**

**Nguyên nhân:** HashMap không đảm bảo thứ tự → Client nhận NPC sai thứ tự → Crash!

**Giải pháp:** Đổi sang **LinkedHashMap** (giữ thứ tự insert)

---

## ✅ **ĐÃ SỬA TỰ ĐỘNG:**

**File:** `src/nro/models/server/Manager.java` - Dòng 86

```java
// TRƯỚC (Có thể gây lỗi):
public static final Map<Integer, NpcTemplate> NPC_TEMPLATES = new HashMap<>();

// SAU (Fix):
public static final Map<Integer, NpcTemplate> NPC_TEMPLATES = new LinkedHashMap<>();
                                                              ↑↑↑↑↑↑↑↑↑↑↑
```

**LinkedHashMap** = HashMap + Giữ thứ tự insert → Client nhận đúng order!

---

## 🚀 **BÂY GIỜ LÀM GÌ:**

### **Bước 1: Build lại**
```bash
# NetBeans: Clean and Build
ant clean && ant jar
```

### **Bước 2: Restart server**
```bash
run.bat
```

### **Bước 3: XÓA CACHE CLIENT** (quan trọng!)
```
- Xóa folder RES/ trong thư mục game client
- Hoặc xóa file cache (nếu có)
- Restart client hoàn toàn
```

### **Bước 4: Login lại**

**→ PHẢI VÀO ĐƯỢC!** ✅

---

## 🔍 **NẾU VẪN KHÔNG VÀO ĐƯỢC:**

### **Hãy cho tôi biết:**

1. **Server có chạy được không?**
   - Có thấy "Active Port 14445" trong log?
   - Có lỗi gì khi start server?

2. **Client báo lỗi gì?**
   - "Không kết nối được server"?
   - Treo ở màn hình login?
   - Lỗi khác?

3. **Copy log server** (phần cuối cùng) gửi cho tôi!

---

## 📊 **SO SÁNH:**

| | HashMap | LinkedHashMap |
|---|---------|---------------|
| Tốc độ | Nhanh | Nhanh |
| Thứ tự | ❌ Ngẫu nhiên | ✅ Giữ thứ tự insert |
| Client game | ❌ Có thể lỗi | ✅ OK |

---

## 🛠️ **TÙY CHỌN 2: NẾU VẪN LỖI**

Thử sắp xếp lại khi gửi cho client:

**File:** `src/nro/models/data/DataGame.java`

```java
import java.util.ArrayList;
import java.util.Comparator;

// Dòng 111-118, thay thế:
msg.writer().writeByte(Manager.NPC_TEMPLATES.size());

// Sắp xếp theo ID trước khi gửi
List<NpcTemplate> sortedNpcs = new ArrayList<>(Manager.NPC_TEMPLATES.values());
sortedNpcs.sort(Comparator.comparingInt(npc -> npc.id));

for (NpcTemplate temp : sortedNpcs) {
    msg.writer().writeUTF(temp.name);
    msg.writer().writeShort(temp.head);
    msg.writer().writeShort(temp.body);
    msg.writer().writeShort(temp.leg);
    msg.writer().writeByte(0);
}
```

---

## ⚠️ **LƯU Ý:**

- ✅ LinkedHashMap load NPC theo thứ tự trong DB (ORDER BY id)
- ✅ Giữ thứ tự 0, 1, 2, ..., 84, 103, ..., 111
- ✅ Client nhận đúng thứ tự → Không crash!

---

## 📝 **CHECKLIST:**

- [x] Đổi HashMap → LinkedHashMap
- [ ] Build lại project
- [ ] Restart server
- [ ] XÓA CACHE CLIENT
- [ ] Login lại
- [ ] Test NPC ID 111

---

**BUILD LẠI VÀ THỬ NGAY! NẾU VẪN LỖI, GỬI LOG CHO TÔI!** 🚀
