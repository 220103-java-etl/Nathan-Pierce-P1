function viewRequests(){
    document.getElementById("ReimbursementsTable").style.display= "block";
    document.getElementById("RequestForm").style.display = "none";
    getMyRequests();
}

function requestForm(){
    document.getElementById("ReimbursementsTable").style.display= "none";
    document.getElementById("RequestForm").style.display = "block";
}

function getName(){
    let urlU = "/Project-1/login";
    let xhtr = new XMLHttpRequest();

    xhtr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            let Ujson = JSON.parse(this.responseText);
            document.getElementById("Name").innerHTML = Ujson.fullName;
        }
    }
    xhtr.open("GET", urlU);

    xhtr.send();

}

window.onload = function() {
    document.getElementById("ReimbursementsTable").style.display = "none";
    document.getElementById("RequestForm").style.display = "none";

    getName();    
}

function getMyRequests(){
    let urlR = "/Project-1/reimtable";
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            let rjson = JSON.parse(this.responseText);
            console.log(rjson);
            populateTable(rjson);
        }
    }

    xhr.open("GET", urlR);

    xhr.send();
}

function populateTable(rjson){
    let reimDiv = document.getElementById("ReimbursementsTable");
    reimDiv.innerHTML = "";
    
    let reimTable = document.createElement("table");
    reimTable.setAttribute("class", "table");

    let tHead = document.createElement("thead");

    let tableHeaderRow = document.createElement("tr");

    let tHeaders = ["ID", "Amount", "Reason", "Status", "Processing Manager", "Approval/Denial Reason"];
    for (let h of tHeaders) {
        let th = document.createElement("th");
        th.setAttribute("scope", "col");
        th.innerHTML = h;
        tableHeaderRow.append(th);
    }
    tHead.append(tableHeaderRow)
    reimTable.append(tHead);

    for (let reimbursement of rjson) {
        let tr = document.createElement("tr");
        tr.id = reimbursement.id;
        
        let tdID = document.createElement("td");
        tdID.innerHTML = reimbursement.id;
        tr.append(tdID);

        let tdAmount = document.createElement("td");
        tdAmount.innerHTML = reimbursement.amount;
        tr.append(tdAmount);

        let tdReason = document.createElement("td");
        tdReason.innerHTML = reimbursement.submitReason;
        tr.append(tdReason);

        let tdStatus = document.createElement("td");
        tdStatus.innerHTML = reimbursement.status;
        tr.append(tdStatus);

        let tdFManager = document.createElement("td");
        tdFManager.id = reimbursement.manager.id;
        tdFManager.innerHTML = `${reimbursement.resolver.fullName}`;
        tr.append(tdFManager);

        let tdProReason = document.createElement("td");
        tdProReason.innerHTML = reimbursement.processReason;
        tr.append(tdProReason);

        reimTable.append(tr);
    }

    reimDiv.append(reimTable);

}
