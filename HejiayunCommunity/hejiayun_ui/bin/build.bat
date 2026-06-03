@echo off
echo.
echo [信息] 打包Web前端，生成dist文件夹
echo.

%~d0
cd %~dp0

cd ..
set NODE_OPTIONS=--openssl-legacy-provider
npm run build:prod

pause
