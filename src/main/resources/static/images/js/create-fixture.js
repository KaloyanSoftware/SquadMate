document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("createMatchForm");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        // Collect form values
        const matchDate = document.getElementById("matchDate").value;
        const location = document.getElementById("location").value.trim();
        const team2 = document.getElementById("opponentTeam").value.trim();

        // Build DTO object
        const addMatchDTO = {
            matchDate: matchDate,
            location: location,
            opponentTeam: team2
        };

        try {
            const response = await fetch("/api/match", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify(addMatchDTO)
            });

            if (response.status === 201) {
                showAlert("Match created successfully!", "success");

                // Optionally reset form
                form.reset();

                window.location.href = `/coach/fixtures`;
            } else {
                const errorText = await response.text();
                showAlert(`Error: ${errorText}`, "danger");
            }
        } catch (error) {
            console.error("Error creating match:", error);
            showAlert("An unexpected error occurred. Please try again.", "danger");
        }
    });

    /**
     * Helper function to display Bootstrap alerts dynamically
     */
    function showAlert(message, type) {
        const alertPlaceholder = document.createElement("div");
        alertPlaceholder.innerHTML = `
            <div class="alert alert-${type} alert-dismissible fade show mt-3" role="alert">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        `;
        form.parentNode.insertBefore(alertPlaceholder, form.nextSibling);
    }
});
