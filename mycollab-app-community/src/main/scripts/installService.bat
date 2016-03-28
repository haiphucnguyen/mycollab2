@echo off
set SERVICE_NAME=MyCollab2

if "%PROCESSOR_ARCHITECTURE%" == "X86" goto 32bit
if "%PROCESSOR_ARCHITECTURE%" == "AMD64" goto 64bit
if "%PROCESSOR_ARCHITECTURE%" == "IA64" goto 64bitIntel

:32bit
SET PR_INSTALL=D:\Documents\mycollab2\mycollab-app-community\target\MyCollab-5.2.10\MyCollab-5.2.10\bin\prunsrv.exe
goto execution

:64bit
SET PR_INSTALL=D:\Documents\mycollab2\mycollab-app-community\target\MyCollab-5.2.10\MyCollab-5.2.10\bin\amd64\prunsrv.exe
goto execution

:64bitIntel
set PR_INSTALL=D:\Documents\mycollab2\mycollab-app-community\target\MyCollab-5.2.10\MyCollab-5.2.10\bin\ia64\prunsrv.exe
goto execution

:execution
echo PATH %PR_INSTALL%
REM Service log configuration
set PR_LOGPREFIX=%SERVICE_NAME%
set PR_LOGPATH=c:\logs
set PR_STDOUTPUT=c:\logs\stdout.txt
set PR_STDERROR=c:\logs\stderr.txt
set PR_LOGLEVEL=Debug

REM Path to java installation
set PR_JVM=C:\Program Files\Java\jre1.8.0_65\bin\server\jvm.dll
set PR_CLASSPATH=..\executor.jar

REM Startup configuration
set PR_STARTUP=auto
set PR_STARTMODE=jvm
set PR_STARTCLASS=com.esofthead.mycollab.Executor
set PR_STARTMETHOD=start

REM Shutdown configuration
set PR_STOPMODE=jvm
set PR_STOPCLASS=com.esofthead.mycollab.Executor
set PR_STOPMETHOD=stop

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
