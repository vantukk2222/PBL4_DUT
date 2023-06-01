function Loanding() {
    GetPeople(hanldeLoanding);
}

function GetPeople(callback) {
    var apiUser = "https://localhost:44349/api/People/GetPeople";
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
        let tdElement9 = document.createElement("a");
        let tdElement10 = document.createElement("a");

        tdElement1.innerHTML = data.iD_Account;
        tdElement2.innerHTML = data.name;
        if (data.gender) {
            tdElement3.innerHTML = 'Male'
        }
        else {
            tdElement3.innerHTML = 'Female'
        }

        const myArray = data.dateOfBirth.split("T");
        tdElement4.innerHTML = myArray[0];
        tdElement5.innerHTML = data.email;
        tdElement6.innerHTML = data.phoneNumber;
        tdElement9.innerHTML =
            `<a href='#'><button class='btn btn-secondary px-4 pt-2 m-1' data-bs-toggle="modal" data-bs-target="#model_edit" onclick="OnLoandModel(${data.iD_Account})"></button></a>`
        tdElement10.innerHTML =
            `<a href='#' class='btn btn-danger px-4 pt-2 m-1' onclick='onDeleteProfiles(${data.iD_Account})'></a>`
        tdElement7.appendChild(tdElement9);
        tdElement8.appendChild(tdElement10);

        trElement.appendChild(tdElement1);
        trElement.appendChild(tdElement2);
        trElement.appendChild(tdElement3);
        trElement.appendChild(tdElement4);
        trElement.appendChild(tdElement5);
        trElement.appendChild(tdElement6);
        trElement.appendChild(tdElement7);
        trElement.appendChild(tdElement8);

        let tBody = document.getElementById("body");
        tBody.appendChild(trElement);
    });
}

// Delete
function onDeleteProfiles(id) {
    var answer = window.confirm("Delete data?");
    if (answer) {
        var api = `https://localhost:44349/api/People/DeletePerson?id=${id}`;
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

// Edit Profile
function OnLoandModel(id) {
    GetModelProfile(id);
}

function GetModelProfile(id) {
    var apiUser = `https://localhost:44349/api/People/GetPerson?id=${id}`;
    fetch(apiUser).then(res => res.json())
        .then(data => {
            console.log(data);
            document.getElementById("exampleModalLabel").innerHTML = `Edit Profile ID : ${data.iD_Account}`
            document.getElementById("id_account").value = data.iD_Account;
            document.getElementById("ed_name").value = data.name;
            const myArray = data.dateOfBirth.split("T");
            document.getElementById("ed_dob").value = myArray[0];
            document.getElementById("ed_phone").value = data.phoneNumber;
            document.getElementById("ed_email").value = data.email;
            document.getElementById("selectGender").value = data.gender;

        })
}

function onPutProfile(id) {
    let name = document.getElementById("ed_name").value;
    let dob = document.getElementById("ed_dob").value;
    let phone = document.getElementById("ed_phone").value;
    let email = document.getElementById("ed_email").value;
    let Gender = document.getElementById("selectGender").value;
    console.log(Gender);
    let apiUser = "https://localhost:44349/api/People/PutPerson";
    let data = {
        iD_Account: id,
        name: name,
        gender: Boolean(Gender),
        dateOfBirth: dob,
        email: email,
        phoneNumber: phone
    };


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

//Add

function LoandModelAdd() {
    var select = document.getElementById('selectid');
    while(select.length)
    {
        select.remove(select.length); 
        select.length = select.length - 1;
    }
    
    GetPersonNull(hanldeLoandModel);
}

function GetPersonNull(callback) {
    var apiUser = "https://localhost:44349/api/People/GetPersonNull";
    fetch(apiUser)
        .then(function (res) {
            if (res.status == 200) {
                return res.json();
            }
        })
        .then(callback);
}


function hanldeLoandModel(data) { 
    document.getElementById("selectid").innerHTML = `<option selected value="0">Select ID Account</option>`;
data.forEach((data) => {
    document.getElementById("selectid").innerHTML +=  `<option value="${data}">ID Account : ${data}</option>`;
});
}

function onAddProfile()
{
    var Phone = document.getElementById("phone").value;
    var Email = document.getElementById("email").value;
    var Dob = document.getElementById("dob").value;
    var SelectGender = document.getElementById("selectGender").value;
    var Name = document.getElementById("name").value;
    var Selectid = document.getElementById("selectid").value;
    let Profile = {
        iD_Account: Selectid,
        name: Name,
        gender: Boolean(SelectGender),
        dateOfBirth: Dob,
        email: Email,
        phoneNumber: Phone,
    };
    console.log(Profile);

    if(Selectid == 0 || Email == '' || Dob=='' || Name=='' || Phone=='')
    {
        swal({ title: 'Please fill in information.', type: 'warning' });
    }
    else
    {
    AddProfile(Profile);
    }
    

}

function AddProfile(data)
{
    var apiUser = "https://localhost:44349/api/People/PostPerson";
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
            swal({ title: 'Error', type: 'error' });
        }
    });
}




Loanding();