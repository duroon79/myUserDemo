export JAVA_HOME=/app/jdk1.8.0_333
export CLASSPATH=.:$JAVA_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$PATH
java -version
cd $1
mvn clean
mvn compile
mvn package
mv target/user-demo-1.0-SNAPSHOT.jar $2
cd $2
docker build -t userdemo:$3 .
docker run -d -p 96:8080 userdemo:$3
