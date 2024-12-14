async function generateBookCards() {
    try {
        const response = await fetch("books");
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        console.log("File fetched successfully:", data);

        const container = document.querySelector('#book-container');

        data.forEach(book => {
            const title = book.title;
            const isbn = book.isbn;
            const genre = book.genre;
            const image = book.image;
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
                            <button class="btn btn-primary" onclick="showBookModal('${title}', '${isbn}', '${image}','Author: ${authors}\\nGenre: ${genre}', '${borrowed}' )">View Details</button>
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
function showBookModal(title, isbn, imageUrl, details, borrowed) {
    document.getElementById('modalTitle').innerText = title;
    document.getElementById('modalImage').src = imageUrl;
    document.getElementById('modalDetails').innerText = details;


    // Show the modal
    const modal = document.getElementById("bookModal");
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

    // Check the borrow-statue of the book and change the borrow/return button as needed
    const borrowButton = document.getElementById('borrowBtn');
    updateBorrowButtonStatus();

    // Borrow button status logic
    function updateBorrowButtonStatus() {
        if (borrowed === "true") {
            borrowButton.innerText = 'Return';
            borrowButton.onclick = function () {
                returnBook(isbn);
            };

        } else {
            borrowButton.innerText = 'Borrow';
            borrowButton.onclick = function () {
                borrowBook(isbn);
            };
        }
    }

}
async function borrowBook(isbn) {
    const userId = localStorage.getItem("userId"); // Retrieve user ID from storage
    try {
        const response = await fetch('/borrow', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ isbn, userId }),
        });

        const result = await response.json();
        if (result.success) {
            alert(result.message);
            const borrowButton = document.getElementById('borrowBtn');
            borrowButton.innerText = 'Return';
            borrowButton.onclick = function () {
                returnBook(isbn);
            };
        } else {
            alert(result.message);
        }
    } catch (error) {
        console.error("Error borrowing the book:", error);
    }
}

async function returnBook(isbn) {
    try {
        const response = await fetch('/return', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ isbn }),
        });

        const result = await response.json();
        if (result.success) {
            alert(result.message);
            document.getElementById('bookModal').style.display = 'none';
        } else {
            alert(result.message);
        }
    } catch (error) {
        console.error("Error returning the book:", error);
    }
}

function loggedOutBorrow() {
    alert("Please log in to start borrowing books");
}

generateBookCards();