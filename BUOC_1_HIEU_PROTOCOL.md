# 📡 BƯỚC 1: HIỂU CÁCH UNITY CLIENT VÀ JAVA SERVER "NÓI CHUYỆN"

## 🎯 **TỔNG QUAN:**

```
Unity Client (C#)  ←──────→  NRO Server (Java)
     │                              │
     │    TCP Socket Connection     │
     │◄──────────────────────────►│
     │                              │
     │    Message Binary Format     │
     │◄──────────────────────────►│
```

---

## 📦 **MESSAGE FORMAT:**

### **Cấu trúc 1 Message:**

```
┌─────────┬──────────┬─────────────┐
│  Size   │ Command  │    Data     │
│ 2 bytes │  1 byte  │  N bytes    │
└─────────┴──────────┴─────────────┘
```

**Ví dụ: Gửi damage về client**

```
Server Java:
┌─────────┬──────────┬─────────────────┐
│  0x00   │   -54    │  damage (long)  │
│  0x08   │  (CMD)   │    8 bytes      │
└─────────┴──────────┴─────────────────┘

Client Unity đọc:
1. Đọc 2 bytes → Size = 8
2. Đọc 1 byte → CMD = -54
3. Đọc 8 bytes → damage (long)
```

---

## 🔍 **JAVA SERVER GỬI:**

### **File: Service.java (hoặc Controller.java)**

```java
// 1. Tạo message
Message msg = new Message(-54);  // CMD: Player attack mob

// 2. Ghi data
msg.writer().writeLong(damage);  // Ghi 8 bytes
msg.writer().writeInt(mobId);    // Ghi 4 bytes

// 3. Gửi cho client
player.sendMessage(msg);
```

**Byte sequence:**
```
[00 08] [-54] [00 00 00 0E 00 00 00 00] [00 00 00 01]
 ↑       ↑              ↑                    ↑
Size    CMD      damage=60000000000        mobId=1
```

---

## 🔍 **UNITY CLIENT NHẬN:**

### **File: Controller.cs (hoặc MessageHandler.cs)**

```csharp
// 1. Nhận message từ server
byte[] data = socket.Receive();

// 2. Parse message
Message msg = new Message(cmd, data);

// 3. Đọc data
long damage = msg.reader().readLong();  // Đọc 8 bytes
int mobId = msg.reader().readInt();     // Đọc 4 bytes

// 4. Xử lý
if (cmd == -54) {
    // Player attack mob
    showDamage(damage);
}
```

---

## ⚠️ **VẤN ĐỀ HIỆN TẠI:**

### **Khi checkTypeData = TRUE (hiện tại):**

```
Java Server gửi:
msg.writer().writeInt(damage);  // 4 bytes
└─ [00 00 00 00 7F FF FF FF]  (2,147,483,647)

Unity Client đọc:
long damage = msg.readLong3Byte();
├─ if (checkTypeData == true)
│   └─ return dis.readInt();    // Đọc 4 bytes ✅
└─ Damage = 2,147,483,647
```

### **Khi checkTypeData = FALSE (muốn 60 tỷ):**

```
Java Server gửi:
msg.writer().writeInt(damage);  // 4 bytes (SAI!)
└─ [00 00 00 00 7F FF FF FF]

Unity Client đọc:
long damage = msg.readLong3Byte();
├─ if (checkTypeData == false)
│   └─ return dis.readLong();   // Đọc 8 bytes ❌
└─ Đọc sai! (thiếu 4 bytes)
```

**→ PHẢI ĐỔI SERVER GỬI LONG:**

```
Java Server gửi:
msg.writer().writeLong(damage);  // 8 bytes ✅
└─ [00 00 00 00 0E 00 00 00 00]  (60,000,000,000)

Unity Client đọc:
long damage = msg.readLong3Byte();
├─ if (checkTypeData == false)
│   └─ return dis.readLong();   // Đọc 8 bytes ✅
└─ Damage = 60,000,000,000 ✅
```

---

## 📊 **BẢNG ĐỐI CHIẾU:**

| Data Type | Java | Unity C# | Bytes | Range |
|-----------|------|----------|-------|-------|
| byte | byte | sbyte | 1 | -128 to 127 |
| short | short | short | 2 | -32,768 to 32,767 |
| int | int | int | 4 | -2.1 tỷ to 2.1 tỷ |
| long | long | long | 8 | -9 tỷ tỷ to 9 tỷ tỷ |

---

## 🔧 **PHẢI LÀM GÌ:**

### **1. Unity Client (C#):**

```csharp
// File: HM9r329.cs
internal static bool checkTypeData = false;  // BẬT LONG MODE
```

```csharp
// File: Message.cs (đã có sẵn)
public long readLong3Byte()
{
    if (HM9r329.checkTypeData) 
        return dis.readInt();   // 4 bytes
    else 
        return dis.readLong();  // 8 bytes ✅
}
```

### **2. Java Server:**

```java
// File: Service.java
// TÌM TẤT CẢ CHỖ GỬI DAMAGE

// CŨ:
msg.writer().writeInt(damage);  // 4 bytes ❌

// MỚI:
msg.writer().writeLong(damage); // 8 bytes ✅
```

---

## 🎯 **CÁCH TÌM CHỖ CẦN SỬA:**

### **Trong Java Server:**

```bash
# Tìm tất cả chỗ gửi damage/dame
grep -rn "writeInt" src/ | grep -i "damage\|dame"

# Output ví dụ:
# src/.../Service.java:1234: msg.writer().writeInt(damage);
# src/.../Mob.java:567: msg.writer().writeInt(dame);
```

**CHỖ NÀO CÓ `writeInt(damage/dame)` → ĐỔI THÀNH `writeLong`**

---

## 📋 **CHECKLIST TÌM CHỖ CẦN SỬA:**

```
☐ Service.java - Gửi damage tổng quát
☐ PlayerService.java - Player damage
☐ Mob.java - Mob attack damage
☐ Player.java - Player injured
☐ Boss.java (các file boss) - Boss damage
☐ PvpService.java - PVP damage
☐ SkillService.java - Skill damage
```

---

## 🔍 **VÍ DỤ CỤ THỂ:**

### **Tìm trong Service.java:**

```java
// Tìm method gửi damage về client
public void sendPlayerAttack(Player player, Mob mob, long damage) {
    Message msg = new Message(-54);
    msg.writer().writeInt((int)damage);  // ← TÌM THẤY! ĐỔI THÀNH writeLong
    msg.writer().writeInt(mob.id);
    player.sendMessage(msg);
}
```

**ĐỔI THÀNH:**

```java
public void sendPlayerAttack(Player player, Mob mob, long damage) {
    Message msg = new Message(-54);
    msg.writer().writeLong(damage);  // ✅ ĐÃ ĐỔI THÀNH LONG!
    msg.writer().writeInt(mob.id);
    player.sendMessage(msg);
}
```

---

## 🎯 **CÁC CMD (COMMAND) QUAN TRỌNG:**

| CMD | Ý nghĩa | Có damage? |
|-----|---------|-----------|
| -54 | Player attack mob | ✅ CÓ |
| -55 | Mob attack player | ✅ CÓ |
| -56 | Player attack player (PVP) | ✅ CÓ |
| -44 | Update inventory | ❌ |
| -30 | Update player info | ❌ |
| -41 | Chat message | ❌ |

**CHỈ CẦN SỬA CÁC CMD CÓ DAMAGE!**

---

## 🔧 **CÔNG CỤ HỖ TRỢ:**

### **A. Tìm bằng IDE:**

**Eclipse/IntelliJ:**
```
Ctrl+Shift+F → Search in Project
Tìm: "writeInt"
Filter: "damage" hoặc "dame"
```

**Visual Studio Code:**
```
Ctrl+Shift+F → Search
Tìm: writeInt.*damage
Regex: ON
```

### **B. Tìm bằng command line:**

```bash
cd /workspace/Threading/src

# Tìm writeInt + damage
grep -rn "writeInt" . | grep -i damage

# Lưu kết quả
grep -rn "writeInt" . | grep -i damage > result.txt
```

---

## 📊 **KẾT QUẢ EXPECTED:**

### **Trước khi sửa:**

```
Java Server:
- dame: int (max 2.1 tỷ)
- gửi: writeInt(damage)  // 4 bytes

Unity Client:
- checkTypeData: true
- đọc: readInt()  // 4 bytes
- hiển thị: max 2.1 tỷ
```

### **Sau khi sửa:**

```
Java Server:
- dame: long (max 9 tỷ tỷ)
- gửi: writeLong(damage)  // 8 bytes ✅

Unity Client:
- checkTypeData: false
- đọc: readLong()  // 8 bytes ✅
- hiển thị: 60-70 tỷ ✅
```

---

## 🎯 **BƯỚC TIẾP THEO:**

Sau khi hiểu, chúng ta sẽ:

1. **Bước 2:** Sửa Unity client config
2. **Bước 3:** Build Unity client
3. **Bước 4:** Tìm và sửa Java server
4. **Bước 5:** Build Java server
5. **Bước 6:** Test

---

**SẴN SÀNG SANG BƯỚC 2?** 🚀

Hoặc cần tôi giải thích thêm phần nào? 🤔