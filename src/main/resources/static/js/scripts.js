
loadFragment = () => {
    // document.getElementById("loadFragmento").addEventListener("click", function() {
        fetch("/newNote")
            .then(res => res.text())
            .then(html => {
                let element = document.getElementById("newNotePopup");
                element.innerHTML = html;
                element.style.display = "inline-block";
            });
    // });
}


