//const text = document.querySelector('.title'); //Declaring a variable to store the header container in html
const change = document.querySelector('.changeColor');
const list = document.querySelector('.lists');
// text.style.color = 'green';  grabs the text and change its color. if background then type backgroundColor.
// text.classList.add('changes'); goes to the css and applies the style in that class called .changes
/*
change.addEventListener('click', function change() {
    text.classList.add('changes');

    ----Remove Event Listener just use same syntax as adding it but use remove.

    change.removeEventListener('click', function change() {
    text.classList.add('changes');
});
*/


/*
for (lis of list) {
    lis.addEventListener('click', function() {
        this.style.color = 'red'; // this refers to each element in the list
    });
}
*/

const inputL = document.querySelector(".input");
change.addEventListener('click', function() {
    const text = document.querySelector('.title');
    text.classList.add('changes');
    const newLi = document.createElement("LI");
    const liContent = document.createTextNode(inputL.value);
    newLi.appendChild(liContent)
    list.appendChild(newLi);
});
document.getElementById("name");