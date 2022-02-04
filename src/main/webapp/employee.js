window.onload = function() {
    document.getElementById('Reimbursements').style.display = 'none';
    document.getElementById('submitForm').style.display = 'none';

    let xhr = new XMLHttpRequest()
    let url = "http://localhost:8080/Project-1/login";
    xhr.open("GET", url);
    xhr.onreadystatechange = function() {
        if (xhr.readystate == 4 && xhr.status == 200 ){
            let jsonRes = JSON.parse(req.responseText);
            localStorage.setItem("UsersName", jsonRes.fullName);
            document.getElementById('Name').innerHTML =localStorage.getItem("UsersName");
        }
    }
        
 xhr.send();
};



function viewRequests(){
    document.getElementById('Reimbursements').style.display= 'block';


}