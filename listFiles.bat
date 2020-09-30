d:
cd D:\SpringTools4.6.1\workspace\genSpringTest
find ./src -type f -print | sed -e "s/$/<br>/g" > files.md

pause