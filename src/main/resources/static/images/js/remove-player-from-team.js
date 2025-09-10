import {csrfHeader, csrfToken} from "./utility/csrf.js";

const removeForms = document.querySelectorAll(".removeForm");

removeForms.forEach(form => {
    form.addEventListener("submit", async event => {
        event.preventDefault();

        const button = form.querySelector("button[data-player-id]");
        const playerId = button.getAttribute("data-player-id");

        if (!playerId) {
            console.error("Player ID missing from data attribute.");
            return;
        }

        if (!confirm("Are you sure you want to remove this player?")) {
            return;
        }

        try {
            const response = await fetch(`/api/team/players/${playerId}`, {
                method: 'DELETE',
                headers:{
                    [csrfHeader]: csrfToken
                }
            });

            if (response.status === 204) {
                const cardCol = form.closest(".col");
                cardCol.style.transition = "opacity 0.3s ease";
                cardCol.style.opacity = "0";
                setTimeout(() => cardCol.remove(), 300);
            } else {
                const errorData = await response.json();
                alert(`Error removing player: ${errorData.message || 'Something went wrong when removing the player!'}`);
            }

        } catch (e) {
            console.error('Network error:', e);
            alert("Oops, network error! Please try again.");
        }
    });
});
