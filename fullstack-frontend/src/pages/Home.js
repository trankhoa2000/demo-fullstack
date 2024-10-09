import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Home() {
    const [news, setNews] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [searchTitle, setSearchTitle] = useState("");
    const [searchTime, setSearchTime] = useState(null);

    const pageSize = 10;

    useEffect(() => {
        if (!searchTitle) {
            loadNews();
        }
    }, [page, searchTitle]);

    const loadNews = async () => {
        const result = await axios.get(`http://localhost:8077/news?page=${page}&size=${pageSize}`);
        setNews(result.data.content);
        setTotalPages(result.data.totalPages);
    };

    const handleSearch = async (e) => {
        const searchValue = e.target.value.toLowerCase();
        setSearchTitle(searchValue);

        if (searchValue) {
            try {
                const result = await axios.get(`http://localhost:8077/search?title=${searchValue}`);
                const { newsList, timeTaken } = result.data;

                setNews(newsList);
                setTotalPages(1);
                setPage(0);
                setSearchTime(timeTaken);
            } catch (error) {
                console.error("Error fetching news:", error);
            }
        } else {
            loadNews();
            setSearchTime(null);
        }
    };

    const deleteNews = async (id) => {
        await axios.delete(`http://localhost:8077/news/${id}`);
        loadNews();
    };


    const handlePreviousPage = () => {
        if (page > 0) setPage(page - 1);
    };

    const handleNextPage = () => {
        if (page < totalPages - 1) setPage(page + 1);
    };

    return (
        <div className='container'>
            <div className='py-4'>
                <div className='mb-4'>
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search by title..."
                        value={searchTitle}
                        onChange={handleSearch}
                    />
                </div>

                {searchTime !== null && <div>Time taken for search: {searchTime} ms</div>}

                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Id</th>
                            <th scope="col">Title</th>
                            <th scope="col">Summary</th>
                            <th scope="col">Content</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {news.map((n, index) => (
                            <tr key={n.id}>
                                <th scope="row">{index + 1}</th>
                                <td>{n.id}</td>
                                <td>{n.title}</td>
                                <td>{n.summary}</td>
                                <td>{n.content}</td>
                                <td>
                                    <Link className='btn btn-primary mx-2' to={`/viewnews/${n.id}`}>View</Link>
                                    <Link className='btn btn-outline-primary mx-2' to={`/editnews/${n.id}`}>Edit</Link>
                                    <button className='btn btn-danger mx-2' onClick={() => deleteNews(n.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                {!searchTitle && (
                    <div className="d-flex justify-content-between">
                        <button className="btn btn-secondary" disabled={page === 0} onClick={handlePreviousPage}>
                            Previous
                        </button>
                        <span>Page {page + 1} of {totalPages}</span>
                        <button className="btn btn-secondary" disabled={page === totalPages - 1} onClick={handleNextPage}>
                            Next
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
}
