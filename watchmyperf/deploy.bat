@echo off
set PATH=%PATH%;C:\Program Files (x86)\python
set CWD=%CD%
date /T > deploy_date.dat
time /T > deploy_time.dat

cd /d D:\Code\google_appengine
python appcfg.py update %CWD%

cd /d %CWD%
pause
