c:\centralticket\CentralTicket.exe //IS ^
  --Install="C:\centralticket\CentralTicket.exe" ^
  --Jvm=auto --Startup=auto --StartMode=jvm --StopMode=jvm ^
  --Classpath="C:\centralticket\centralticket-1.0-SNAPSHOT-jar-with-dependencies.jar" ^
  --StartClass="com.quicktravel.centralticket.App" --StartMethod="start" ^
  --StartParams="443;keystore.jks;password" ^
  --StopClass="com.quicktravel.centralticket.App" --StopMethod="stop" ^
  --LogPath="c:\centralticket\logs" --LogLevel="Debug" ^
  --StdOutput=auto --StdError=auto ^
  --StopTimeout=1
