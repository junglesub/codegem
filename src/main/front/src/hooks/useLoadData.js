import { useState } from "react";
import { useFetchBe } from "../tools/api";
import { removeDuplicates } from "../tools/tools";

const useLoadData = ({ all } = {}) => {
  const [allFeeds, setAllFeed] = useState([]);
  const [hasMore, setHasMore] = useState(true);
  const fetch = useFetchBe();

  const getData = async () => {
    const lastTimestamp = allFeeds.at(-1)?.sentAtEpoch || -1;
    const data = await fetch(
      `/kafeed/scrolllist?afterSentAt=${lastTimestamp}&all=${all ? "y" : "n"}`
    );
    if (!Array.isArray(data)) return;
    setAllFeed((prev) =>
      removeDuplicates(
        [
          ...prev,
          ...data.map((dd) => ({
            author: "실명카톡방2",
            sentAtEpoch: dd.sentAt,
            createdAt: new Date(dd.sentAt * 1000),
            id: dd.id,
            content: dd.message,
            files: dd.files,
            img: dd.files[0], // Temp just for testing
            subjectId: dd.subjectId,
          })),
        ],
        "id"
      )
    );
    if (data.length === 0) setHasMore(false);
  };

  return [allFeeds, hasMore, getData];
};

export default useLoadData;
