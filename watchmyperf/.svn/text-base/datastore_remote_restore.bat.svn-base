@echo off
set PATH=%PATH%;C:\Program Files (x86)\python
set CWD=%CD%
set APP_ID=watchmyperf
set APP_NAME=%APP_ID%
set FILENAME=datastore.backup

cd /d D:\Code\google_appengine
bulkloader.py --dump --app_id=%APP_ID% --url=http://%APP_NAME%.appspot.com/remote_api --filename=%FILENAME%
bulkloader.py --restore --app_id=%APP_ID% --filename=%FILENAME% %CWD%

cd /d %CWD%
pause
