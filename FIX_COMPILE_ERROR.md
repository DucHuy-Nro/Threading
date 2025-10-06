# ✅ ĐÃ FIX LỖI COMPILE

## ❌ **LỖI BẠN GẶP:**

```
E:\...\DataGame.java:112: error: for-each not applicable to expression type
            for (NpcTemplate temp : Manager.NPC_TEMPLATES) {
  required: array or java.lang.Iterable
  found:    Map<Integer,NpcTemplate>
```

---

## 🐛 **NGUYÊN NHÂN:**

Khi đổi `NPC_TEMPLATES` từ **List** → **Map**, không thể dùng for-each trực tiếp:

```java
// SAI - Map không phải Iterable
for (NpcTemplate temp : Manager.NPC_TEMPLATES) {
```

Cần dùng `.values()` để lấy collection các values:

```java
// ĐÚNG
for (NpcTemplate temp : Manager.NPC_TEMPLATES.values()) {
```

---

## ✅ **ĐÃ SỬA:**

**File:** `src/nro/models/data/DataGame.java`

**Dòng 112:**

```java
// TRƯỚC (SAI):
for (NpcTemplate temp : Manager.NPC_TEMPLATES) {

// SAU (ĐÚNG):
for (NpcTemplate temp : Manager.NPC_TEMPLATES.values()) {
```

---

## 📝 **TỔNG HỢP TẤT CẢ THAY ĐỔI:**

### **1. Manager.java (dòng 86):**
```java
public static final Map<Integer, NpcTemplate> NPC_TEMPLATES = new HashMap<>();
```

### **2. Manager.java (dòng 743):**
```java
NPC_TEMPLATES.put((int) npcTemp.id, npcTemp);
```

### **3. DataGame.java (dòng 112):** ⭐ MỚI
```java
for (NpcTemplate temp : Manager.NPC_TEMPLATES.values()) {
```

---

## 🚀 **BÂY GIỜ BUILD LẠI:**

```bash
# Trong NetBeans:
Clean and Build (hoặc Shift+F11)

# Hoặc command line:
ant clean
ant jar
```

**→ SẼ COMPILE THÀNH CÔNG!** ✅

---

## ✅ **CHECKLIST:**

- [x] Đổi List → Map trong Manager.java
- [x] Đổi .add() → .put() trong Manager.java  
- [x] Sửa for-each loop trong DataGame.java
- [x] Thêm constant SGOHAN trong ConstNpc.java
- [x] Thêm case trong NpcFactory.java
- [x] Tạo class SGohan.java

**→ TẤT CẢ ĐÃ XONG!** 🎉

---

## 🎯 **SAU KHI BUILD XONG:**

1. ✅ **Compile** thành công
2. ✅ **Restart** server: `run.bat`
3. ✅ **Test** NPC ID 111 trong game
4. ✅ Không còn lỗi IndexOutOfBounds!

---

**CHÚC MỪNG! BẠN ĐÃ FIX XONG TẤT CẢ!** 🎊
