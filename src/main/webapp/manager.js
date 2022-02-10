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