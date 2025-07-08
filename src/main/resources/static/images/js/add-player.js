const form = document.querySelector("#addPlayerForm");

form.addEventListener("submit",async event =>{
    event.preventDefault();

    const playerId = document.querySelector("#playerId").value;
    const jerseyNumber = document.querySelector("#jerseyNumber").value;
    const position = document.querySelector("#position").value;

    const jsonBody = JSON.stringify({
        jerseyNumber: jerseyNumber,
        position: position,
    })

    try{
        const response = await fetch(`/api/player/${playerId}`,{
            method: 'PATCH',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: jsonBody
        })

        if(response.status === 200){
            window.location = `/coach/teamManager`;
        }else{
            alert("Something went wrong when adding a player to the team!")
        }

    }catch (e){
        alert("Oops, network error!")
    }
})