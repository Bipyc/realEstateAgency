let dropArea = document.getElementById("drop-area")
var appendedFiles = [];

function preventDefaults(e) {
    e.preventDefault()
    e.stopPropagation()
}

function highlight(e) {
    dropArea.classList.add('highlight')
}

function unhighlight(e) {
    dropArea.classList.remove('highlight')
    dropArea.classList.remove('active')
}

function handleDrop(e) {
    var dt = e.dataTransfer
    var files = dt.files

    handleFiles(files)
}

function handleFiles(files) {
    appendedFiles.forEach(function (value) {
        document.getElementById('gallery').removeChild(value)
    })
    appendedFiles = [];
    files.forEach(previewFile)
}

function previewFile(file) {
    let reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onloadend = function () {
        let img = document.createElement('img')
        img.src = reader.result
        document.getElementById('gallery').appendChild(img)
    }
}

function uploadFile(file, i) {
    $("input[name=fileLoadInput]").clone().appendTo($("input[name=fileLoadInput]").parent()).val('');
    $("input[name=fileLoadInput]").hide();
    //$("#fileLoadInput").val(uploadedFiles);
}

function addFileInput() {
    let reader = new FileReader()
    reader.readAsDataURL(document.getElementById('inputFile').files[0])
    reader.onloadend = function () {
        let div = document.getElementsByClassName('previewImageTemplate')[0].cloneNode(true);
        div.removeAttribute("class");
        let img = div.getElementsByClassName('previewImage')[0];
        img.src = reader.result;
        let inputElem = document.getElementById('inputFile');
        let clonedElem = inputElem.cloneNode(true);
        let deleteElem = div.getElementsByClassName('deleteImage')[0];
        deleteElem.addEventListener("click", deleteImage);
        clonedElem.removeAttribute("id");
        clonedElem.removeAttribute("onchange");
        div.appendChild(clonedElem);
        inputElem.value = '';
        document.getElementById('gallery').appendChild(div)
    }
}

function deleteImage() {
    $(this).parent().remove();
}

function addEmail() {
    let div = document.getElementsByClassName('emailAddresses')[0];
    let count = div.getElementsByClassName("emails").length;
    let input = document.createElement('input');
    input.setAttribute("type","text");
    input.setAttribute("class","emails");
    input.setAttribute("name","emails["+count+"]");
    div.appendChild(input);
}

function init() {
    $(".deleteImage").bind("click", deleteImage);
}

$(init);