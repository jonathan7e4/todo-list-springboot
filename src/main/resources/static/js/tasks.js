// Add button active or unactive
const inputBox = document.querySelector(".task-insert input");
const addBtn = document.querySelector(".task-insert button");

inputBox.onkeyup = ()=> {
    let userData = inputBox.value;
    if (userData.trim() != 0) {
        addBtn.classList.add("active");
    } else {
        addBtn.classList.remove("active");
    }
}

// Get the input field
var input = document.getElementById("task");

// Execute a function when the user releases a key on the keyboard
input.addEventListener("keyup", function(event) {
    // Number 13 is the "Enter" key on the keyboard
    if (event.keyCode === 13) {
        // Cancel the default action, if needed
        event.preventDefault();
        // Trigger the button element with a click
        document.getElementById("addBtn").click();
    }
})

// Check / Uncheck list
var list = document.querySelector('#task_list');
list.addEventListener('click', function(ev) {
    if (ev.target.tagName === 'LI') {
        ev.target.classList.toggle('checked');
    }
}, false);

function addTask() {
    var li = document.createElement("li"),
        inputText = document.getElementById("task").value,
        textNode = document.createTextNode(inputText)
    li.appendChild(textNode)
    if (inputText === "") {
        alert("Has d'introduir text abans d'afegir")
    } else {
        document.getElementById("task_list").appendChild(li)
    }
    document.getElementById("task").value = ""

    // Appends to UL an element LI and also appends to LI an element SPAN
    var span = document.createElement("span");
    span.setAttribute("class", "close")
    var i = document.createElement("i");
    i.className = "fas fa-times-circle";
    span.appendChild(i);
    li.appendChild(span);

    // Delete task
    var close = document.getElementsByClassName("close");
    for (i = 0; i < close.length; i++) {
        close[i].onclick = function() {
            var div = this.parentElement;
            div.remove();
        }
    }

    // DRAG & DROP
    var lista = document.getElementById("task_list");
    Sortable.create(lista,{})
}