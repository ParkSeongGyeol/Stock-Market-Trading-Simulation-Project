@echo off
chcp 65001
cls
echo ==========================================
echo    Stock Market Simulation Launcher
echo ==========================================

if not exist "bin" mkdir bin

echo [1/2] Compiling source code...
javac -d bin -encoding UTF-8 -sourcepath src src/main/Main.java src/view/MainView.java src/model/*.java src/repository/*.java src/service/*.java src/util/*.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b
)

echo [2/2] Launching Application...
echo ------------------------------------------
java -cp bin main.Main

pause
