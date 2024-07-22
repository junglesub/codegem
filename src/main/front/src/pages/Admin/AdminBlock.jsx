import React, { useEffect, useState } from "react";
import CompareTwo from "./CompareTwo";

function AdminBlock({ orgData, newData, updateFeed }) {
  const [blockNewData, setBlockNewData] = useState(null);

  const newDataInfo = newData[orgData.id];
  useEffect(() => {
    setBlockNewData(newDataInfo?.content || orgData.message);
  }, [newDataInfo, orgData.message]);
  return (
    <div className="AdminBlock">
      <div>
        <b>{newData[orgData.id] ? "Published" : "Draft"}</b> - {orgData.id}
      </div>
      <CompareTwo
        orgData={orgData}
        newData={newData}
        blockNewData={blockNewData}
        setBlockNewData={setBlockNewData}
      />
      <button
        className="saveBtn"
        onClick={() => {
          updateFeed(orgData.id, blockNewData);
        }}
        disabled={newData[orgData.id]}
      >
        저장
      </button>
      <button
        className="saveBtn"
        onClick={() => {
          updateFeed(orgData.id, null);
        }}
        disabled={!newData[orgData.id]}
      >
        삭제
      </button>
    </div>
  );
}

export default AdminBlock;
