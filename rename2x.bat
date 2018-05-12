@echo off  
echo.开始调用函数  
call:myDosFunc  %cd%
::set path="F:\bat\切图\2x\common_btn_back@2x.png"
::call:renameFunc %path%
echo.&pause&goto:eof  

:myDosFunc   
echo %~1 
for /R "%~1" %%s in (*) do ( 
	call:renameFunc %%s
) 

goto:eof  

:renameFunc  
set name=%~1
set filename=%~nx1
set newName2x=%filename:@2x=%
set newName3x=%filename:@3x=%
echo. %newName2x%
echo. %name%

echo. %filename%
if "%newName2x%" neq "%filename%" (
	ren %name% %newName2x% || echo 文件 %name% 改名失败
) else (
	if "%newName3x%" neq "%filename%" (
		ren %name% %newName3x% || echo 文件 %name% 改名失败
	)
)


goto:eof  