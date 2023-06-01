function Loanding() {
    GetAccounts(hanldeLoanding);
}

function GetAccounts(callback) {
    var apiUser = "https://localhost:44349/api/Accounts/GetAccounts";
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
        let tdElement6 = document.createElement("a");
        let tdElement7 = document.createElement("td");
        let tdElement8 = document.createElement("a");

        tdElement1.innerHTML = data.iD_Account;
        tdElement2.innerHTML = data.userName;
        tdElement3.innerHTML = data.password;
        if (data.position == 0) {
            tdElement4.innerHTML = "Admin";
        }
        else {
            tdElement4.innerHTML = "Customer";
        }
        tdElement6.innerHTML =
            `<a href='#'><button class='btn btn-secondary px-4 pt-2 m-1' data-bs-toggle="modal" data-bs-target="#model_edit" onclick="OnLoandModel(${data.iD_Account})"></button></a>`
        tdElement8.innerHTML =
            `<a href='#' class='btn btn-danger px-4 pt-2 m-1 mx-2' onclick='onDeleteAccount(${data.iD_Account})'></a>`
        tdElement5.appendChild(tdElement6);
        tdElement7.appendChild(tdElement8);

        trElement.appendChild(tdElement1);
        trElement.appendChild(tdElement2);
        trElement.appendChild(tdElement3);
        trElement.appendChild(tdElement4);
        trElement.appendChild(tdElement5);
        trElement.appendChild(tdElement7);

        let tBody = document.getElementById("body");
        tBody.appendChild(trElement);
    });
}

// Edit Account
function OnLoandModel(id) {
    GetModelAccount(id);
}

function GetModelAccount(id) {
    var apiUser = `https://localhost:44349/api/Accounts/GetAccountByID?id=${id}`;
    fetch(apiUser).then(res => res.json())
        .then(data => {
            document.getElementById("exampleModalLabel2").innerHTML = `Edit Account ID : ${data.iD_Account}`
            document.getElementById("id_account").value = data.iD_Account;
            document.getElementById("ed_username").value = data.userName;
            document.getElementById("ed_password").value = data.password;
            document.getElementById("selectPostion").value = data.position;
        })
}

function PutAccount(id) {
    let us = document.getElementById("ed_username").value;
    let pw = document.getElementById("ed_password").value;
    let pos = document.getElementById("selectPostion").value;
    let apiUser = "https://localhost:44349/api/Accounts/PutAccount";
    let data = {
        iD_Account: id,
        userName: us,
        password: pw,
        position: pos
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


// Add Account

function OnAddAccount() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if (username.length < 5 || password.length < 5) {
        swal({ title: 'Passwords and Username must be at least 6 characters', type: 'warning' })
    }
    else {
        handlePostAccount();
    }
}

function handlePostAccount() {
    let username = document.getElementById("username");
    let password = document.getElementById("password");
    let position = document.getElementById("selectPost");
    let Account = {
        userName: username.value,
        password: password.value,
        position: position.value
    };
    PostAccount(Account);
}

function PostAccount(data) {
    var apiUser = "https://localhost:44349/api/Accounts/PostAccount";
    fetch(apiUser, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then(function (res) {
        if (res.status == 200) {
            location.reload();
        }
        else {
            swal({ title: 'Account does not exist', type: 'error' });
        }
    });
}

//Delete Account

function onDeleteAccount(id) {
    var answer = window.confirm("Delete data?");
    if (answer) {
        var api = `https://localhost:44349/api/Accounts/DeleteAccount?id=${id}`;
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

// 
function onPos(text) {

    let e = document.getElementById("body");
    let child = e.lastElementChild;
    while (child) {
        // xóa child
        e.removeChild(child);

        // gán child bằng phần tử con cuối cùng mới
        child = e.lastElementChild;
    }
    if (text == 3) {
        Loanding();
    }
    else {
        var apiUser = "https://localhost:44349/api/Accounts/GetAccounts";
        fetch(apiUser)
            .then(function (res) {
                if (res.status == 200) {
                    return res.json();
                }
            })
            .then(data => {
                data.forEach((data) => {
                    if (data.position == text) {
                        let trElement = document.createElement("tr");
                        let tdElement1 = document.createElement("td");
                        let tdElement2 = document.createElement("td");
                        let tdElement3 = document.createElement("td");
                        let tdElement4 = document.createElement("td");
                        let tdElement5 = document.createElement("td");
                        let tdElement6 = document.createElement("a");
                        let tdElement7 = document.createElement("td");
                        let tdElement8 = document.createElement("a");
                        tdElement1.innerHTML = data.iD_Account;
                        tdElement2.innerHTML = data.userName;
                        tdElement3.innerHTML = data.password;
                        if (data.position == 0) {
                            tdElement4.innerHTML = "Admin";
                        }
                        else {
                            tdElement4.innerHTML = "Customer";
                        }
                        tdElement6.innerHTML =
                            `<a href='#'><button class='btn btn-secondary px-4 pt-2 m-1' data-bs-toggle="modal" data-bs-target="#model_edit" onclick="OnLoandModel(${data.iD_Account})"></button></a>`
                        tdElement8.innerHTML =
                            `<a href='#' class='btn btn-danger px-4 pt-2 m-1 mx-2' onclick='onDeleteAccount(${data.iD_Account})'></a>`
                        tdElement5.appendChild(tdElement6);
                        tdElement7.appendChild(tdElement8);

                        trElement.appendChild(tdElement1);
                        trElement.appendChild(tdElement2);
                        trElement.appendChild(tdElement3);
                        trElement.appendChild(tdElement4);
                        trElement.appendChild(tdElement5);
                        trElement.appendChild(tdElement7);

                        let tBody = document.getElementById("body");
                        tBody.appendChild(trElement);
                    }
                });


            });
        }

    }


    Loanding();
