# 每日新发数据匹配工具  


## 运行环境: java8 安装地址：https://www.java.com/zh-CN/download/  


## 运行方法：
### win：  
	系统点击start.bat运行程序  
 ### macOS：  
	打开terminal  
	cd "/你的xfTool文件目录/"  
	chmod -x ./mac_run.sh  
	点击mac_run.sh运行程序  


## 使用方法：

+	将Qtrade下载的新发债券复制到	inputs文件夹下的“当日新增.txt”中
  
	将一创固收公众号内容复制到	  inputs文件夹下的“一创固收.txt”中

	讲昨日新发数据放到 inputs文件夹下的“昨日新增.txt”中

	在 inputs文件夹下的config.txt中找到	date:MM/DD 配置继续发要筛选的时间  


+	检查log文件是否有报错，如果没有输出文件在outputs文件夹下



## 已知问题：

当将一创固收公众号内容缺少部分估值或剩余时间数据时该条数据会出错需手动修改



