@echo off
cd /d E:\Idea_workscope

:: 拉取远程代码
git pull DailyCode-Gitee main
git pull DailyCode-GitHub main

:: 英文提示（无乱码）
set /p commit_msg=Please enter commit message for main project: 

:: 提交+推送
git add .
git commit -m "%commit_msg%"
git push DailyCode-Gitee main
git push DailyCode-GitHub main

echo.
echo Main project pushed successfully!
pause