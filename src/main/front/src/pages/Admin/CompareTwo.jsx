import React from "react";

function CompareTwo({ orgData, newData, blockNewData, setBlockNewData }) {
  return (
    <div className="CompareTwo" key={orgData.id}>
      <div className="mainBl original">
        <div className="title">원문</div>
        <div className="content">{orgData.message}</div>
      </div>
      <div className="mainBl after">
        <div className="title">수정본</div>
        <textarea
          className="content"
          onChange={(e) => setBlockNewData(e.target.value)}
          value={blockNewData}
          // contentEditable="True"
          // suppressContentEditableWarning={true}
          // onInput={(e) => setBlockNewData(e.currentTarget.textContent)}
        ></textarea>
      </div>
    </div>
  );
}

export default CompareTwo;
