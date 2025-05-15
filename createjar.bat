@echo off
REM ------------------------------------------------------------------
REM Batch file to compile Java project to bin folder and create a JAR
REM ------------------------------------------------------------------

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

REM Create the JAR file using the compiled classes and resources
echo Creating the JAR file...
jar cfm QuestForTheSpiritStick.jar manifest.txt -C bin .

echo Build complete. To run the JAR file, use:
echo java -jar QuestForTheSpiritStick.jar
pause