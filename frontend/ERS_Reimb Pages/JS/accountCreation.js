

//landing page for the website
const url = "http://localhost:8080/P1-markratib/"

document.getElementById("submitButton").addEventListener("click", accountCreationFunc);

document.getElementById("returnButton").addEventListener("click", returnFunc)

async function accountCreationFunc()
{
    let usern = document.getElementById("inputUsername").value;
    let userp = document.getElementById("inputPassword").value;
    let userfn = document.getElementById("inputFirstName").value;
    let userln = document.getElementById("inputLastName").value;
    let userem = document.getElementById("inputEmail").value;
    let userr = document.getElementById("inputRole").value;

    //check if the user inputed something to all the fields, if they didnt...
    if((usern === "") || (userp === "") || (userfn === "") || (userln === "") || (userem === ""))
    {
        console.log("i got here :)")
    }
    else//the user inputed all needed information
    {
        let newUser = {
            username:usern,
            password:userp,
            firstname:userfn,
            lastname:userln,
            email:userem,
            role:userr
        }

        console.log(JSON.stringify(newUser));

        let response = await fetch(url + 'accountCreation', {
            method: "POST",
            body: JSON.stringify(newUser),
            credentials: "include"
        })
        //status 201 is account created successfully
        if(response.status === 201)
        {
            window.location.href = "./index.html";
        }
    }
}

function returnFunc()
{
    window.location.href = "./index.html";
}



