@echo off
set SERVICE_NAME=MyCollab5w

for %%i in ("%~dp0..") do set "MYCOLLAB_HOME=%%~fi"

if "%PROCESSOR_ARCHITECTURE%" == "X86" goto 32bit
if "%PROCESSOR_ARCHITECTURE%" == "AMD64" goto 64bit
if "%PROCESSOR_ARCHITECTURE%" == "IA64" goto 64bitIntel

:32bit
SET PR_INSTALL=%MYCOLLAB_HOME%\bin\prunsrv.exe
goto execution

:64bit
SET PR_INSTALL=%MYCOLLAB_HOME%\bin\amd64\prunsrv.exe
goto execution

:64bitIntel
set PR_INSTALL=%MYCOLLAB_HOME%\bin\ia64\prunsrv.exe
goto execution

:execution
REM Service log configuration
set PR_LOGPREFIX=%SERVICE_NAME%
set PR_LOGPATH=%MYCOLLAB_HOME%\logs
set PR_STDOUTPUT=%MYCOLLAB_HOME%\logs\stdout.txt
set PR_STDERROR=%MYCOLLAB_HOME%\logs\stderr.txt
set PR_LOGLEVEL=Debug

REM Path to java installation
set PR_CLASSPATH=%MYCOLLAB_HOME%\executor.jar

REM Startup configuration
set PR_STARTUP=auto
set PR_STARTMODE=jvm
set PR_STARTCLASS=com.esofthead.mycollab.Executor
set PR_STARTPATH=%MYCOLLAB_HOME%
set PR_STARTMETHOD=start

REM Shutdown configuration
set PR_STOPMODE=jvm
set PR_STOPCLASS=com.esofthead.mycollab.Executor
set PR_STOPMETHOD=stop
set PR_STOPPATH=%MYCOLLAB_HOME%

REM JVM configuration
set PR_JVMMS=256
set PR_JVMMX=1024
set PR_JVMSS=4000
set PR_JVMOPTIONS=

REM Install service
IF DEFINED PR_INSTALL (
  %PR_INSTALL% //IS//%SERVICE_NAME%
) else (
  echo "Not find the procrun program"
)
