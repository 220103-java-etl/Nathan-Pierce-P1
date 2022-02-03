function login(){
    let url = "http://localhost:8080/Project-1/login";

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let loginInfo = {
        username: username,
        password: password
    }
    let loginJson = JSON.stringify(loginInfo)

    let xhr = new XMLHttpRequest();

    xhr.onreadyStateChange = receiveData;

    xhr.open("Post", url, true);

    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.send(loginJson);



    function receiveData() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let r = xhr.responseText;

            rJson = JSON.parse(r);
            localStorage.setItem("userID", rJson.id);
            localStorage.setItem("userName", rJson.fullName);

            if(rJson.role == Employee){
                window.location.href="/Project-1/employee.html";
            }
            else{
                window.location.href="/Project-1/manager.html";
            }
        }
        else if(xhr.readyState == 4 && xhr.status == 418){
            alert(`Your login credentials were incorrect.`);
        }
    }

}
