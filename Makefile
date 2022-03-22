JFLAGS = -g
JC = javac
PATH = src
CLASSES = \
        Main.java 
		
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

default: classes

classes: $(CLASSES:.java=.class)

run: 
	java -classpath $(PATH) Main

clean:
	$(RM) *.class