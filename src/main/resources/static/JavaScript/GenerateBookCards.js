async function generateBookCards() {
    try {
        const response = await fetch('/books');
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json(); // Parse the JSON response
        console.log("File fetched successfully:", data);

        const container = document.querySelector('#book-container');

        data.forEach(book => {
            const title = book.title;
            const isbn=book.isbn;
            const genre = book.genre;
            const image=book.image;
            const authors = book.authors;


            // Create card HTML
            const cardHTML = `
                <div class="col">
                    <div class="card" style="height: 400px; width: 100%;"> <!-- Inline style to control size -->
                        <img src=${image} class="card-img-top img-fluid" alt="Book Cover">
                        <div class="card-body">
                            <h5 class="card-title">${title}</h5>
                            <p class="card-text">Author: ${authors}<br>Genre: ${genre}</p>
                        </div>
                        <div class="card-footer">
                            <button class="btn btn-primary" onclick="showBookModal('${title}', '${isbn}', '${image}','Author: ${authors}\\nGenre: ${genre}' )">View Details</button>
                        </div>
                    </div>
                </div>`;
            container.innerHTML += cardHTML; // Add the card to the container
        });
    } catch (error) {
        console.error("Failed to fetch the file:", error);
    }
}

// Add info to the bookModal
function showBookModal(title, isbn, imageUrl, details) {
    document.getElementById('modalTitle').innerText = title;
    document.getElementById('modalImage').src = imageUrl;
    document.getElementById('modalDetails').innerText = details;

    window.selectedBook = isbn;


    // Show the modal
    const modal = document.getElementById('bookModal');
    modal.style.display = "flex";

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


function loggedOutBorrow(){
alert("Please log in to start borrowing books");
}

generateBookCards();
