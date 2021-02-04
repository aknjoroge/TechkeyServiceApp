document.write(greet); // PRINTS OUT GREET AS THE OUT PUT IN THE BROWSER
var age = 20; // THIS IS A VARIABLE A THAT HOLDS THE VALUE OF AGE AS 20
//alert("Alert Message");
var x = 25,
    y = 35,
    z = "25";
//alert(x == z); //true 
//alert(x == y); //false
//alert(x < y); // true
//alert(x <= y); // true
//alert(x >= y); // false
//alert(x === z); // false: SINCE THEY ARE NOT IDENTICAL.

//<input type="submit" onclick="alert('Hello Martin')" value="Submit"></input>  PRINTS HELLO MARTIN ONCLICK

ONCLICK
/* 

USES THE ONCLICK FUNCTION TO PERFORM THE OPERATION IN THE FUNCTION
<button type="button" id="myBtn">Click Me</button>

function greet() {
    var name = prompt("Enter your Name");
    alert("Hello " + name + ", Welcome to our Website");
}
document.getElementById("myBtn").onclick = greet;

*/

ONCONTEXTMENU
/*
 <button type="button" oncontextmenu="alert('You have clicked me')" id="myBtn">Click Me</button>
 DISPLAY THE ALERT MESSAGE WHEN THE USER RIGHT CLICK
 */

ONMOUSEOVER: //PLACE A MOUSE OVER AN ELEMENT
    /*
<button type="button" onmouseover="alertt()" id="myBtn">Click Me</button>
    <script>
        function alertt() {
            alert("Hello Martinezz");
        }
    </script>
 */


    ONMOUSEOUT: //EVENT OCCURS WHEN THE MOUSE IS PLACED OUTSIDE AN ELEMENT
    /*
 <button type="button" onmouseout="alertt()" id="myBtn">Click Me</button>
    <script>
        function alertt() {
            alert("Hello Martinezz");
        }
    </script>
*/


    ONKEYDOWN: //Display alert message when the down arrow key is pressed.
    ONKEYPRESS: //Display alert message when any other key is pressed other than arrow keys. 
    /*
    <button type="button" onkeydown="alertt()" id="myBtn">Click Me</button>
        <script>
            function alertt() {
                alert("Hello Martinezz");
            }
        </script>
    */



    OBJECTS DECLARATIONS
    /*
    var o = new Object; //MUST INCLUDE THE NEW OBJECT
            o.name = "Martin";
            alert(o.name); //OUTPUTS Martin
            delete o.name;
            alert(o.name); // Outputs UNDEFINED
    */

ARGUMENTS
/*
function doAdd() { if(arguments.length == 1) { alert(arguments[0] + 10); } else if (arguments.length == 2) { alert(arguments[0] + arguments[1]); } }
doAdd(10);        //outputs “20” doAdd(30, 20);    //outputs “50”
    */


ARRAY
/*
var aColors = new Array(“red”, “green”, “blue”); 
alert(aColors.length);    //outputs “3” aColors[25] = “purple”; 
aColors(arr.length);    //outputs “26”
*/

ARRAY WITH.LOG
    /*
    var numbers;
    numbers=[2,7,3,5];
    document.write(numbers);
    console.log(numbers);
    */


FUNCTION WITH Parameters
/*
function sayHi(sName, sMessage) {
            alert('Hello ' + sName + ',' + sMessage);
        }
        sayHi('MARTIN', ' how are you today?');
*/

DOCUMENT / WINDOW EVENTS.


ONSUBMIT FUNCTION IN FORM //alert before submiting a form
/*
 <form action="" onsubmit="alert('The Data will be Submitted ');">
        <input type="text" name="name" id="">
        <input type="submit" value="Submit">
    </form>
*/

ONLOAD //Event occur when a page has finished loading. placed in the body tag
/*
<body onload="window.alert('Page has finished loading');">
*/

ONUNLOAD //Event occur when the user try to leave a page
/*
<body onunload="alert('Are you sure you want to leave this page');">
*/


ONESIZE //Occur when a user resizes a window or minimised/ maximised
/*
<p id="result">
        My Name is Martin Njoroge <br>I am 20 years old.
    </p>

    <script>
        function resize() {
            var w = window.outerWidth;
            var h = window.outerHeight;
            var txt = "Window size: width" + w + "height=" + h;
            document.getElementById("result").innerHTML = txt;
        }
        window.onresize = resize;
    </script>
*/

BREAK AND CONTINUE STATEMENTS

BREAK
/*
var iNum = 0;
for (var i=1; i < 10; i++) { if (i % 5 == 0) { break; } iNum++; }
alert(iNum);    //outputs “4
*/

CONTINUE
/*
var iNum = 0;
for (var i=1; i < 10; i++) { if (i % 5 == 0) { continue; } iNum++; }
alert(iNum);    //outputs “8”
*/

BREAK OUTERMOST

//Used in nested loops. it not only breaks the inner most loop but also the outermpst loop
/*
var iNum = 0;
outermost:
 for (var i=0; i < 10; i++)
             { 
                 for (var j=0; j < 10; j++)
                  { 
                      if (i == 5 && j == 5) 
                      { 
                          break outermost; 
                       } iNum++; 
                  }
              }
alert(iNum);    //outputs “55”

The code iterates every 10 times in the j then increment the i and goes for another 10 times
therefore the code will be executed 100 times in total. 
The breakoutermost will act when i=5 and j=5 that is after looping 50 times the in the 
5th time it breaks both the inner and outermost loop therefore stopping at 55.
*/

CONTINUE OUTERMOST
//For Continue it will not finish the remaining 5 and it skips to i=6 therefore outputs 95.
/*
var iNum = 0;
outermost: 
for (var i=0; i < 10; i++)
 { 
     for (var j=0; j < 10; j++)
      {
           if (i == 5 && j == 5)
            {
                 continue outermost;
             } iNum++; 
            }
 }
alert(iNum);    //outputs “95”
*/

SQUARE ROOT
Math.sqrt();

POWER
Math.pow(2, 4); //Outputs 16

RETURN VALUES:
    console.log(Math.max(2, 4));
//outputs 4.

TYPE OF: To identify the type variable used.
console.log(typeof 4.5)
    //outputs number.


CAPITALISATION or UpperCase
/*
var name="martin";
console.log(name.toUpperCase());   WHEN USING CONSOLE
document.write(name.toUpperCase());   WHEN USING BROWSER
*/


PUSH, POP, SHIFT, UNSHIFT, indexOf FUNCTION

/*
var number=[1,2,3];
number.push(4); ADDS TO THE END OF AN ARRAY
number.push(5);
document.write(number);
console.log(number);

number.shift("john");// removes an element at the start of an array
number.unshift("j"); // adds an element at the start of an array
number.pop();    REMOVES THE LAST ELEMENT FROM THE ARRAY
indexOf(); gets the index of an element in an array
document.write(number);
console.log(number);
*/

OBJECTS IN JS
/*
var obj={
    man:"Homo Sapein",
    event:["work ","eat ","walk"]
};
alert(obj.event[0]);
*/

OPERATIONS ON OBJECTS
/*
delete obj.event[0];   Deletes an object and returns undefined when printed out
console.log("event" in obj);   Checks if an object is there and if its not it print out false

let objectA = {a: 1, b: 2}; 
Object.assign(objectA, {b: 3, c: 4});  Copies all properties to the assigned objects
console.log(objectA); 
OUTPUTS {a: 1, b: 3, c: 4}  */
SLICE FUNCTION
/* It removes all the elements after it
console.log([0, 1, 2, 3, 4].slice(2, 4));
outputs// → [2, 3] 
console.log([0, 1, 2, 3, 4].slice(2)); 
outputs// → [2, 3, 4] 

console.log("coconuts".slice(4, 7)); outputs: nut 
console.log("coconut".indexOf("u")); outputs: 5
console.log("one two three".indexOf("ee")); outputs: 11  NB: if you use e alone it gives the index of the first e this is 2 in this example*/
indexOf
/* returns the index(position) of an element in Array
console.log([1, 2, 3, 2, 1].indexOf(2)); outputs 1 */
TRIM
/* removes all spaces, newline and tabs
console.log(" okay \n ".trim()); outputs:okay */
Split and Join
/*You can split a string on every occurrence of another string with split and join it again with join.
let sentence = "Secretarybirds specialize in stomping"; 
let words = sentence.split(" "); 
console.log(words); // → ["Secretarybirds", "specialize", "in", "stomping"] 
console.log(words.join(" ")); // → Secretarybirds. specialize. in. stomping */
Repeat a string a number of times.
    /*console.log("LA".repeat(3)); // → LALALA */
Get the Length of a string.
    /*
    let string = "abc"; console.log(string.length); Outputs: 3 */
Rest Parameters by using three dots...
    /*
    let words = ["never", "fully"]; 
    console.log(["will", ...words, "understand"]); Outputs:  ["will", "never", "fully", "understand"]*/
Random, COSINE, SINE...PI
    /*
    function randomPointOnCircle(radius)
     { let angle = Math.random() * 2 * Math.PI; return 
        {x: radius * Math.cos(angle), y: radius * Math.sin(angle)};
     } console.log(randomPointOnCircle(2)); OUTPUTS: {x: 0.3667, y: 1.966}*/

CONVERT STRINGS TO INTEGERS:
    /*
    var b=prompt("Number");
    var a=parseInt(b);
    document.write(a);
    */

    HOW TO REPRESENT FUNCTION IN OTHER WAYS
    /* a function that adds two numbers
    var add=(num1,num2)=> num1+num2;      Similar to: function add(num1,num2).....
    a=parseInt(prompt("Enter the first No."));
    b=parseInt(prompt("Enter the second No."));
    document.write(add(a,b));
    */

USING DOLLAR SIGN $
/*
document.write(`Martin ${i+1}`.repeat(3));
*/

Conditional Operator
/*
var x = 8;
        x >= 8 ? document.write("True") : document.write("True");
        */

GET Keycode


document.addEventListener("keyup", function(event) {
    console.log(event.keyCode);
})