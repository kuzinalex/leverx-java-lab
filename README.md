# leverx-java-lab

## Сompiling and running.

-  Clone repository and open leverx-java-lab/console-experiments in console.
-  Compile files with command **javac -d bin src/com/kuzin/*** , where **-d** - flag, after which you should specify the location where the compiled classes will go, **bin** - folder name, **src/com/kuzin*** source files location, * means, that it is necessary to compile all classes.
-  Run program with command **java -classpath bin com.kuzin.Main**, where **-classpath** - flag, after which you should specify the location of compiled classes.

## Сreating and running of a JAR-file.

- Create JAR-файл with command **jar -c -f  main.jar -e com.kuzin.Main  -C bin .**, где **-f** -  flag, after which you should specify file name, **-e** - flag, after which you should specify program entry point, **-C** - flag, after which you should specify the location of compiled classes  (**bin** in our case), **.** - path, where jar-file will be created.
- Run jar-file with command **java -jar main.jar**.
