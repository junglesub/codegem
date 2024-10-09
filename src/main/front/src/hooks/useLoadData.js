import { useState } from "react";
import { useFetchBe } from "../tools/api";
import { removeDuplicates } from "../tools/tools";
import { useFeedCount } from "./useFeed";

const useLoadData = ({ all } = {}) => {
  const fetch = useFetchBe();
  const [_, getCount] = useFeedCount();

  const [allFeeds, setAllFeed] = useState([]);
  const [hasMore, setHasMore] = useState(true);

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
            like: dd.like,
          })),
        ],
        "id"
      )
    );
    if (data.length === 0) setHasMore(false);

    // Also update watch seen data on init request
    if (lastTimestamp === -1) getCount();
  };

  return [allFeeds, hasMore, getData];
};

export default useLoadData;
