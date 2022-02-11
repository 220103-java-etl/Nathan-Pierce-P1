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

function getMyRequests(){
    let urlR = "/Project-1/reimbursement";
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
        if(reimbursement.status == "pending"){
            tdStatus.innerHTML = '<button type="button" onclick="process(event, "Approve")">Approve</button><button type="button" onclick="process(event, "Denied")">Deny</button>';
        }
        else{
            tdStatus.innerHTML = reimbursement.status;
        }
        tr.append(tdStatus);

        let tdFManager = document.createElement("td");
        tdFManager.id = reimbursement.manager.id;
        tdFManager.innerHTML = `${reimbursement.resolver.fullName}`;
        tr.append(tdFManager);
        
        reimTable.append(tr);

        let tdProReason = document.createElement("td");
        tdProReason.innerHTML = reimbursement.processReason;
        tr.append(tdProReason);
    }

    reimDiv.append(bookTable);

}

function process(event, status){
    let reason = window.prompt("Please enter the reason for approving or denying the request.");

    let proUrl = "/Project-1/reimbursement";
    let xhr = new XMLHttpRequest;
    let tabRow = event.path[2];

    let request = {
        id: tabRow.cells[0],
        status: status,
        reason: reason
    }
    let json = JSON.stringify(request);

    xhr.open("PUT", proUrl);

    xhr.send(json);

}