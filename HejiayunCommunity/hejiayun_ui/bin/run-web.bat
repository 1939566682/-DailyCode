@echo off
echo.
echo [信息] 使用 Vue 启动 Web 前端（OpenSSL 兼容模式）
echo.

%~d0
cd %~dp0

cd ..
set NODE_OPTIONS=--openssl-legacy-provider
npm run dev

pause
