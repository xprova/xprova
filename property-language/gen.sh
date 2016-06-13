#!/bin/bash

java -jar antlr-4.5.3-complete.jar -o ../src/main/java/net/xprova/propertylanguage -package net.xprova.propertylanguage PropertyLanguage.g4

dos2unix -q ../src/main/java/net/xprova/propertylanguage/*
