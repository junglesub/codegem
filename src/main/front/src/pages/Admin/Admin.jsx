import React, { useEffect, useState } from "react";
import "./Admin.scss";
import AdminBlock from "./AdminBlock";

function Admin() {
  const [orgDatas, setOrgDatas] = useState([]);
  const [newData, setNewDatas] = useState({});

  const getNewData = () => {
    fetch("/api/feed/get").then((data) =>
      data.json().then((json) =>
        setNewDatas(
          json.reduce((prev, curr) => {
            prev[curr.id] = { ...curr };
            return prev;
          }, {})
        )
      )
    );
  };

  useEffect(() => {
    fetch("/api/kafeed/all").then((data) =>
      data.json().then((json) => setOrgDatas(json))
    );
    getNewData();
  }, []);

  const updateFeed = (id, toUpdate) => {
    if (!toUpdate) {
      fetch(`/api/feed/${id}`, { method: "DELETE" })
        .then(getNewData)
        .catch((e) => alert(e));
      return;
    }
    console.log("UpdateMe: ", toUpdate);
    fetch("/api/feed", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id,
        title: "자동추가",
        content: toUpdate,
        author: "관리자",
      }),
    }).then(() => {
      getNewData();
    });
  };

  console.log(newData);
  return (
    <div className="Admin">
      <div>Admin</div>
      <div className="options">
        {orgDatas.map((orgData) => (
          <AdminBlock
            orgData={orgData}
            newData={newData}
            updateFeed={updateFeed}
          />
        ))}
      </div>
    </div>
  );
}

export default Admin;
