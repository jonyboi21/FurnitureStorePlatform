import axios from 'axios';

const API_URL = 'https://api.example.com/books'; // Replace with your actual API URL

export const fetchBooks = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching books:', error);
        throw error;
    }
};

export const fetchBookById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching book with id ${id}:`, error);
        throw error;
    }
};

// Additional service functions can be added here as needed.