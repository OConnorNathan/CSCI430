#!/bin/bash

rm -rf bin/

mkdir  bin

javac -d bin/ UserInterface/*.java BackEnd/*.java

cd bin/

jar -cvfm Warehouse.jar ../MANIFEST.MF UserInterface/*.class BackEnd/*.class


