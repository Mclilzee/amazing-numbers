# Amazing Number
A Program to have fun with numbers! and It's not math.
Numbers are fun, and they have many properties, like being Odd or Even, Primal etc... More will be shown in the examples bellow.
You provide the input you want, such as finding a number with some property. You will be able to choose a range, choose a specific number, search for properties and more

# Requirement
- Java version 17+ <a href="https://www.oracle.com/de/java/technologies/downloads/">Java download Link</a>

# Build - Run Project
- Clone repository and navigate into repo's directory
- Run project `$ ./gradlew run"`

# Usage Commands

Instructions will be printed out in terminal after starting up the program.
You can search for one number properties by giving a starting number and how many numbesr to look for. Note: the second parameter is not the end number, its how many numbers to search more will be shown in the examples bellow.
You can specify what properties you want and not want, to execlude a proprity prefix it with `-`. If two properties can't co-exist such as `even and odd` at the same time, an error message will be printed out to inform the user.

# Example - Instructions
```console
Welcome to Amazing Numbers!

Supported requests:
- enter a natural number to know its properties;
- enter two natural numbers to obtain the properties of the list:
  * the first parameter represents a starting number;
  * the second parameter shows how many consecutive numbers are to be processed;
- two natural numbers and properties to search for;
- a property preceded by minus must not be present in numbers;
- separate the parameters with one space;
- enter 0 to exit.

Enter a request:
```

# Example - One Number Properties
```console
 Enter a request: 234

Properties of 234
        buzz: false
        duck: false
 palindromic: false
      gapful: false
         spy: false
      square: false
       sunny: false
     jumping: true
       happy: false
         sad: true
        even: true

```

# Example - Looking for Range
- Will look for 10 numbers, starting from the number 5
```console
Enter a request: 5 10

                5 is palindromic, spy, jumping, sad, odd
                6 is palindromic, spy, jumping, sad, even
                7 is buzz, palindromic, spy, jumping, happy, odd
                8 is palindromic, spy, sunny, jumping, sad, even
                9 is palindromic, spy, square, jumping, sad, odd
                10 is duck, jumping, happy, even
                11 is palindromic, sad, odd
                12 is jumping, sad, even
                13 is happy, odd
                14 is buzz, sad, even

```

# Example - Looking for specific Property

- Looking for 10 numbers that are odd starting from 1
```console
Enter a request: 1 10 odd

                1 is palindromic, spy, square, jumping, happy, odd
                3 is palindromic, spy, sunny, jumping, sad, odd
                5 is palindromic, spy, jumping, sad, odd
                7 is buzz, palindromic, spy, jumping, happy, odd
                9 is palindromic, spy, square, jumping, sad, odd
                11 is palindromic, sad, odd
                13 is happy, odd
                15 is sunny, sad, odd
                17 is buzz, sad, odd
                19 is happy, odd

```
- Looking for 5 numbers that are odd and not jumping starting from 5
```console
Enter a request: 5 5 odd -jumping

                11 is palindromic, sad, odd
                13 is happy, odd
                15 is sunny, sad, odd
                17 is buzz, sad, odd
                19 is happy, odd
 
```
- Looking for conflicting properties
```console
Enter a request: 1 10 odd even

The request contains mutually exclusive properties: [EVEN, ODD]
There are no numbers with these properties.
```