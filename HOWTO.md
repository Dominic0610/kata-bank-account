# Technical notes & How To use

The Maven project is configured as Java 11 compatible (source and target), but it should run fine on a Java 8 system
(no specific language above Java 8 was used). If you only have Java 8 installed, change the pom.xml to replace
```
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
```
by
```
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
```
and perform a rebuild with
```
mvn compile
```

I developed it with IntelliJ and Java 17, but ran into some issues with jBehave which is not yet compatible. Read this:
https://jbehave.atlassian.net/browse/JBEHAVE-1479
The jBehave issue is fixed, but not yet released (it will be with version 5.0).

=> I finally picked an installed JDK 13 to develop the project.

A quick test of the application by running it interactively is possible. Follow this after building the Maven
project:
```
PS C:\work\java\kata-bank-account> cd .\target\
PS C:\work\java\kata-bank-account\target> java -cp .\kata-bank-account-1.0-SNAPSHOT.jar com.dominic.kata.bankaccount.MainApp
Enter an amount (positive if DEPOSIT, negative if WITHDRAWAL). Q to Quit: 100
Operation  | Date                |      Op. amount |         Balance
DEPOSIT    | 15/11/2021 05:39:52 |          100.00 |          100.00
Enter an amount (positive if DEPOSIT, negative if WITHDRAWAL). Q to Quit: 200
Operation  | Date                |      Op. amount |         Balance
DEPOSIT    | 15/11/2021 05:40:00 |          200.00 |          300.00
DEPOSIT    | 15/11/2021 05:39:52 |          100.00 |          100.00
Enter an amount (positive if DEPOSIT, negative if WITHDRAWAL). Q to Quit: -50
Operation  | Date                |      Op. amount |         Balance
WITHDRAWAL | 15/11/2021 05:40:07 |          -50.00 |          250.00
DEPOSIT    | 15/11/2021 05:40:00 |          200.00 |          300.00
DEPOSIT    | 15/11/2021 05:39:52 |          100.00 |          100.00
Enter an amount (positive if DEPOSIT, negative if WITHDRAWAL). Q to Quit: q
Bye.
PS C:\work\java\kata-bank-account\target>
```

Running the unitary test is possible with:
```
mvn test
```
and the acceptance test (which also includes the unitary tests) with:
```
mvn integration-test
```