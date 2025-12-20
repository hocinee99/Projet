SRC_DIR = src
BIN_DIR = bin
JUNIT_JAR = /usr/share/java/junit-jupiter-api.jar:/usr/share/java/junit-platform-console-standalone.jar

all:
	mkdir -p $(BIN_DIR)
	javac -sourcepath $(SRC_DIR) -d $(BIN_DIR) -cp $(JUNIT_JAR):$(BIN_DIR) $(SRC_DIR)/**/*.java
	java -jar /usr/share/java/junit-platform-console-standalone.jar --class-path $(BIN_DIR) --scan-class-path
	java -cp $(JUNIT_JAR):$(BIN_DIR) main.Main

clean:
	rm -f $(BIN_DIR)/**/*.class

.PHONY: all clean
