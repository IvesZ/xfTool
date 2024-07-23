#!/bin/bash

# 检查是否存在log文件并删除
[ -f "./log.txt" ] && rm "./log.txt"

# 运行Java程序，并将输出追加到log.txt文件中
java -jar -Dfile.encoding=utf-8 xfTool.jar >> log.txt

# 打开log.txt文件
open -a TextEdit log.txt