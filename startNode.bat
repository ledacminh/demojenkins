set ProjectPath=%~dp0
java  -Dwebdriver.gecko.driver="%ProjectPath%\geckodriver.exe"  -jar  C:\Users\Admin\Desktop\SeleniumGridDemo\Library\selenium-server-standalone-3.4.0.jar -role node  -hub  http://192.170.10.42:4444//grid/register -port 5555
