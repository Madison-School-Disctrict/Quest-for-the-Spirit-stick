@echo off
REM ------------------------------------------------------------------
REM Batch file to compile Java project to bin folder and create a JAR
REM ------------------------------------------------------------------

REM Set timestamp and output folder
for /f "tokens=1-4 delims=/- " %%a in ("%date%") do (
    set YYYY=%%d
    set MM=%%b
    set DD=%%c
)
for /f "tokens=1-2 delims=:." %%a in ("%time%") do (
    set HH=%%a
    set MN=%%b
)
set HH=%HH: =0%
set TIMESTAMP=%YYYY%%MM%%DD%_%HH%%MN%
set JAR_NAME=QuestForTheSpiritStick_%TIMESTAMP%.jar
set OUTPUT_FOLDER=jars

REM Delete the bin folder if it exists and create a new one
echo Clearing bin folder...
if exist bin (
    rd /s /q bin
)
mkdir bin

REM Compile Java code from 'src' folder into 'bin' folder
echo Compiling Java files...
javac -d bin -sourcepath src src\main\Main.java

REM Copy 'res' folder (resources like images and sounds) to 'bin' folder
echo Copying resources from 'res' to 'bin'...
xcopy /E /Y /Q res bin

REM Create the output folder if it doesn't exist
if not exist "%OUTPUT_FOLDER%" (
    mkdir "%OUTPUT_FOLDER%"
)

REM Create the JAR file using the compiled classes and resources
echo Creating the JAR file: %JAR_NAME% ...
jar cfm "%OUTPUT_FOLDER%\%JAR_NAME%" manifest.txt -C bin .

echo Build complete. To run the JAR file, use:
echo java -jar %OUTPUT_FOLDER%\%JAR_NAME%
pause
