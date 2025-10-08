# 📤 HƯỚNG DẪN UPLOAD UNITY PROJECT

## 🎯 MỤC ĐÍCH:

Tôi cần xem các file C# script để:
1. Hiểu protocol Unity client dùng
2. Tích hợp với NRO server (Java)
3. Sửa lỗi nếu có
4. Thêm tính năng

---

## 📋 CÁC FILE TÔI CẦN XEM:

### ⭐ PRIORITY 1 (Quan trọng nhất):

```
Scripts/Network/Message.cs          ← QUAN TRỌNG!
Scripts/Network/NetworkManager.cs   ← QUAN TRỌNG!
Scripts/Network/MessageHandler.cs
Scripts/Network/Session.cs
```

Đây là file xử lý kết nối và đọc/ghi message từ server.
**PHẢI CÓ** để tôi hiểu protocol!

### ⭐ PRIORITY 2:

```
Scripts/Player/Player.cs
Scripts/Player/Inventory.cs
Scripts/Player/Skill.cs
Scripts/Item/Item.cs
Scripts/Item/ItemTemplate.cs
```

### ⭐ PRIORITY 3:

```
Scripts/UI/InventoryUI.cs
Scripts/UI/ChatUI.cs
Scripts/Game/GameManager.cs
```

---

## 🚀 CÁCH UPLOAD NHANH:

### Option A: Upload từng file vào chat

```bash
# Mở file trong Visual Studio/Notepad++
# Copy toàn bộ code
# Paste vào chat như này:

"Đây là file Message.cs:"

```csharp
using System;
using System.IO;

public class Message {
    private MemoryStream ms;
    // ... code ...
}
```

```

### Option B: Upload vào workspace

**Bước 1:** Copy file vào thư mục
```
YourUnityProject/Assets/Scripts/Network/Message.cs
   ↓ COPY
/workspace/unity-client/Assets/Scripts/Network/Message.cs
```

**Bước 2:** Dùng File Explorer của Cursor
```
1. Bấm Ctrl+Shift+E (Explorer)
2. Right click folder "unity-client/Assets/Scripts"
3. Chọn "Reveal in File Explorer"
4. Paste files vào đây
```

**Bước 3:** Báo tôi
```
"Đã upload Message.cs vào unity-client/Assets/Scripts/Network/"
```

### Option C: Zip rồi upload

```bash
# Zip folder Scripts
Scripts.zip (5-50 MB)

# Upload vào workspace:
/workspace/unity-client/Scripts.zip

# Tôi sẽ extract:
unzip Scripts.zip -d unity-client/Assets/
```

---

## 🔍 TÔI SẼ TÌM GÌ?

### 1. Protocol Format:

```csharp
// Message.cs
public class Message {
    public void WriteInt(int value) {
        // Cách ghi int: Big Endian hay Little Endian?
        // 4 bytes
    }
    
    public int ReadInt() {
        // Cách đọc int
    }
    
    public void WriteShort(short value) {
        // Cách ghi short: 2 bytes
    }
}
```

### 2. Damage Type:

```csharp
// Player.cs
public class Player {
    public int dame;        // int hay long?
    public long dameg;      // int hay long?
}
```

### 3. Item Format:

```csharp
// Item.cs
public class Item {
    public int templateId;
    public int quantity;
    public List<ItemOption> options;
    
    // Có field đặc biệt không?
    public int customData;  // ← Có không?
    public int expireTime;  // ← Có không?
}
```

---

## 💡 NẾU BẠN KHÔNG BIẾT FILE NÀO QUAN TRỌNG:

Cứ upload **TẤT CẢ** file .cs trong folder Scripts/

Tôi sẽ tự tìm và đọc!

```bash
# List tất cả file .cs
dir /s /b *.cs > file_list.txt

# Upload file_list.txt cho tôi xem
# Tôi sẽ nói file nào cần upload
```

---

## 🎯 SAU KHI TÔI ĐỌC XONG:

Tôi sẽ:

✅ Phân tích protocol Unity client
✅ So sánh với NRO server (Java)
✅ Tìm điểm khác biệt
✅ Đề xuất giải pháp:
   - Sửa server cho khớp Unity client
   - Hoặc sửa Unity client cho khớp server
   - Hoặc tạo adapter layer
✅ Giúp tích hợp damage long (60-70 tỷ)
✅ Fix bugs nếu có

---

## ❓ CÂU HỎI:

### "Project Unity này có kết nối được với NRO server Java không?"

→ Cần kiểm tra protocol!

### "Làm sao Unity (C#) kết nối với Java server?"

→ Qua Socket TCP, Message binary format

### "Unity client này có hỗ trợ long damage không?"

→ Tôi sẽ kiểm tra khi đọc code!

---

**SẴN SÀNG NHẬN FILES!** 🚀

Bạn upload file nào cũng được, tôi sẽ đọc và phân tích!