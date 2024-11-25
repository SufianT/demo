async function generateBookCards() {
    try {
        const response = await fetch('../JavaScript/booksDatabase.csv');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.text();
        console.log("File fetched successfully:", data);

        const rows = data.split('\n').slice(1); // Remove first row
        const container = document.querySelector('#book-container');

        rows.forEach(row => {
            // split CSV row
            const columns = row.match(/(".*?"|[^",]+)(?=,|$)/g);
            if (!columns) return; // Skip invalid rows

            const title = columns[0]?.replace(/"/g, '').trim();
            const author = columns[1]?.replace(/"/g, '').trim();
            const genre = columns[3]?.replace(/"/g, '').trim(); // Adjusted to fit actual CSV format

            // Create card HTML
            const cardHTML = `
                <div class="col">
                    <div class="card" style="height: 400px; width: 100%;"> <!-- Inline style to control size -->
                        <img src="../static/images/bookImg.png" class="card-img-top img-fluid" alt="Book Cover">
                        <div class="card-body">
                        <h5 class="card-title">${title}</h5>
                            <p class="card-text">Author: ${author}<br>Genre: ${genre}</p>
                        </div>
                        <div class="card-footer">
                            <button class="btn btn-primary" onclick="showBookModal('${title}', 'Author: ${author}\\nGenre: ${genre}')">View Details</button>
                        </div>
                    </div>
                </div>`;
            container.innerHTML += cardHTML;
        });
    } catch (error) {
        console.error("Failed to fetch the file:", error);
    }
}

function showBookModal(title, details) {
    alert(`Book Details:\n\nTitle: ${title}\n${details}`);
}

generateBookCards();