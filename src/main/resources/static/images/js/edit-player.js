document.addEventListener("DOMContentLoaded", () => {
    const editButtons = document.querySelectorAll(".edit-btn");
    const editForm = document.getElementById("editPlayerForm");

    const playerIdInput = document.getElementById("editPlayerId");
    const jerseySelect = document.getElementById("jerseyNumber");
    const positionSelect = document.getElementById("position");
    const roleSelect = document.getElementById("role");

    let currentPlayerId = null;

    // When clicking EDIT, populate modal fields
    editButtons.forEach(button => {
        button.addEventListener("click", () => {
            currentPlayerId = button.getAttribute("data-player-id");

            const currentJersey = button.getAttribute("data-jersey");
            const currentPosition = button.getAttribute("data-position");
            const currentStatus = button.getAttribute("data-status");

            playerIdInput.value = currentPlayerId;

            // ✅ Ensure player's current jersey is included in the dropdown
            if (jerseySelect && currentJersey) {
                let optionExists = Array.from(jerseySelect.options)
                    .some(opt => opt.value === currentJersey);

                if (!optionExists) {
                    // Add current jersey as first option and preselect it
                    const newOption = new Option(currentJersey, currentJersey, true, true);
                    jerseySelect.add(newOption, 0);
                } else {
                    // If it already exists, just select it
                    jerseySelect.value = currentJersey;
                }
            }

            // Preselect Position
            if (positionSelect) {
                positionSelect.value = currentPosition;
            }

            // Preselect Starter/Substitute
            if (roleSelect) {
                roleSelect.value = currentStatus === "true" ? "true" : "false";
            }
        });
    });

    // Handle PATCH form submission
    editForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const payload = {
            jerseyNumber: jerseySelect.value,
            position: positionSelect.value,
            isStarter: roleSelect.value
        };

        try {
            const response = await fetch(`/api/player/${currentPlayerId}`, {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                // Close modal and refresh page
                const modal = bootstrap.Modal.getInstance(document.getElementById("editPlayerModal"));
                modal.hide();
                window.location.reload();
            } else {
                console.error("Failed to update player", response.status);
                alert("Error updating player.");
            }
        } catch (err) {
            console.error("Patch request failed", err);
            alert("Network error. Try again later.");
        }
    });
});
