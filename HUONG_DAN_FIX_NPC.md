# 🔧 HƯỚNG DẪN FIX LỖI NPC INDEX OUT OF BOUNDS

## ❌ LỖI BẠN GẶP PHẢI:
```
java.lang.IndexOutOfBoundsException: Index 111 out of bounds for length 94
```

**Nguyên nhân:** Bạn đã thêm NPC vào map với `tempId = 111`, nhưng trong database chỉ có 93 NPC templates (index 0-92).

---

## ✅ GIẢI PHÁP:

### **Cách 1: SỬA LẠI MAP DATA (KHUYẾN NGHỊ) ⭐**

1. **Mở Navicat → Table `map_template`**

2. **Tìm map có NPC tempId = 111** (có thể là map ID 111 "Đông Nam Karin")

3. **Xem cột `data`** - cột này chứa thông tin NPC dưới dạng JSON:
   ```json
   Ví dụ: [[npc_tempId, status, x, y], [npc_tempId, status, x, y]]
   ```

4. **Sửa tempId từ 111 → một ID HỢP LỆ**

   **Các NPC Template ID HỢP LỆ:**
   - `0-84`: Ông Gohan (0), Bulma (7), Dende (8), Appule (9), Dr.Brief (10), Quy Lão Kame (13), v.v.
   - `103-110`: Chú Bé Đần (103), Khá Bảnh (104), Tiến Bry (105), v.v.

   **Ví dụ sửa:**
   ```json
   // SAI - tempId = 111 không tồn tại
   [[111, 1, 300, 400]]
   
   // ĐÚNG - tempId = 7 (Bulma)
   [[7, 1, 300, 400]]
   ```

5. **Save và Restart server**

---

### **Cách 2: THÊM NPC TEMPLATE MỚI ID = 111**

Nếu bạn thực sự muốn tạo NPC mới với ID 111:

1. **Mở Navicat → Table `npc_template`**

2. **Thêm record mới:**
   ```sql
   INSERT INTO `npc_template` VALUES (
       111,                    -- id
       'Tên NPC Của Bạn',     -- NAME
       18,                     -- head (sprite head ID)
       19,                     -- body (sprite body ID)  
       20,                     -- leg (sprite leg ID)
       349                     -- avatar (icon ID)
   );
   ```

3. **QUAN TRỌNG:** Bạn cũng cần **thêm code xử lý** cho NPC này:

   - **File:** `src/nro/models/npc/NpcFactory.java`
   - **Thêm constant:** `src/nro/models/consts/ConstNpc.java`
   
   Ví dụ:
   ```java
   // ConstNpc.java
   public static final byte TEN_NPC_MOI = 111;
   
   // NpcFactory.java - trong switch case
   case ConstNpc.TEN_NPC_MOI ->
       new TenNpcMoi(mapId, status, cx, cy, tempId, avatar);
   ```

4. **Tạo class NPC mới** tại `src/nro/models/npc_list/TenNpcMoi.java`

---

### **Cách 3: TÌM VÀ SỬA NHANH BẰNG SQL**

Chạy câu lệnh sau để tìm map nào đang dùng tempId = 111:

```sql
-- Tìm tất cả map có NPC tempId = 111
SELECT id, NAME, data 
FROM map_template 
WHERE data LIKE '%\[\[111,%' 
   OR data LIKE '%,[111,%'
   OR data LIKE '%\[111,%';
```

Sau đó sửa cột `data` của map đó.

---

## 📋 DANH SÁCH NPC TEMPLATE ID HỢP LỆ:

| ID | Tên NPC | Avatar |
|----|---------|--------|
| 0 | Ông Gôhan | 349 |
| 1 | Ông Paragus | 348 |
| 2 | Ông Moori | 347 |
| 7 | Bunma | 562 |
| 8 | Dende | 350 |
| 9 | Appule | 565 |
| 10 | Dr. Brief | 7184 |
| 13 | Quy Lão Kame | 564 |
| 14 | Trưởng lão Guru | 566 |
| 15 | Vua Vegeta | 563 |
| ... | ... | ... |
| 103 | Chú Bé Đần | 7775 |
| 104 | Khá BảnH | 15273 |
| 105 | Tiến Bry | 15272 |
| 106 | Bulma Tết | 10477 |
| 107 | Bill Bí Ngô | 7016 |
| 108 | Heart | 16165 |
| 109 | Bulma Bunny | 4119 |
| 110 | Bunma Rực Rỡ | 16269 |

**⚠️ KHÔNG DÙNG ID: 85-102, 111+ (không tồn tại!)**

---

## 🚀 SAU KHI SỬA:

1. **Lưu thay đổi trong database**
2. **Restart server**
3. **Kiểm tra log** - không còn lỗi IndexOutOfBounds

---

## 💡 MẸO:

Khi thêm NPC vào map trong tương lai:
- ✅ **LUÔN kiểm tra** NPC template ID có tồn tại trong `npc_template` table
- ✅ **Dùng ID từ 0-84 hoặc 103-110**
- ❌ **KHÔNG dùng** ID 85-102, 111+

---

Chúc bạn fix thành công! 🎉
