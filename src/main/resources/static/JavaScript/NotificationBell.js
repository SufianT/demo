document.addEventListener("DOMContentLoaded", async () => {
    const token = localStorage.token;
    const bellIconContainer = document.getElementById("notificationIcon");

    try {
        // Fetch the unread notification status from the backend
        const response = await fetch(`/notification/hasUnread?token=${token}`);
        const result = await response.json();

        if (result.success) {
            const hasUnread = result.hasUnread;

            if (!hasUnread) {
                // Default bell icon (no unread notifications)
                bellIconContainer.innerHTML = `
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                                 class="bi bi-bell" viewBox="0 0 16 16">
                                <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2M8 1.918l-.797.161A4 4 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4 4 0 0 0-3.203-3.92zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5 5 0 0 1 13 6c0 .88.32 4.2 1.22 6"/>
                    </svg>`;
            } else {
                bellIconContainer.innerHTML = `
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                        class="bi bi-bell-fill" viewBox="0 0 16 16">
                        <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2m.995-14.901a1 1 0 1 0-1.99 0A5 5 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901"/>
                        </svg>`;
            }
        } else {
            console.error("Failed to fetch unread status:", result.message);
        }
    } catch (error) {
        console.error("Error checking notifications:", error);
    }
});
