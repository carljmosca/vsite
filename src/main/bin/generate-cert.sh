keytool -genkey -noprompt \
    -alias server \
    -dname "CN=springboot.carljmosca.com, OU=Virginia, O=Carl J. Mosca, C=US" \
    -keystore ../docker/keystore.p12 \
    -storepass changeit \
    -storetype PKCS12 -keyalg RSA -keysize 2048 \
    -keypass changeit -validity 3650