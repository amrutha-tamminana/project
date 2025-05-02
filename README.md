# Arbitrary arithmetic operations
This Java library provides support for performing **arbitrary-precision arithmetic operations** on large integers and floating point numbers. It can handle basic operations like addition, subtraction, multiplication, and division for both integer and floating point numbers.
this library is split into two main classes
1. **AInteger**
2. **AFloat**
## Package structure
|---src/
|    |-AInteger.java #arbitrary precision integer class
|    |-AFloat.java   #arbitrary precision float class
|    |-MyInfArith.java # command line for operations
|---dist/aarithmetic.jar
|---Test.java
|---README.md
|---report.pdf
|---run.py
## Features
1. **AIntegers class**: used for operations of large integers
2. **AFloat class**:used for operations of floating point data types upto 30 decimal precision
Supports basic arithmetic operations i.e

(i) Addition(+)
(ii) Subtraction(-)
(iii) Multiplication(*)
(iv) Division(/)
## How to use
1. Import libraries
2. Perform arithmetic operations
### Run the project using docker:
1. First build the docker image
2. Run the docker container
   Output will be seen.
### Run using ant
