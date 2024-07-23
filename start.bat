echo off

if exist ".\log.txt" del ".\log.txt"

java -jar -Dfile.encoding=utf-8 xfTool.jar  >> log.txt

start notepad ".\log.txt"

pause