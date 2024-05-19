import React from "react";

function formatTimestamp(timestamp) {
  // Parse the timestamp into a Date object
  const date = new Date(timestamp);

  // Extract the parts of the date
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0"); // Months are zero-based
  const day = String(date.getDate()).padStart(2, "0");
  const hours = String(date.getHours()).padStart(2, "0");
  const minutes = String(date.getMinutes()).padStart(2, "0");

  // Format the parts into the desired format
  return `${year}/${month}/${day} ${hours}:${minutes}`;
}

function FeedItem({ item, getAllData }) {
  return (
    <div className="FeedItem">
      <div className="moreinfo">
        <div className="author">{item.author}</div>
        <div className="rightflx">
          <div className="date">{formatTimestamp(item.createdAt)}</div>
          <button
            onClick={() => {
              fetch(`/api/feed/${item.id}`, { method: "DELETE" })
                .then(getAllData)
                .catch((e) => alert(e));
            }}
          >
            삭제
          </button>
        </div>
      </div>
      <div className="title">{item.title}</div>
      <div className="content">{item.content}</div>
      <div className="deletebtn"></div>
    </div>
  );
}

export default FeedItem;
