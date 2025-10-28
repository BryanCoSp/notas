
loadNewNotePopup = () => {
        fetch("/newNote")
            .then(res => res.text())
            .then(html => {
                let element = document.getElementById("newNotePopup");
                element.innerHTML = html;
                element.style.display = "inline-block";
            });
}

hideFragment = () => {
    let element = document.getElementById("newNotePopup");
    element.innerHTML = "<div></div>";
    element.style.display = "none";
}

showNote = (id) => {
    fetch("/" + id)
        .then(res => res.text())
        .then(html => {
            let element = document.getElementById("showNotePopup");
            element.innerHTML = html;
            element.style.display = "inline-block";
        });
}

hideNote = () => {
    let element = document.getElementById("showNotePopup");
    element.innerHTML = "<div></div>";
    element.style.display = "none";
}

// document.getElementById("newNotePopup").addEventListener("click", function() {
//     let element = document.getElementById("newNotePopup");
//     element.innerHTML = "<div></div>";
//     element.style.display = "none";
// });


