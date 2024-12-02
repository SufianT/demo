app.use(bodyParser.json());
const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');
const cors = require('cors');

const app = express();

// 👇️ Configure CORS
app.use(cors());
// Create the Express app
const port = 3000;

// Middleware


// Serve static files (optional)
app.use(express.static('public'));

// Endpoint to add a new book
app.post('/books', (req, res) => {
    const { title, author, isbn, genre} = req.body;



    if (!title || !author || !isbn || !genre) {
       return res.status(400).json({ error: 'Missing required fields' });
    }

    const newBook = { title, author, isbn, genre };
    let books = [];

    try {
        const data = fs.readFileSync('Books.json', 'utf8');
        books = JSON.parse(data);
    } catch {
        console.log('Creating a new Books.json file.');
    }
    const duplicate = books.find(book => book.isbn === isbn);

    books.push(newBook);

    fs.writeFileSync('Books.json', JSON.stringify(books, null, 2));
    res.status(201).json({ message: 'Book added successfully', newBook });
});

// Start the server
app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});

