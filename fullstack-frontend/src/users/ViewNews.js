import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewNews() {
  const [news, setNews] = useState({
    title: "",
    summary: "",
    content: "",
  });

  const { id } = useParams();

  useEffect(() => {
    loadNews();
  });

  const loadNews = async () => {
    const result = await axios.get(`http://localhost:8077/news/${id}`);
    setNews(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">News Details</h2>

          <div className="card">
            <div className="card-header">
              Details of news id : {news.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Title:</b>
                  {news.title}
                </li>
                <li className="list-group-item">
                  <b>Summary:</b>
                  {news.summary}
                </li>
                <li className="list-group-item">
                  <b>content:</b>
                  {news.content}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}