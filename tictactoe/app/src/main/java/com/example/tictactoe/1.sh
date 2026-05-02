 #!/bin/bash

# Укажите путь к папке (текущая папка или укажите свою)
TARGET_DIR="${1:-.}"

# Имя выходного файла
OUTPUT_FILE="all_files_content.txt"

# Очищаем выходной файл
> "$OUTPUT_FILE"

# Рекурсивно обходим все файлы
find "$TARGET_DIR" -type f | while read -r file; do
    echo "================== FILE: $file ==================" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
    echo "" >> "$OUTPUT_FILE"
done

echo "Готово! Содержимое сохранено в $OUTPUT_FILE"
