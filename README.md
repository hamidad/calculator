# Calculator developed in JAVA using javax.swing & java.awt libraries

<br/>

This is a standard calculator developed in JAVA using javax.swing & java.awt libraries. The program uses basic JFrame GUI which, then, contains set of JPanels, JButtons and JLabels. 

## Calculator frame consists of: <br/>
-  Header - Which is used to display user's input and calculator result <br/>
-  Interactive part which is a set of: <br/>
    -  Operations
        -  Clear entry
        -  Clear
        -  Clear previous number
        -  Division
        -  Multiplication
        -  Addition
        -  Subtraction
        -  Reversion number status (positive/negative)
        -  Dot for decimal points
        -  Calculation (equal sign)
    -  Digit numbers
        -  0, 1, 2, 3, 4, 5, 6, 7, 8, 9

## Program flow:
1. Interactive part allows user to choose numbers and/or operations
2. Every choice rebuilds the calculator string which is a set of choosen numbers and operations
3. Result calculation <br/>
    1. Calculator string is splitted into separate string by DMAS operation including them. Eg. `„345.3*45-3+56 ÷999“`  -> `„345.3“, „*“, „45“, „-“, „3“, „+“, „56“, „÷“, „999“` <br/>
    2. New splitted string are converted into ArrayList to be able for manipulation <br/>
    3. First are calculated high precedence operations <br/>
         1. Previous and next element (before and after operation) are double parsed -> Converted to double number
         2. Result is calculated based on operation, switched previous (element before operation) with it and removed operation and following element
    4. Second are calculated low precedence operations
        1. Same flow is done like in high precedence operations
    5. Result is last left element in array
4. Result is added to header 
