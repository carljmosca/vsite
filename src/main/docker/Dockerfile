FROM java:8
ADD vsite-0.0.1-SNAPSHOT.jar app.jar
ADD keystore.p12 keystore.p12

ENV JAVA_OPTIONS="-Xmx300m"
CMD java $JAVA_OPTIONS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
