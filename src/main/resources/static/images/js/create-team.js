import {csrfHeader, csrfToken} from "./utility/csrf.js";

const form = document.getElementById('createTeamForm');

form.addEventListener("submit", async event => {
    event.preventDefault();

    const teamName = document.getElementById('teamName').value.trim();

    // Validate that team name is not empty
    if (!teamName) {
        alert("Please enter a team name!");
        return;
    }

    const jsonBody = JSON.stringify({
        teamName: teamName
    });

    try {
        const response = await fetch(`/api/team`, {
            method: 'POST',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                [csrfHeader]: csrfToken
            },
            body: jsonBody
        });

        if (response.status === 201) {
            // Clear the form
            form.reset();
            // Redirect to team manager
            window.location.href = `/coach/teamManager`;
        } else {
            const errorData = await response.json();
            alert(`Error creating team: ${errorData.message || 'Something went wrong when creating a team!'}`);
        }

    } catch (e) {
        console.error('Network error:', e);
        alert("Oops, network error! Please try again.");
    }
});
