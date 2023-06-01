function Loanding() {
    GetBill(hanldeLoanding);
}

function GetBill(callback) {
    var apiUser = "https://localhost:44349/api/HoaDons/GetHoaDons";
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
        let tdElement8 = document.createElement("a");
        let tdElement9 = document.createElement("a");
        let tdElement10 = document.createElement("a");

        const myArray = data.ngayLap.split("T");
        tdElement4.innerHTML = myArray[0];

        tdElement1.innerHTML = data.iD_HoaDon;
        tdElement2.innerHTML = myArray[0] + " " + myArray[1];
        tdElement3.innerHTML = data.tongTien;
        tdElement4.innerHTML = data.iD_Account;

        tdElement8.innerHTML =
            `<a href='#'><button class='btn btn-info px-4 pt-2 m-1' onclick="OnLoandlDetail(${data.iD_HoaDon})"></button></a>`
        tdElement9.innerHTML =
            `<a href='#'><button class='btn btn-secondary px-4 pt-2 m-1' data-bs-toggle="modal" data-bs-target="#model_edit" onclick="OnLoandModelEdit(${data.iD_HoaDon})"></button></a>`
        tdElement10.innerHTML =
            `<a href='#' class='btn btn-danger px-4 pt-2 m-1 mx-2' onclick='onDeleteBill(${data.iD_HoaDon})'></a>`
        tdElement5.appendChild(tdElement8);
        tdElement6.appendChild(tdElement9);
        tdElement7.appendChild(tdElement10);

        trElement.appendChild(tdElement1);
        trElement.appendChild(tdElement2);
        trElement.appendChild(tdElement3);
        trElement.appendChild(tdElement4);
        trElement.appendChild(tdElement5);
        trElement.appendChild(tdElement6);
        trElement.appendChild(tdElement7);

        let tBody = document.getElementById("body");
        tBody.appendChild(trElement);
    });
}

// Edit Account
function OnLoandModelEdit(id) {
    GetIDAccounts(hanldeLoandingID);
    GetModelBill(id);
}

function GetModelBill(id) {
    var apiUser = `https://localhost:44349/api/HoaDons/GetHoaDon?id=${id}`;
    fetch(apiUser).then(res => res.json())
        .then(data => {
            document.getElementById("exampleModalLabel2").innerHTML = `Edit Bill ID : ${data.iD_HoaDon}`
            document.getElementById("id_bill").value = data.iD_HoaDon;
            document.getElementById("ed_price").value = data.tongTien;
            const myArray = data.ngayLap.split("T");
            document.getElementById("ed_date").value = myArray[0];
            document.getElementById("ed_time").value = myArray[1];
            document.getElementById("selectid").value = data.iD_Account;
        })
}


//get id
function hanldeLoandingID(data) {
    data.forEach((data) => {
        document.getElementById("selectid").innerHTML += `<option value="${data.iD_Account}">ID Account : ${data.iD_Account}</option>`;
    })
}

function GetIDAccounts(callback) {
    var apiUser = "https://localhost:44349/api/Accounts/GetAccounts";
    fetch(apiUser)
        .then(function (res) {
            if (res.status == 200) {
                return res.json();
            }
        })
        .then(callback);
}
//

function PutBill(id) {
    let id_account = document.getElementById("selectid").value;
    let date = document.getElementById("ed_date").value;
    console.log(date);
    let time = document.getElementById("ed_time").value;
    let datetime = date + "T" + time;
    let price = document.getElementById("ed_price").value;
    if (id_account != "" && date != "" && time != "" && price != "") {
        let apiUser = "https://localhost:44349/api/HoaDons/PutHoaDon";
        let data = {
            iD_HoaDon: id,
            ngayLap: datetime,
            tongTien: price,
            iD_Account: id_account
        };

        fetch(apiUser, {
            method: "PUT",
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
                swal({ title: 'error', type: 'error' });
            }
        });
    }
    else{
        swal({ title: 'Please fill in information.', type: 'warning' });
    }
}

//Delete Bill

function onDeleteBill(id) {
    var answer = window.confirm("Delete data?");
    if (answer) {
        var api = `https://localhost:44349/api/HoaDons/DeleteHoaDon?id=${id}`;
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

// Detail

function OnLoandlDetail(id) {
    GetDetail(id);
}


function GetDetail(id) {
    var apiUser = `https://localhost:44349/api/ChiTietHoaDons/GetChiTietHoaDons?id=${id}`;
    fetch(apiUser)
        .then(function (res) {
            if (res.status == 200) {
                return res.json();
            }
        })
        .then((data) => {
            if (data) {
                hanldeLoandingDetail(data, id);
            }
            else {
                swal({ title: 'Dữ liệu không tồn tại hoặc đã bị xóa', type: 'warning' });
            }
        }
        );
}


function hanldeLoandingDetail(data, id) {

    let e = document.getElementById("body2");
    let child = e.lastElementChild;
    while (child) {
        // xóa child
        e.removeChild(child);

        // gán child bằng phần tử con cuối cùng mới
        child = e.lastElementChild;
    }

    data.forEach((data) => {

        let trElement = document.createElement("tr");
        let tdElement1 = document.createElement("td");
        let tdElement2 = document.createElement("td");
        let tdElement3 = document.createElement("td");
        let tdElement4 = document.createElement("td");
        let tdElement5 = document.createElement("td");

        tdElement1.innerHTML = id;
        tdElement2.innerHTML = data.iD_Sach;
        var apiUser = `https://localhost:44349/api/Saches/GetSach?id=${data.iD_Sach}`;
        fetch(apiUser)
            .then(function (res) {
                if (res.status == 200) {
                    return res.json();
                }
            })
            .then((data) => {
                tdElement3.innerHTML = data.tenSach;
            }
            );
        tdElement4.innerHTML = data.soLuong;
        tdElement5.innerHTML = data.tongTien;

        trElement.id = "BillD"
        trElement.appendChild(tdElement1);
        trElement.appendChild(tdElement2);
        trElement.appendChild(tdElement3);
        trElement.appendChild(tdElement4);
        trElement.appendChild(tdElement5);

        let tBody = document.getElementById("body2");
        tBody.appendChild(trElement);
    });
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


Loanding();
