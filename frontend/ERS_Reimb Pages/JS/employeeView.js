//landing page for the website
const url = "http://localhost:8080/P1-markratib/";

const createTicket = "createTicket";
const compl = "getCompleteTickets";
const pend = "getPendingTickets";

document.getElementById("createTicketBtn").addEventListener("click", createTicketFunc)
document.getElementById("completeTicketsBtn").addEventListener("click", getCompleteTicketsFunc);
document.getElementById("pendingTicketsBtn").addEventListener("click", getPendingTicketsFunc);

/************************************************************BUTTON FUNCTIONS************************************************************/
async function createTicketFunc()
{
    console.log("Hi from create ticket :)")
    if((document.getElementById("inputUsername").value === "" || 
    (document.getElementById("inputAmount").value === "") ||
    (document.getElementById("inputType").value === "")))
    {
        //nothing
        console.log("INSERT VALUES")
    }
    else//user has data, let's do something
    {
        let type = 1;
        switch(document.getElementById("inputType").value)
        {
            case "lodging":
                {
                    type = 1;
                    break;
                }
            case "travel":
                {
                    type = 2;
                    break;
                }
            case "food":
                {
                    type = 3;
                    break;
                }
            case "other":
                {
                    type = 4;
                    break;
                }
            default:
                {
                    type = 4;
                }
        }
        let body = {
            username:document.getElementById("inputUsername").value,
            amount:document.getElementById("inputAmount").value,
            type:type,
            desc:document.getElementById("inputDesc").value,
            date:Date.now()
        }

        console.log(body);
        let response = await fetch(url + createTicket, {
            method: "POST",
            body: JSON.stringify(body),
            credentials:"include"
        });
    }
}
async function getCompleteTicketsFunc()
{
    console.log("hi from complete tickets :)")

    let response = await fetch(url + compl, {
        method: "GET",
        body: null,
        credentials: "include"
    });

    if(response.status === 200)
    {
        let data = await response.json();

        console.log(data)

        sendData(data);
    }
}

async function getPendingTicketsFunc()
{
    console.log("hi from pending tickets :)")

    let response = await fetch(url + pend, {
        method: "GET",
        body: null,
        credentials: "include"
    });

    if(response.status === 200)
    {
        let data = await response.json();

        console.log(data)

        sendDataPending(data);
    }
}

/************************************************************SEND DATA FUNCTIONS************************************************************/
//These should send data to the populate functions
function sendData(data)
{
    console.log("hello from send data :)")
    // console.log(data[0])
    // console.log(data[1])
    removeElementsByClass("decideButton");
    removeElementsByClass("headerColumn");
    removeElementsByClass("bodyColumn");
    removeElementsByClass("rowHeader");
    removeElementsByClass("rowBody");
    populateHeader();
    

    for(let i=0;i<data.length;i++)
    {
        populateData(data[i])
    }

}

function sendDataPending(data)
{
    console.log("hello from send data :)")
    // console.log(data[0])
    // console.log(data[1])
    removeElementsByClass("decideButton");
    removeElementsByClass("headerColumn");
    removeElementsByClass("bodyColumn");
    removeElementsByClass("rowHeader");
    removeElementsByClass("rowBody");
    populateHeader();
    

    for(let i=0;i<data.length;i++)
    {
        populateDataPending(data[i])
    }

}

/************************************************************POPULATE DATA FUNCTIONS************************************************************/
function populateHeader()
{
    let dataSection = document.getElementById("ticketTableHeader");

    let rowTag = document.createElement("div");
    let idTag = document.createElement("div");
    let ammountTag = document.createElement("div");
    let submitDateTag = document.createElement("div");
    let resolveDateTag = document.createElement("div");
    let authorTag = document.createElement("div");
    let resolverTag = document.createElement("div");
    let statusTag = document.createElement("div");
    let typeTag = document.createElement("div");
    let descTag = document.createElement("div");

    rowTag.classList.add("row", "rowHeader");
    idTag.classList.add("col-1", "headerColumn");
    ammountTag.classList.add("col-1", "headerColumn");
    submitDateTag.classList.add("col-1", "headerColumn");
    resolveDateTag.classList.add("col-1", "headerColumn");
    authorTag.classList.add("col-1", "headerColumn");
    resolverTag.classList.add("col-1", "headerColumn");
    statusTag.classList.add("col-1", "headerColumn");
    typeTag.classList.add("col-1", "headerColumn");
    descTag.classList.add("col-4", "headerColumn");

    idTag.innerHTML ="Ticket ID";
    ammountTag.innerHTML = "Amount";
    submitDateTag.innerHTML = "Date Submited";
    resolveDateTag.innerHTML = "Date Resolved";
    authorTag.innerHTML = "Author";
    resolverTag.innerHTML = "Resolver";
    statusTag.innerHTML = "Status";
    typeTag.innerHTML = "Type";
    descTag.innerHTML = "Description";

    rowTag.innerHTML += idTag.outerHTML
    rowTag.innerHTML += ammountTag.outerHTML
    rowTag.innerHTML += submitDateTag.outerHTML
    rowTag.innerHTML += resolveDateTag.outerHTML
    rowTag.innerHTML += authorTag.outerHTML
    rowTag.innerHTML += resolverTag.outerHTML
    rowTag.innerHTML += statusTag.outerHTML
    rowTag.innerHTML += typeTag.outerHTML
    rowTag.innerHTML += descTag.outerHTML

    dataSection.appendChild(rowTag);
}

function populateData(data)
{
    if(data != null)
    {
        let dataSection = document.getElementById("ticketTableBody");

        //create the row that the columns will go into
        let rowTag = document.createElement("div");
        let idTag = document.createElement("div");
        let ammountTag = document.createElement("div");
        let submitDateTag = document.createElement("div");
        let resolveDateTag = document.createElement("div");
        let authorTag = document.createElement("div");
        let resolverTag = document.createElement("div");
        let statusTag = document.createElement("div");
        let typeTag = document.createElement("div");
        let descTag = document.createElement("div");
        
        rowTag.classList.add("row", "rowBody");
        idTag.classList.add("col-1", "bodyColumn");
        ammountTag.classList.add("col-1", "bodyColumn");
        submitDateTag.classList.add("col-1", "bodyColumn");
        resolveDateTag.classList.add("col-1", "bodyColumn");
        authorTag.classList.add("col-1", "bodyColumn");
        resolverTag.classList.add("col-1", "bodyColumn");
        statusTag.classList.add("col-1", "bodyColumn");
        typeTag.classList.add("col-1", "bodyColumn");
        descTag.classList.add("col-4", "bodyColumn");


        idTag.innerHTML = data.id;
        ammountTag.innerHTML = "$" + data.amount;

        //this was a fun thing to figure out :)
        let intermediary1 = data.dateSubmitted;
        let intermediary2 = data.dateResolved;

        //if the date is not null...
        if(intermediary1 != null)
        {
            let submitDate = new Date(intermediary1);
            submitDateTag.innerHTML = submitDate.toDateString();
        }
        else//date is null
        {
            submitDateTag.innerHTML = "N/A";
        }
        //if the date is not null...
        if(intermediary1 != null)
        {
            let resolveDate = new Date(intermediary2);
            resolveDateTag.innerHTML = resolveDate.toDateString();
        }
        else//date is null
        {
            resolveDateTag.innerHTML = "N/A";
        }

        authorTag.innerHTML = data.author.username;
        if(data.resolver === null)
        {
            resolverTag.innerHTML = "N/A";
        }
        else
        {
            resolverTag.innerHTML = data.resolver.username;
        }
        
        statusTag.innerHTML = data.status.status;
        typeTag.innerHTML = data.type.type;
        descTag.innerHTML = data.desc;

        rowTag.innerHTML += idTag.outerHTML
        rowTag.innerHTML += ammountTag.outerHTML
        rowTag.innerHTML += submitDateTag.outerHTML
        rowTag.innerHTML += resolveDateTag.outerHTML
        rowTag.innerHTML += authorTag.outerHTML
        rowTag.innerHTML += resolverTag.outerHTML
        rowTag.innerHTML += statusTag.outerHTML
        rowTag.innerHTML += typeTag.outerHTML
        rowTag.innerHTML += descTag.outerHTML
        // dataSection.appendChild(rowTag);
        // dataSection.appendChild(idTag);
        // dataSection.appendChild(ammountTag);
        // dataSection.appendChild(submitDateTag);
        // dataSection.appendChild(resolveDateTag);
        // dataSection.appendChild(authorTag);
        // dataSection.appendChild(resolverTag);
        // dataSection.appendChild(statusTag);
        // dataSection.appendChild(typeTag);
        // dataSection.appendChild(descTag);

        // console.log("descTag = " + descTag.outerHTML)
        // console.log("rowTag = " + rowTag.outerHTML)

        dataSection.appendChild(rowTag);
    }
    else
    {
        //do nothing :)
    }
}

function populateDataPending(data)
{
    if(data != null)
    {
        let dataSection = document.getElementById("ticketTableBody");

        //create the row that the columns will go into
        let rowTag = document.createElement("div");
        let idTag = document.createElement("div");
        let ammountTag = document.createElement("div");
        let submitDateTag = document.createElement("div");
        let resolveDateTag = document.createElement("div");
        let authorTag = document.createElement("div");
        let resolverTag = document.createElement("div");
        let statusTag = document.createElement("div");
        let typeTag = document.createElement("div");
        let descTag = document.createElement("div");
        // let approveBtnTag = document.createElement("button")
        // let denyBtnTag = document.createElement("button")
        
        rowTag.classList.add("row", "rowBody");
        idTag.classList.add("col-1", "bodyColumn");
        ammountTag.classList.add("col-1", "bodyColumn");
        submitDateTag.classList.add("col-1", "bodyColumn");
        resolveDateTag.classList.add("col-1", "bodyColumn");
        authorTag.classList.add("col-1", "bodyColumn");
        resolverTag.classList.add("col-1", "bodyColumn");
        statusTag.classList.add("col-1", "bodyColumn");
        typeTag.classList.add("col-1", "bodyColumn");
        descTag.classList.add("col-4", "bodyColumn");
        // approveBtnTag.classList.add("col-1", "decideButton", "btn-success");
        // denyBtnTag.classList.add("col-1", "decideButton", "btn-danger");
        // approveBtnTag.setAttribute("id", "approveBtn");
        // denyBtnTag.setAttribute("id", "denyBtn");

        idTag.innerHTML = data.id;
        ammountTag.innerHTML = "$" + data.amount;

        //this was a fun thing to figure out :)
        let intermediary1 = data.dateSubmitted;
        let intermediary2 = data.dateResolved;

        //if the date is not null...
        if(intermediary1 != null)
        {
            let submitDate = new Date(intermediary1);
            submitDateTag.innerHTML = submitDate.toDateString();
        }
        else//date is null
        {
            submitDateTag.innerHTML = "N/A";
        }
        //if the date is not null...
        if(intermediary1 != null)
        {
            let resolveDate = new Date(intermediary2);
            resolveDateTag.innerHTML = resolveDate.toDateString();
        }
        else//date is null
        {
            resolveDateTag.innerHTML = "N/A";
        }
        

        if(data.resolver === null)
        {
            resolverTag.innerHTML = "N/A";
        }
        else
        {
            resolverTag.innerHTML = data.resolver.username;
        }
        authorTag.innerHTML = data.author.username;
        statusTag.innerHTML = data.status.status;
        typeTag.innerHTML = data.type.type;
        descTag.innerHTML = data.desc;
        // approveBtnTag.innerHTML = "Approve";
        // denyBtnTag.innerHTML = "Deny";

        rowTag.innerHTML += idTag.outerHTML
        rowTag.innerHTML += ammountTag.outerHTML
        rowTag.innerHTML += submitDateTag.outerHTML
        rowTag.innerHTML += resolveDateTag.outerHTML
        rowTag.innerHTML += authorTag.outerHTML
        rowTag.innerHTML += resolverTag.outerHTML
        rowTag.innerHTML += statusTag.outerHTML
        rowTag.innerHTML += typeTag.outerHTML
        rowTag.innerHTML += descTag.outerHTML
        // rowTag.innerHTML += approveBtnTag.outerHTML
        // rowTag.innerHTML += denyBtnTag.outerHTML
        // dataSection.appendChild(rowTag);
        // dataSection.appendChild(idTag);
        // dataSection.appendChild(ammountTag);
        // dataSection.appendChild(submitDateTag);
        // dataSection.appendChild(resolveDateTag);
        // dataSection.appendChild(authorTag);
        // dataSection.appendChild(resolverTag);
        // dataSection.appendChild(statusTag);
        // dataSection.appendChild(typeTag);
        // dataSection.appendChild(descTag);

        // console.log("descTag = " + descTag.outerHTML)
        // console.log("rowTag = " + rowTag.outerHTML)

        dataSection.appendChild(rowTag);
    }
    else
    {
        //do nothing :)
    }
}

/************************************************************MISC FUNCTIONS************************************************************/
function removeElementsByClass(className){
    const elements = document.getElementsByClassName(className);
    while(elements.length > 0){
        elements[0].parentNode.removeChild(elements[0]);
    }
}