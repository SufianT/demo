async function generateBookCards() {
    try {
        const response = await fetch('../JavaScript/Books.json'); // Update the file path to the JSON file
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json(); // Parse the JSON response
        console.log("File fetched successfully:", data);

        const container = document.querySelector('#book-container');

        data.forEach(book => {
            // Assuming the JSON structure has keys: title, author, genre
            const title = book.title;
            const author = book.author;
            const isbn=book.isbn;
            const genre = book.genre;  // Adjust this if the structure of your JSON is different

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
                            <button class="btn btn-primary" onclick="showBookModal('${title}', '../static/images/bookImg.png','Author: ${author}\\nGenre: ${genre}')">View Details</button>
                        </div>
                    </div>
                </div>`;
            container.innerHTML += cardHTML; // Add the card to the container
        });
    } catch (error) {
        console.error("Failed to fetch the file:", error);
    }
}

function showBookModal(title, imageUrl, details) {
    document.getElementById('modalTitle').innerText = title;
    document.getElementById('modalImage').src = imageUrl; // Set the book's image
    document.getElementById('modalDetails').innerText = details;

    // Show the modal
    const modal = document.getElementById('bookModal');
    modal.style.display = 'block';

    const closeButton = document.querySelector('.close');
    closeButton.onclick = function() {
        modal.style.display = 'none';
    }
    const closeButton2 = document.getElementById('closeBtn');
        closeButton2.onclick = function() {
            modal.style.display = 'none';
        }


    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    }
}

generateBookCards();