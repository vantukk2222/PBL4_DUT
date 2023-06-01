function onLogin() {
    GetAccounts(hanldeLogin);
}

function GetAccounts(callback) {
    var apiUser = "https://localhost:44349/api/Accounts/GetAccounts";
    fetch(apiUser)
        .then(function (res) {
            if (res.status == 200) {
                return res.json();
            }
            else {
                wal({ title: 'Error', type: 'error' })
            }
        })
        .then(callback);
}

function hanldeLogin(data) {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var Check = false;
    if (username == '' || password == '') {
        swal({ title: 'Please enter your username and password', type: 'error' });
    }
    else {
        data.forEach((data) => {
            if (data.userName == username && data.password == password) {
                Check = true;
            }
        });
        if (Check == true) {
            swal({ title: 'Success', type: 'success' })
            setTimeout(() => {
                window.location.assign("../pages/Dashboard.html")
            }, 1000);
        }
        else {
            swal({ title: 'The username or password is incorrect', type: 'error' })
        }
    }

}

