PATH = src/
CLASSES = Main
JC	 = javac
FLAGS = -g

all: $(PATH)Main.java 
	$(JC) $(PATH)Main.java

run: $(PATH)Main.class
	java Main

clean:
	$(RM) *.class