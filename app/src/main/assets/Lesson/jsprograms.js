FIZZ BUZZ NUMBER

/*
var a=1,b,c=1,d=1,e,i=1,n;
for(i=1;i<=100;i++)
{
    if(i%3==0 && i%5==0)
    {
        document.write('FizzBuzz<br>');
        continue;
    }
    if(i%3==0)
    {
        document.write('Fizz<br>');
        continue;
    }
    if(i%5==0)
    {
        document.write('Buzz<br>');
        continue;
    }
    
    else
    {
        document.write(i+"<br>");
    }
}
*/

FACTORIAL PROGRAM
/*
var a=1,
    b,
    c=1,
    d=1,
    e,
    i=1,
    n;
n=prompt("Enter the number to compute factorial");
for (i=1;i<=n;i++)
{
  b=d*c;
  c=b;
  d++;
}
alert(c);
*/

TRIANGLE SHAPE PROGRAM
/*
var a, b, c, d, e = 9,
            i = 0;
        for (a = 0; a < 9; a++) {
            for (b = 0; b <= i; b++) {
                document.write("🍎");
            }
            i++;
            document.write("<br>");
        }

        for (c = 9; c >= 0; c--) {
            for (d = 0; d <= e; d++) {
                document.write("🍎");
            }
            e--;
            document.write("<br>");
        }
*/

CHESS BOARD
/*
var a,b,c,d,e,i=0;
for(a=0;a<8;a++)
{
    if(a%2==0)
    {
        document.write("\t\t");
        for(b=1;b<=7;b++)
        {  
            document.write(" #");
        }
        document.write(" <br>");
    }

    if(a%2==1)
    {
        for(c=1;c<=7;c++)
        {
            document.write("# ");
        }
        document.write(" <br>");
    }
}
*/

SAY HI Function
/*
var a,b,c,d,e,i=0;
function sayHi(name,age)
{
    alert("Hello "+name+" You are "+age+" Old");
}
sayHi('Martin',20);
*/


Printing Numbers with a Step i.e by increasing or decreasing order:
    /*
    var a,b,c,f,l,ff,ll,ss,fff,lll,sss;
    var i,d,e=0,f,l,st; 

     fff=prompt("Enter the First number in the sequence");
    lll=prompt("Enter the Last number in the sequence");
    sss=prompt("Enter the Step to display the sequence in a given order");
    ff=parseInt(fff);
    ll=parseInt(lll);
    ss=parseInt(sss);
    function numbers(first,last,step)
    {
     if(ss<0){
    st=ss*-1;
    for(i=first;i>=last;i-=st)
        {
            d=e+i;
            e=d;
            document.write(i+"\n");
            
        }
        document.write("<br><br>The sum is: "+e);
     }
     else
     {
         for(i=first;i<=last;i+=step)
        {
            d=e+i;
            e=d;
            document.write(i+"\n");
            
        }
        document.write("<br><br>The sum is: "+e);
     } 
        
        
    }

    document.write(numbers(ff,ll,ss));
    */


    REVERSE AN Array
    /*
    var a,b,c,d,array;
    array=[1,3,4,5,6,9];
    c=array.length;
    function reverseArray()
    {
        d=c;
      for(b=0;b<=c;b++)
      {
          document.write(array[d]+", ");
          d--;
      }
    }
    reverseArray()
    */

OBJECTS PROGRAMS
/*
let protoRabbit = {
            speak(say) {  // This is a Method inside an objcet and it will be called
                document.write(`The ${this.type} Rabbit says ${ say} <br>`);
            }
        };
        let hungryRabbit = Object.create(protoRabbit);
        let coolRabbit = Object.create(protoRabbit);
        coolRabbit.type = "Cool";
        coolRabbit.speak("coooool");
        hungryRabbit.type = 'Hungry';
        hungryRabbit.speak("Aaaoouhw");
        */

classes and objects
/*
class Temperature {
            set celsius(celsius) {
                b = celsius * 1.8 + 32;
            }
            get celsius() {
                return b;
            }
            set fahrein(value) {
                a = (value - 32) / 1.8;
            }
            get fahrein() {
                return a;
            }
        }
        let temp = new Temperature();
        temp.fahrein = 86;
        temp.celsius = 22;
        document.write(temp.fahrein);
        document.write("<br>");
        document.write(temp.celsius);
        */


Function Change Image
/*
<script>
        var i = 0,
            a;
        var time = 4000;
        var image = ['bg.png', 'bird.png', 'fg.png'];

        function changeImg() {
            document.slide.src = image[i];
            if (i < image.length - 1) {
                i++;
            } else {
                i = 0;
            }
            setTimeout(() => {
                changeImg();
            }, time);
        }
        window.onload = changeImg;
    </script>

    <img name="slide" width="20%" height="20%" alt="">
*/