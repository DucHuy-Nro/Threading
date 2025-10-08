# 🎯 HƯỚNG DẪN TÍCH HỢP UNITY CLIENT VỚI NRO SERVER (LONG DAMAGE)

## 📊 **TỔNG QUAN:**

Bạn có:
- ✅ NRO Server (Java) - Threading source
- ✅ Unity Client (C# mod) - Hỗ trợ LONG damage
- 🎯 Mục tiêu: Damage 60-70 tỷ hiển thị chính xác

---

## 🔍 **PHÂN TÍCH UNITY CLIENT:**

### **File: `HM9r329.cs` (Line 19)**

```csharp
internal static bool checkTypeData = true; //true là int, false là long
```

**Chế độ hiện tại:**
- `true` = Client đọc INT (max 2.147 tỷ)
- `false` = Client đọc LONG (max 9 tỷ tỷ)

### **File: `Message.cs`**

```csharp
public long readLong3Byte()
{
    if (HM9r329.checkTypeData) 
        return dis.readInt();   // Mode INT
    else 
        return dis.readLong();  // Mode LONG
}
```

**KẾT LUẬN:**
```
🔴 Client CÓ SẴN chức năng đọc LONG!
🔴 Chỉ cần BẬT bằng cách đổi checkTypeData = false
🔴 Server phải GỬI LONG để client đọc đúng
```

---

## 🛠️ **BƯỚC 1: SỬA UNITY CLIENT**

### **A. Đổi Config (Dễ nhất)**

**File:** `Scripts/Assembly-CSharp/HAIRMOD/Mod/HM9r329.cs`

```csharp
// CŨ (Line 19):
internal static bool checkTypeData = true; //true là int, false là long

// MỚI:
internal static bool checkTypeData = false; //true là int, false là long
```

**Chỉ cần đổi `true` → `false`!**

### **B. Build Unity Client**

**Trong Unity Editor:**
```
1. Mở Unity project
2. File → Build Settings
3. Chọn Platform:
   - PC: Windows/Mac/Linux
   - Android: Android (.apk)
4. Click "Build"
5. Đợi compile xong
6. Nhận được file:
   - PC: DragonBoy.exe
   - Android: DragonBoy.apk
```

**Hoặc dùng script build:**
```bash
# Windows
Unity.exe -quit -batchmode -executeMethod BuildScript.BuildWindows

# Android
Unity.exe -quit -batchmode -executeMethod BuildScript.BuildAndroid
```

---

## 🛠️ **BƯỚC 2: SỬA NRO SERVER (JAVA)**

### **File cần sửa:**

#### **A. NPoint.java - Dame types**

```java
// CŨ:
public int dame, dameg;
public int dameAdd;
public int dameAfter;

// MỚI:
public long dame, dameg;
public long dameAdd;
public long dameAfter;
```

#### **B. NPoint.java - getDameAttack()**

```java
// CŨ:
public int getDameAttack(boolean isAttackMob) {
    int dameAttack = this.dame;
    // ... tính toán ...
    if (dameAttack > 2_147_483_647) {
        dameAttack = 2_147_483_647;  // ← XÓA DÒNG NÀY
    }
    return (int) dameAttack;
}

// MỚI:
public long getDameAttack(boolean isAttackMob) {
    long dameAttack = this.dame;
    // ... tính toán ...
    // XÓA check 2.147 tỷ
    return dameAttack;  // Trả về long
}
```

#### **C. Skill.java - Skill damage%**

```java
// CŨ:
public short damage;  // MAX 32,767

// MỚI:
public int damage;    // MAX 2.147 tỷ
```

#### **D. Service.java - Gửi damage về client**

**TÌM TẤT CẢ CHỖ GỬI DAMAGE:**

```java
// CŨ:
msg.writer().writeInt(damage);     // Gửi 4 bytes (int)

// MỚI:
msg.writer().writeLong(damage);    // Gửi 8 bytes (long)
```

**Các file có thể cần sửa:**
```
src/nro/models/services/Service.java
src/nro/models/services/PlayerService.java
src/nro/models/mob/Mob.java
src/nro/models/player/Player.java
src/nro/models/boss/Boss.java (các boss)
```

#### **E. Xóa tất cả check 2.147 tỷ**

**Tìm và xóa:**
```java
if (damage > 2_147_483_647) {
    damage = 2_147_483_647;  // ← XÓA DÒNG NÀY
}
```

**Hoặc:**
```java
damage = Math.min(damage, 2_147_483_647);  // ← XÓA DÒNG NÀY
```

---

## 🔍 **BƯỚC 3: TÌM CHỖ GỬI DAMAGE**

### **Cách tìm nhanh:**

```bash
# Tìm tất cả chỗ gửi int (có thể là damage)
grep -r "writeInt" src/ | grep -i "damage\|dame\|hp"

# Tìm message gửi về client
grep -r "sendMessage" src/ | grep -i "damage\|attack"
```

### **Các CMD (command) liên quan:**

Trong NRO, message damage thường có CMD:
```java
// Damage từ player → mob
CMD: -54 (PLAYER_ATTACK_MOB)

// Damage từ mob → player
CMD: -55 (MOB_ATTACK_PLAYER)

// Damage từ player → player (PVP)
CMD: -56 (PLAYER_ATTACK_PLAYER)
```

**Tìm trong Service.java:**
```java
public void sendPlayerAttackMob(...) {
    Message msg = new Message(-54);
    msg.writer().writeInt(damage);  // ← ĐỔI THÀNH writeLong
    // ...
}
```

---

## 📋 **BƯỚC 4: KIỂM TRA PROTOCOL KHỚP**

### **Unity Client đọc:**

```csharp
// Message.cs
public long readLong3Byte()
{
    if (HM9r329.checkTypeData) 
        return dis.readInt();   // Đọc 4 bytes
    else 
        return dis.readLong();  // Đọc 8 bytes
}
```

### **Java Server gửi:**

```java
// Phải khớp với client!
if (checkTypeData == false) {
    msg.writer().writeLong(damage);  // Gửi 8 bytes
} else {
    msg.writer().writeInt(damage);   // Gửi 4 bytes
}
```

**⚠️ LƯU Ý:**
- Client đọc LONG (8 bytes) nhưng server gửi INT (4 bytes) → **LỖI!**
- Client đọc INT (4 bytes) nhưng server gửi LONG (8 bytes) → **LỖI!**

---

## 🎯 **BƯỚC 5: BUILD VÀ TEST**

### **A. Build Server**

```bash
cd /workspace/Threading
ant clean
ant build
```

### **B. Run Server**

```bash
run.bat
```

### **C. Kết nối Client**

```
1. Chạy Unity client đã build
2. Nhập IP server
3. Login
4. Test đánh mob
5. Xem damage hiển thị
```

### **D. Test Cases**

| Test | Dame gốc | Skill% | Expected | Result |
|------|----------|--------|----------|--------|
| 1 | 1 triệu | 3,000% | 30 triệu | ? |
| 2 | 10 triệu | 3,000% | 300 triệu | ? |
| 3 | 100 triệu | 3,000% | 3 tỷ | ? |
| 4 | 1 tỷ | 3,000% | 30 tỷ | ✅ |
| 5 | 2 tỷ | 3,000% | 60 tỷ | ✅ |

**Nếu Test 4, 5 hiện đúng → THÀNH CÔNG!** 🎉

---

## 🐛 **TROUBLESHOOTING:**

### **LỖI 1: Client vẫn hiện max 2.147 tỷ**

**Nguyên nhân:**
- Chưa đổi `checkTypeData = false`
- Chưa build lại client

**Fix:**
```csharp
// HM9r329.cs
internal static bool checkTypeData = false;  // ← PHẢI FALSE!
```

### **LỖI 2: Client disconnect/crash**

**Nguyên nhân:**
- Server gửi LONG nhưng client đọc INT
- Protocol không khớp

**Fix:**
- Kiểm tra lại client đã build với `checkTypeData = false`
- Kiểm tra server đã gửi `writeLong` đúng chỗ

### **LỖI 3: Damage hiển thị sai (số âm, số lẻ)**

**Nguyên nhân:**
- Byte order sai (Big Endian vs Little Endian)
- Đọc sai vị trí trong message

**Fix:**
- Check `myReader.cs` và `myWriter.cs`
- Đảm bảo cùng byte order

### **LỖI 4: Một số damage đúng, một số sai**

**Nguyên nhân:**
- Có chỗ gửi LONG, có chỗ gửi INT
- Thiếu sót khi sửa

**Fix:**
- Tìm TẤT CẢ chỗ gửi damage
- Đổi hết thành `writeLong`

---

## 📊 **CHECKLIST:**

### **Unity Client:**
```
☐ Đổi checkTypeData = false
☐ Build lại client (.exe hoặc .apk)
☐ Test client kết nối được server
```

### **NRO Server:**
```
☐ Đổi int → long cho dame, dameg, dameAdd
☐ Đổi short → int cho skill.damage
☐ Đổi getDameAttack() return long
☐ Xóa tất cả check 2.147 tỷ
☐ Tìm tất cả writeInt(damage) → writeLong(damage)
☐ Build lại server
```

### **Test:**
```
☐ Server start OK
☐ Client connect OK
☐ Login OK
☐ Đánh mob → Damage hiển thị
☐ Damage < 2 tỷ → hiển thị chính xác
☐ Damage > 2 tỷ → hiển thị chính xác (QUAN TRỌNG!)
☐ Damage 60-70 tỷ → hiển thị OK ✅
```

---

## 🎁 **BONUS: AUTO DETECT MODE**

Nếu muốn server tự động detect client mode:

```java
// Server.java
public class Session {
    public boolean useLongDamage = false;
    
    public void detectClientMode() {
        // Client gửi flag khi login
        if (clientVersion.contains("LONG")) {
            useLongDamage = true;
        }
    }
}

// Service.java
public void sendDamage(Player player, long damage) {
    Message msg = new Message(-54);
    
    if (player.session.useLongDamage) {
        msg.writer().writeLong(damage);  // Client LONG
    } else {
        msg.writer().writeInt((int)Math.min(damage, 2_147_483_647));  // Client INT
    }
    
    player.sendMessage(msg);
}
```

**Lợi ích:**
- Hỗ trợ cả 2 loại client
- Client cũ (INT) vẫn chơi được
- Client mới (LONG) thấy damage đúng

---

## 🎯 **KẾT LUẬN:**

```
UNITY CLIENT NÀY ĐÃ HỖ TRỢ LONG DAMAGE!
CHỈ CẦN:
1. Đổi checkTypeData = false
2. Build lại client
3. Sửa server gửi LONG
4. XONG!

DAMAGE 60-70 TỶ SẼ HIỂN THỊ CHÍNH XÁC! 🔥
```

---

## 📞 **CẦN GIÚP ĐỠ?**

Nếu gặp khó khăn ở bước nào, cho tôi biết:
- Bạn đang ở bước nào?
- Lỗi gì xảy ra?
- Log/screenshot?

Tôi sẽ giúp debug! 💪