
call mvn -f travelmanagementsystem-common/pom.xml clean install -U 
call mvn -f travelmanagementsystem-dto/pom.xml clean install -U 
call mvn -f travelmanagementsystem-entity/pom.xml clean install -U    
call mvn -f persistence/pom.xml clean install -U 
call mvn -f mapper/pom.xml clean install -U  
call mvn -f framework-entity/pom.xml clean install -U  
call mvn -f notification/pom.xml clean install -U
call mvn -f document-utility/pom.xml clean install -U 
call mvn -f travelmanagementsystem-service/pom.xml clean install -U  
call mvn -f webframework/pom.xml clean install -U  
call mvn -f travelmanagementsystem-portal/pom.xml clean install -U 
call mvn -f travelmanagementsystem-ear/pom.xml clean install -U   
echo "Done"