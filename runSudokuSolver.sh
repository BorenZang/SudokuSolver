#!/bin/bash

# Define the Java file and the class name
JAVA_FILE="SudokuSolver.java"
CLASS_NAME="SudokuSolver"

# Compile the Java program
echo "Compiling $JAVA_FILE..."
javac $JAVA_FILE

# Check if the Java compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful. Running $CLASS_NAME..."
    # Run the compiled Java program
    java $CLASS_NAME
else
    echo "Compilation failed."
fi
