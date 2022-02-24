
@echo off

echo ... stopping the execution ...
taskkill /IM "java.exe" /F
taskkill /IM "chromedriver.exe" /F
echo ... the execution stopped ...
:END
endlocal
echo Bye!
pause