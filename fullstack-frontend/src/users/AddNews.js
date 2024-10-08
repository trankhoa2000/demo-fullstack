import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'

export default function AddNews() {
    let navigate = useNavigate();

    const [news, setNews] = useState({
        title: "",
        summary: "",
        content: "",
    });

    const { title, summary, content } = news;

    const onInputChange = (e) => {
        setNews({ ...news, [e.target.name]: e.target.value });
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.post("http://localhost:8077/news", news);
        navigate("/");
      };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Create News</h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Title" className="form-label">Title</label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your title"
                                name="title"
                                value={title}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Summary" className="form-label">Summary</label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your summary"
                                name="summary"
                                value={summary}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Content" className="form-label">Content</label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your content"
                                name="content"
                                value={content}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">Submit</button>
                        <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    );
}
