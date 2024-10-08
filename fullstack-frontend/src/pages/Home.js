import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom';

export default function Home() {

    const [news, setNews] = useState([])

    useEffect(() => {
        loadNews();
    }, []);

    const loadNews = async () => {
        const result = await axios.get("http://localhost:8077/news");
        setNews(result.data);
    };

    const deleteNews = async (id) => {
        await axios.delete(`http://localhost:8077/news/${id}`);
        loadNews();
    }

    return (
        <div className='container'>
            <div className='py-4'>
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
                        {
                            news.map((n, index) => (
                                <tr>
                                    <th scope="row" key={index}>{index+1}</th>
                                    <td>{n.id}</td>
                                    <td>{n.title}</td>
                                    <td>{n.summary}</td>
                                    <td>{n.content}</td>
                                    <td>
                                        <Link className='btn btn-primary mx-2' to={`/viewnews/${n.id}`}>View</Link>
                                        <Link className='btn btn-outline-primary mx-2' to = {`/editnews/${n.id}`}>Edit</Link>
                                        <button className='btn btn-danger mx-2' onClick={() => deleteNews(n.id)}>Delete</button>
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}
