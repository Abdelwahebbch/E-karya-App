@echo off
setlocal

:: === CONFIGURATION ===
set APP_NAME=EKarya
set MAIN_MODULE=com.ekarya.app/com.ekarya.app.App
set ICON=src\main\resources\pictures\AppIcon.ico
set JAVAFX_LIB=C:\javafx-sdk-21.0.2\lib
set RUNTIME_DIR=runtime
set OUTPUT_DIR=installer
set JAVA_MODULES=java.base,java.desktop,java.sql,javafx.controls,javafx.fxml

:: === CLEAN UP OLD ARTIFACTS ===
echo Cleaning up old build files...
rd /s /q %RUNTIME_DIR%
rd /s /q %OUTPUT_DIR%



:: === CREATE CUSTOM RUNTIME ===
echo Creating custom Java runtime with jlink...
jlink ^
  --module-path "%JAVAFX_LIB%;target" ^
  --add-modules %JAVA_MODULES% ^
  --output %RUNTIME_DIR% ^
  --strip-debug ^
  --compress=2 ^
  --no-header-files ^
  --no-man-pages

:: === PACKAGE THE APP ===
echo Packaging the application with jpackage...
jpackage ^
  --type exe ^
  --input target ^
  --dest %OUTPUT_DIR% ^
  --name %APP_NAME% ^
  --module com.ekarya.app/com.ekarya.app.App ^
  --icon %ICON% ^
  --runtime-image %RUNTIME_DIR% ^
  --win-dir-chooser ^
  --win-menu ^
  --win-shortcut


echo.
echo ✅ Done! Check the "%OUTPUT_DIR%" folder for your installer.
pause
