const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');
const cors = require('cors');

const app = express();
app.use(bodyParser.json());

// 👇️ Configure CORS
app.use(cors());
// Create the Express app
const port = 3000;


// Serve static files (optional)
app.use(express.static('public'));

// Endpoint to add a new book
app.post('/books', (req, res) => {
    const { title, authors, isbn, genre, image,borrowed} = req.body;



    if (!title || !isbn || !authors || !image || authors.length === 0) {
       return res.status(400).json({ error: 'Missing required fields' });
    }
        console.log('Received book:', { title, authors, isbn, genre, image, borrowed });

    const newBook = { title, authors, isbn, genre, image, borrowed};
    let books = [];

    try {
        const data = fs.readFileSync('Books.json', 'utf8');
        books = JSON.parse(data);
    } catch {
        console.log('Creating a new Books.json file.');
    }
    const duplicate = books.find(book => book.isbn === isbn);
    if (books.find(book => book.isbn === isbn)) {
        return res.status(400).json({ error: 'A book with this ISBN already exists' });
    }
    books.push(newBook);

    fs.writeFileSync('Books.json', JSON.stringify(books, null, 2));
    res.status(201).json({ message: 'Book added successfully', newBook });
});
app.patch('/updateBorrowStatus', (req, res) => {
    const { isbn, borrowed } = req.body;

    if (!isbn || typeof borrowed !== 'boolean') {
        return res.status(400).json({ error: 'Missing required fields or invalid data' });
    }

    try {
        // Read the current books data from the file
        const data = fs.readFileSync('Books.json', 'utf8');
        let books = JSON.parse(data);

        // Find the book by ISBN and update its 'borrowed' status
        const bookIndex = books.findIndex(book => book.isbn === isbn);
        if (bookIndex == -1) {
            return res.status(404).json({ error: 'Book not found' });
        }

        // Update the borrowed status of the book
        books[bookIndex].borrowed = borrowed;

        // Write the updated books array back to the file
        fs.writeFileSync('Books.json', JSON.stringify(books, null, 2));

        res.status(200).json({ message: 'Book borrowed status updated successfully' });
    } catch (error) {
        res.status(500).json({ error: 'Failed to update borrowed status' });
    }
});


// Start the server
app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});

