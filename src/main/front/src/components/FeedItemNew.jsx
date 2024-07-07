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

function FeedItemNew({ item, getAllData }) {
  return (
    <div className="FeedItem">
      <div className="moreinfo">
        <div className="leftflx">
          <div className="author">{item.author}</div>
          <div className="date">{formatTimestamp(item.createdAt)}</div>
        </div>
        <div className="rightflx">
          <button onClick={() => {}}>Bookmark</button>
        </div>
      </div>
      <div className="content">{item.content}</div>
      <div className="bottomMenu">
        <div className="like">좋아요</div>
        <div className="comment">댓글달기</div>
        <div className="share">공유하기</div>
      </div>
    </div>
  );
}

export default FeedItemNew;
