#!/bin/bash
# Script kiểm tra sau khi paste folder Scripts

echo "=== KIỂM TRA FOLDER SCRIPTS ==="
echo ""

echo "1. Kiểm tra folder tồn tại:"
if [ -d "/workspace/Scripts" ]; then
    echo "✅ Folder Scripts đã tồn tại!"
else
    echo "❌ Folder Scripts chưa có!"
    exit 1
fi

echo ""
echo "2. Đếm số file .cs:"
CS_COUNT=$(find /workspace/Scripts -name "*.cs" 2>/dev/null | wc -l)
echo "📊 Tổng số file .cs: $CS_COUNT"

echo ""
echo "3. Các file quan trọng:"
for file in "myReader.cs" "myWriter.cs" "Message.cs" "Controller.cs" "Session.cs"; do
    if find /workspace/Scripts -name "$file" 2>/dev/null | grep -q .; then
        echo "✅ Tìm thấy: $file"
    else
        echo "⚠️  Không tìm thấy: $file"
    fi
done

echo ""
echo "4. Cấu trúc thư mục:"
ls -la /workspace/Scripts/ | head -20

echo ""
echo "=== HOÀN TẤT KIỂM TRA ==="
