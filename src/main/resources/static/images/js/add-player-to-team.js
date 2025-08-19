const form = document.querySelector("#addPlayerForm");

form.addEventListener("submit",async event =>{
    event.preventDefault();

    const playerId = document.getElementById("playerId").value.trim();
    const jerseyNumber = document.getElementById("jerseyNumber").value.trim();
    const position = document.getElementById("position").value.trim();

    const jsonBody = JSON.stringify({
        jerseyNumber: jerseyNumber,
        position: position
    })

    try{
        const response = await fetch(`/api/team/players/${playerId}`,{
            method: 'PATCH',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: jsonBody
        })

        if(response.status === 200){
            form.reset();

            window.location.href = `/coach/teamManager`;
        }else{
            const errorData = await response.json();
            alert(`Error adding player: ${errorData.message || 'Something went wrong when adding the player!'}`);
        }

    }catch (e){
        console.error('Network error:', e);
        alert("Oops, network error! Please try again.");
    }
})