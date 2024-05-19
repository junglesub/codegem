import { useEffect, useState } from "react";
import "./App.css";
import FeedItem from "./components/FeedItem";

function App() {
  const [allFeeds, setAllFeed] = useState([]);

  const getAllData = async () => {
    const data = await (await fetch("/api/feed/get")).json();
    setAllFeed(data);
  };

  useEffect(() => {
    // Load Item
    getAllData();
  }, []);

  return (
    <div className="App">
      <div className="header">
        <h1>Handong Feed</h1>
      </div>
      <div className="create">
        <form
          onSubmit={(e) => {
            e.preventDefault();
            const { title, content, author } = e.target;
            fetch("/api/feed", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                title: title.value,
                content: content.value,
                author: author.value,
              }),
            }).then(() => {
              e.target.reset();
              getAllData();
            });
          }}
        >
          <div className="field-title">
            <input name="title" required placeholder="title" />
          </div>
          <div className="field-content">
            <textarea name="content" placeholder="content"></textarea>
          </div>
          <div className="field-author">
            <input name="author" required placeholder="author" type="text" />
          </div>
          <div className="field-btn">
            <button type="submit">등록</button>
          </div>
        </form>
      </div>
      <div className="list">
        {allFeeds.length === 0 && <div>No Entry..</div>}
        {allFeeds
          .sort((a, b) => (a.createdAt < b.createdAt ? 1 : -1))
          .map((item) => (
            <FeedItem item={item} getAllData={getAllData} />
          ))}
      </div>
    </div>
  );
}

export default App;
