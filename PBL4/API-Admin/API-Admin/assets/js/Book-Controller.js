

function Loanding() {
    GetBooks(hanldeLoanding);
}


function GetBooks(callback) {
    var apiUser = "https://localhost:44349/api/Saches/GetSaches";
    fetch(apiUser)
        .then(function (res) {
            if (res.status == 200) {
                return res.json();
            }
        })
        .then(callback);
}


function hanldeLoanding(data) {
    data.forEach((data) => {
        let trElement = document.createElement("tr");
        let tdElement1 = document.createElement("td");
        let tdElement2 = document.createElement("td");
        let tdElement3 = document.createElement("td");
        let tdElement4 = document.createElement("td");
        let tdElement5 = document.createElement("td");
        let tdElement6 = document.createElement("td");
        let tdElement7 = document.createElement("td");
        let tdElement8 = document.createElement("td");
        let tdElement9 = document.createElement("td");
        let tdElement10 = document.createElement("td");
        let tdElement11 = document.createElement("td");
        let tdElement12 = document.createElement("a");
        let tdElement13 = document.createElement("a");

        tdElement1.innerHTML = data.iD_Sach;
        tdElement2.innerHTML = data.tenSach;
        tdElement3.innerHTML = data.theloai;
        tdElement4.innerHTML = `    
        <div class="card move-on-hover" style="width: 50px; height: 65px;">    
        <a href="${data.imgSach}" target="_blank">
        <img src="${data.imgSach}" alt=""
          style=" width: 50px;height :65px; border-radius: 2px ; cursor: pointer;">
        </a>
        </div>   
        `;
        tdElement5.innerHTML = data.tenTacGia;
        tdElement6.innerHTML = data.solanTaiBan;
        tdElement7.innerHTML = data.namXuatBan;
        tdElement8.innerHTML = data.giaBan;
        tdElement9.innerHTML = data.soLuong;

        tdElement12.innerHTML =
            `<a href='#'><button class='btn btn-secondary px-4 pt-2 m-1' data-bs-toggle="modal" data-bs-target="#model_edit" onclick="OnLoandModel(${data.iD_Sach})"></button></a>`
        tdElement13.innerHTML =
            `<a href='#' class='btn btn-danger px-4 pt-2 m-1 mx-2' onclick='onDeleteBook(${data.iD_Sach})'></a>`;
        tdElement10.appendChild(tdElement12);
        tdElement11.appendChild(tdElement13);

        trElement.appendChild(tdElement1);
        trElement.appendChild(tdElement2);
        trElement.appendChild(tdElement3);
        trElement.appendChild(tdElement4);
        trElement.appendChild(tdElement5);
        trElement.appendChild(tdElement6);
        trElement.appendChild(tdElement7);
        trElement.appendChild(tdElement8);
        trElement.appendChild(tdElement9);
        trElement.appendChild(tdElement10);
        trElement.appendChild(tdElement11);

        let tBody = document.getElementById("body");
        tBody.appendChild(trElement);
    });
}

//Add

function OnAddBook() {
    let name = document.getElementById("name").value;
    let category = document.getElementById("category").value;
    let author = document.getElementById("author").value;
    let reprint = document.getElementById("reprint").value;
    let year = document.getElementById("year").value;
    let price = document.getElementById("price").value;
    let number = document.getElementById("number").value;
    if(name =="" || category == "" || author == ""|| reprint == ""|| year == ""|| price == ""|| number == "" )
    {
        swal({ title: 'Please fill in information.', type: 'warning' });
    }
    else
    {
        UploandImage(handlePostBook);
    }
}

function handlePostBook(data) {
    let name = document.getElementById("name").value;
    let category = document.getElementById("category").value;
    let url_img = data.url;
    let author = document.getElementById("author").value;
    let reprint = document.getElementById("reprint").value;
    let year = document.getElementById("year").value;
    let price = document.getElementById("price").value;
    let number = document.getElementById("number").value;

    let Book = {
        tenSach: name,
        theloai: category,
        img_Sach: url_img,
        tenTacGia: author,
        solanTaiBan: reprint,
        namXuatBan: year,
        giaBan: price,
        soLuong: number,
    };

    PostBook(Book);
}

function PostBook(data) {
    var apiUser = "https://localhost:44349/api/Saches/PostSach";
    fetch(apiUser, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then(function (res) {
        if (res.status == 200) {
            swal({ title: 'Success', type: 'success' })
            setTimeout(() => {
                location.reload();
            }, 1000)
        }
        else {
            swal({ title: 'Error', type: 'error' });
        }
    });
}


function UploandImage(callback) {
    const url = "https://api.cloudinary.com/v1_1/damlykdtx/image/upload";
    const files = document.querySelector("[type=file]").files;
    const formData = new FormData();
    if (files.length > 0) {
        for (let i = 0; i < files.length; i++) {
            let file = files[i];
            formData.append("file", file);
            formData.append("upload_preset", "BookTS");
            fetch(url, {
                method: "POST",
                body: formData
            }).then((response) => {
                return response.json();
            }).then(callback);
        }
    }
    else
    {
        swal({ title: 'Please select image .', type: 'warning' });
    }
};

function chooseFile(fileInput) {
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#image').attr('src', e.target.result);
        }
        reader.readAsDataURL(fileInput.files[0]);
    }
}


function validate(evt) {
    var theEvent = evt || window.event;

    // Handle paste
    if (theEvent.type === 'paste') {
        key = event.clipboardData.getData('text/plain');
    } else {
        // Handle key press
        var key = theEvent.keyCode || theEvent.which;
        key = String.fromCharCode(key);
    }
    var regex = /[0-9]|\./;
    if (!regex.test(key)) {
        theEvent.returnValue = false;
        if (theEvent.preventDefault) theEvent.preventDefault();
    }
}
//Delete

function onDeleteBook(id) {
    var answer = window.confirm("Delete data?");
    if (answer) {
        var api = `https://localhost:44349/api/Saches/DeleteSach?id=${id}`;
        fetch(api, {
            method: 'DELETE'
        })
            .then(function (res) {
                if (res.status == 200) {
                    swal({ title: 'Success', type: 'success' })
                    setTimeout(() => {
                        location.reload();
                    }, 1000)
                }
                else {
                    swal({ title: 'error', type: 'error' });
                }
            });
    }
}

//edit

function OnLoandModel(id)
{
    document.getElementById("exampleModalLabel2").innerHTML = `Edit Book ID : ${id}`;
    document.getElementById("id_book").value = id;
    var apiUser = `https://localhost:44349/api/Saches/GetSach?id=${id}`;
    fetch(apiUser).then(res => res.json())
        .then(data => {
            document.getElementById("name_ed").value = data.tenSach ;
            document.getElementById("category_ed").value = data.theloai;
            document.getElementById("author_ed").value = data.tenTacGia;
            document.getElementById("reprint_ed").value = data.solanTaiBan;
            document.getElementById("year_ed").value = data.namXuatBan;
            document.getElementById("price_ed").value = data.giaBan;
            document.getElementById("number_ed").value = data.soLuong;    
            $('#imageEd').attr('src', data.imgSach);
        })

}

function chooseFileEd(fileInput) {
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#imageEd').attr('src', e.target.result);
        }
        reader.readAsDataURL(fileInput.files[0]);
    }
}


function OnPutBook(id)
{
    const url = "https://api.cloudinary.com/v1_1/damlykdtx/image/upload";
    const files = document.getElementById("file_ed").files;
    const formData = new FormData();
    if (files.length > 0) {
        for (let i = 0; i < files.length; i++) {
            let file = files[i];
            formData.append("file", file);
            formData.append("upload_preset", "BookTS");
            fetch(url, {
                method: "POST",
                body: formData
            }).then((response) => {
                return response.json();
            }).then((data) =>
            {
                PutBook(id,data.url)
            }       
            );
        }
    }
    else
    {    
        PutBook(id,document.getElementById("imageEd").src)
    }

}

function PutBook(id,url_img) {
    let name = document.getElementById("name_ed").value;
    let category = document.getElementById("category_ed").value;
    let author = document.getElementById("author_ed").value;
    let reprint = document.getElementById("reprint_ed").value;
    let year = document.getElementById("year_ed").value;
    let price = document.getElementById("price_ed").value;
    let number = document.getElementById("number_ed").value;
    let apiUser = "https://localhost:44349/api/Saches/PutSach";
    let data = {
        iD_Sach: id,
        tenSach: name,
        theloai: category,
        imgSach: url_img,
        tenTacGia: author,
        solanTaiBan: reprint,
        namXuatBan: year,
        giaBan: price,
        soLuong: number,
    };

    console.log(data);
  

    fetch(apiUser, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then(function (res) {
        if (res.status == 200) {
            location.reload();
        }
        else {
            swal({ title: 'error', type: 'error' });
        }
    });
}

Loanding();

