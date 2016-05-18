#!/bin/bash
#
# Run Your App
#
stty -icanon min 1 -echo
java -jar -Djline.terminal=jline.UnixTerminal target/xprova-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
"$@"
stty icanon echo

