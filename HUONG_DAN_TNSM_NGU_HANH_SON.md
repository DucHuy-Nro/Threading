# 📊 FIX TNSM NGŨ HÀNH SƠN

## ✅ **ĐÃ SỬA XONG!**

---

## 🔴 **VẤN ĐỀ:**

**Map Ngũ Hành Sơn (122, 123, 124) có TNSM THẤP hơn map khác!**

### **Nguyên nhân:**

File: `src/nro/models/mob/Mob.java` (dòng 246-248)

```java
// CODE CŨ - BỊ COMMENT:
if (pl.zone.map.mapId == 122 || pl.zone.map.mapId == 123 || pl.zone.map.mapId == 124) {
    //tiemNang *= 2;  // ← BỊ TẮT!!!
}
```

**→ Chức năng nhân x2 TNSM đã bị TẮT!**

---

## ✅ **GIẢI PHÁP ĐÃ ÁP DỤNG:**

### **Bật lại x2 TNSM:**

```java
// CODE MỚI:
if (pl.zone.map.mapId == 122 || pl.zone.map.mapId == 123 || pl.zone.map.mapId == 124) {
    tiemNang *= 2; // x2 TNSM cho Ngũ Hành Sơn
}
```

**→ Giờ TNSM = 2 lần so với map thường!**

---

## 📈 **KẾT QUẢ:**

### **Trước khi fix:**
```
Player đánh quái map 122:
- Dame: 10,000
- TNSM nhận: 500

Player đánh quái map thường:
- Dame: 10,000  
- TNSM nhận: 500

→ Giống nhau!
```

### **Sau khi fix:**
```
Player đánh quái map 122-124:
- Dame: 10,000
- TNSM nhận: 1,000  ← x2!

Player đánh quái map thường:
- Dame: 10,000
- TNSM nhận: 500

→ Map Ngũ Hành Sơn tăng x2!
```

---

## 🎯 **CÁC TÙY CHỌN KHÁC:**

### **OPTION 1: Tăng x3 thay vì x2**

```java
if (pl.zone.map.mapId == 122 || pl.zone.map.mapId == 123 || pl.zone.map.mapId == 124) {
    tiemNang *= 3; // x3 TNSM
}
```

---

### **OPTION 2: Mỗi map khác nhau**

```java
if (pl.zone.map.mapId == 122) {
    tiemNang *= 2; // Map 122: x2
} else if (pl.zone.map.mapId == 123) {
    tiemNang *= 3; // Map 123: x3
} else if (pl.zone.map.mapId == 124) {
    tiemNang *= 4; // Map 124: x4
}
```

---

### **OPTION 3: Điều kiện theo power**

```java
if (pl.zone.map.mapId == 122 || pl.zone.map.mapId == 123 || pl.zone.map.mapId == 124) {
    if (pl.nPoint.power < 16_000_000_000L) {
        tiemNang *= 2; // x2 cho player dưới 16 tỷ
    } else {
        tiemNang *= 1; // x1 cho player trên 16 tỷ
    }
}
```

---

## 📊 **CÔNG THỨC TÍNH TNSM ĐẦY ĐỦ:**

```java
// Bước 1: Tính TNSM cơ bản
tiemNang = dame + (HP_mob * 0.0005)

// Bước 2: Kiểm tra level
if (levelPlayer > levelMob + 5) {
    tiemNang = 1  // Quá cao level
} else {
    tiemNang /= (checkLevel * 0.5) + 1.25
}

// Bước 3: Áp dụng % từ đồ
tiemNang = tiemNang * (1 + %TNSM_từ_đồ)

// Bước 4: Map Ngũ Hành Sơn (MỚI THÊM)
if (map 122-124) {
    tiemNang *= 2  // x2!
}
```

---

## 🔨 **BUILD & TEST:**

```bash
# Build
ant clean && ant jar

# Chạy server
run.bat
```

---

## 🧪 **CÁCH TEST:**

### **1. Vào Map Ngũ Hành Sơn (122, 123, 124)**

### **2. Đánh quái**

### **3. Check TNSM nhận được**

**Kết quả:** TNSM tăng **GẤP ĐÔI** so với trước! ✅

---

## ⚠️ **LƯU Ý:**

### **1. Ảnh hưởng:**
- ✅ Map 122, 123, 124: TNSM tăng x2
- ❌ Map khác: KHÔNG ảnh hưởng

### **2. Điều chỉnh theo ý muốn:**
- Muốn x3? Đổi `tiemNang *= 2` thành `tiemNang *= 3`
- Muốn x5? Đổi thành `tiemNang *= 5`
- Muốn x10? Đổi thành `tiemNang *= 10`

### **3. Cân bằng game:**
```
Map thường: x1 TNSM
Map Ngũ Hành Sơn: x2 TNSM  ← Vừa phải
Map Doanh Trại: x? TNSM (check riêng)
```

---

## 🎉 **HOÀN THÀNH!**

**Map Ngũ Hành Sơn giờ có TNSM x2!**

Build và test ngay! 🚀

---

## 📝 **TÓM TẮT:**

- ✅ **Đã fix:** Bật lại code nhân x2 TNSM
- ✅ **File sửa:** `src/nro/models/mob/Mob.java`
- ✅ **Map áp dụng:** 122, 123, 124
- ✅ **Kết quả:** TNSM tăng gấp đôi!

**BUILD NGAY ĐỂ PLAYER NHẬN X2 TNSM!** ⚡
