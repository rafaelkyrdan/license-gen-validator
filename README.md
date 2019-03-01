# License key generator and license key validator
1. License key generator generates a license key, that is a single string, from two pieces of information:
- a domain name, like `company.com`
- an expiration date, in format "dd/MM/yyyy"

2. License key validator takes a license key as input and print out a message, either:
- 'valid' if the license key is valid for the current time and hostname of the machine it is running on
- 'invalid' if it is running on a machine with an invalid hostname or the license key has expired

## Prerequisites
- jdk
- scala
- sbt
- hostname for Validator is stored in the config file, so if you want to Validator check against your domain then update it there.

## Run from shell scripts
1. A bash script for generator", like `$ ./generate-key <hostname> <expiration-date>`. 
Example:
`./generate-key company.com 18/03/2019`, here important to follow date format "dd/MM/yyyy".
2. A bash script for validate, like `$ ./validate-key <key>`. 
Example:
`./validate-key eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyAiaG9zdG5hbWUiOiAiY29tcGFueS5jb20iLCAiZXhwaXJhdGlvbiI6IDE1ODQ0ODk2MDAgfQ.670XJTTpxekxzK7Sa8DA9yimvsWsOK-Yq7zGyq4RTao`

## Run as sbt project
- sbt compile
- sbt test