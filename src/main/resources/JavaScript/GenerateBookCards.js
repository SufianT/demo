async function generateBookCards() {
    try {
        const response = await fetch('../JavaScript/Books.json'); // file path to the JSON file
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json(); // Parse the JSON response
        console.log("File fetched successfully:", data);

        const container = document.querySelector('#book-container');

        data.forEach(book => {
            const title = book.title;
            const authors = book.authors;
            const isbn=book.isbn;
            const genre = book.genre;
            const image=book.image;
            const borrowed =book.borrowed;

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

    const borrowButton = document.getElementById('borrowBtn');
    updateBorrowButtonStatus();
    console.log(borrowed);
    // Check the borrow-statue of the book and change the borrow/return button as needed
    function updateBorrowButtonStatus() {
            if (borrowed== "true") {
                borrowButton.innerText = 'Return';
                borrowButton.onclick = function() {
                    returnBook();
                };
            } else {
                borrowButton.innerText = 'Borrow';
                borrowButton.onclick = function() {
                    borrowBook();
                };
            }

    }

        async function borrowBook() {
                   try {
                       // Send a request to update the borrowed status in the backend
                       const response = await fetch('http://localhost:3000/updateBorrowStatus', {
                           method: 'PATCH', // Use PATCH for partial update
                           headers: {
                               'Content-Type': 'application/json'
                           },
                           body: JSON.stringify({
                               isbn: isbn,
                               borrowed: true
                           })
                       });

                       if (response.ok) {
                           alert(`'${title}' is borrowed.`);
                           borrowed= "true";
                          showBookModal(title, isbn, imageUrl, details, borrowed);


                       } else {
                           const errorData = await response.json();
                           alert(`Error: ${errorData.error}`);
                       }
                   } catch (error) {
                       console.error('Error updating borrowed status:', error);
                       alert('Failed to borrow the book.');
                   }
               }


       async function returnBook()  {
           try {
               const response = await fetch('http://localhost:3000/updateBorrowStatus', {
                   method: 'PATCH',
                   headers: {
                       'Content-Type': 'application/json',
                   },
                   body: JSON.stringify({
                       isbn: isbn,
                       borrowed: false
                   }),
               });

               if (!response.ok) {
                   throw new Error(`Failed to return book with ISBN: ${isbn}`);
               }

               const data = await response.json();
               console.log(data.message);
               alert(`'${title}' has been returned.`);
               borrowed="false";
               showBookModal(title, isbn, imageUrl, details, borrowed);


           } catch (error) {
               console.error("Error returning book:", error);
           }
       }
}

function loggedOutBorrow(){
alert("Please log in to start borrowing books");
}

generateBookCards();
