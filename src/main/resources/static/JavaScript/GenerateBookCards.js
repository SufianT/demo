// Fetch and display books initially
async function generateBookCards() {
    try {
        const response = await fetch('/books');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json(); // Parse the JSON response
        console.log("File fetched successfully:", data);

        updateBookCards(data); // Initial display of all books
    } catch (error) {
        console.error("Failed to fetch the books:", error);
    }
}

// Update book cards (used for both initial display and search results)
function updateBookCards(books) {
    const container = document.querySelector('#book-container');
    container.innerHTML = ''; // Clear existing cards

    books.forEach(book => {
        const title = book.title;
        const isbn = book.isbn;
        const genre = book.genre;
        const image = book.image;
        const authors = book.authors;

        // Create card HTML
        const cardHTML = `
            <div class="col">
                <div class="card" style="height: 400px; width: 100%;"> <!-- Inline style to control size -->
                    <img src="${image}" class="card-img-top img-fluid" alt="Book Cover">
                    <div class="card-body">
                        <h5 class="card-title">${title}</h5>
                        <p class="card-text">Author: ${authors}<br>Genre: ${genre}</p>
                    </div>
                    <div class="card-footer">
                        <button class="btn btn-primary" onclick="showBookModal('${title}', '${isbn}', '${image}', 'Author: ${authors}\\nGenre: ${genre}')">View Details</button>
                    </div>
                </div>
            </div>`;
        container.innerHTML += cardHTML; // Add the card to the container
    });
}

// Add search functionality
async function handleSearch() {
    const query = document.getElementById('searchInput').value.trim(); // Get the search query

    try {
        const response = await fetch(`/search?query=${encodeURIComponent(query)}`); // Call backend search endpoint
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const searchResults = await response.json(); // Get search results
        updateBookCards(searchResults); // Update cards based on search results
    } catch (error) {
        console.error("Failed to fetch the search results:", error);
    }
}

// Add info to the bookModal (unchanged)
function showBookModal(title, isbn, imageUrl, details) {
    document.getElementById('modalTitle').innerText = title;
    document.getElementById('modalImage').src = imageUrl;
    document.getElementById('modalDetails').innerText = details;

    window.selectedBook = isbn;

    // Show the modal
    const modal = document.getElementById('bookModal');
    modal.style.display = "flex";

    const closeButton = document.querySelector('.close');
    closeButton.onclick = function () {
        modal.style.display = 'none';
    }
    const closeButton2 = document.getElementById('closeBtn');
    closeButton2.onclick = function () {
        modal.style.display = 'none';
    }

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    }
}

// Fetch initial books and initialize event listener
generateBookCards();
document.getElementById('searchButton').addEventListener('click', handleSearch);
