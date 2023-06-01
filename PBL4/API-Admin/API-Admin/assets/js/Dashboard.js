function Loanding() {
    GetNumberUser(hanldeLoandingUser);
    GetMoney(hanldeLoandingMoney);
    GetNumberBill(hanldeLoandingBill)
}

function GetNumberUser(callback) {
    var apiUser = "https://localhost:44349/api/Accounts/GetAccounts";
    fetch(apiUser)
        .then(function (res) {
            if (res.status == 200) {
                return res.json();
            }
        })
        .then(callback);
}

function hanldeLoandingUser(data) {
    var count = 0;
    data.forEach((data) => {
        if(data.position == 1)
        {
            count = count + 1;
        }
    })
    var user = document.getElementById("user");
    user.innerHTML = count ;
    user.innerHTML += ` <span class="text-success text-sm font-weight-bolder">+user</span>`
};



function GetMoney(callback) {
    var apiUser = "https://localhost:44349/api/HoaDons/GetHoaDons";
    fetch(apiUser)
        .then(function (res) {
            if (res.status == 200) {
                return res.json();
            }
        })
        .then(callback);
}


function hanldeLoandingMoney(data) {
    var count = 0;
    data.forEach((data) => {
       count = count + data.tongTien;
    })
    var user = document.getElementById("money");
    user.innerHTML = "$"+count ;
    user.innerHTML += ` <span class="text-success text-sm font-weight-bolder">+$</span>`
};


function GetNumberBill(callback) {
    var apiUser = "https://localhost:44349/api/HoaDons/GetHoaDons";
    fetch(apiUser)
        .then(function (res) {
            if (res.status == 200) {
                return res.json();
            }
        })
        .then(callback);
}


function hanldeLoandingBill(data) {
    var count = 0;
    data.forEach((data) => {
       count = count + 1;
    })
    var user = document.getElementById("bill");
    user.innerHTML = count;
    user.innerHTML += ` <span class="text-success text-sm font-weight-bolder">+bill</span>`
};


Loanding()