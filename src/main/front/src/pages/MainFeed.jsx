import { useEffect, useState } from "react";
import MainDisplay from "../components/MainDisplay";
import FeedCard from "../components/FeedCard";
import InfiniteScroll from "react-infinite-scroller";
import useLoadData from "../hooks/useLoadData";
import { useFeedCount } from "../hooks/useFeed";
import { useFetchBe } from "../tools/api";
import { useSetRecoilState } from "recoil";
import { feedCountAtom } from "../recoil/feedAtom";

function MainFeed() {
  const fetch = useFetchBe();
  const setFeedCount = useSetRecoilState(feedCountAtom);
  const [allFeeds, hasMore, loadData] = useLoadData();
  const [feedNumber] = useFeedCount();
  const [doingBulkDelete, setDoingBulkDelete] = useState(false);

  useEffect(() => {
    if (doingBulkDelete || hasMore || feedNumber < 1) return;
    setDoingBulkDelete(true);
    console.log(feedNumber);
    allFeeds.slice(-feedNumber).map((item) => {
      console.log("bulk delete", item.id);
      fetch("/feeduser/seen", "POST", { subjectId: item.subjectId }).then(() =>
        setFeedCount(0)
      );
    });
  }, [hasMore, feedNumber, allFeeds, fetch, setFeedCount]);

  return (
    <MainDisplay>
      <InfiniteScroll
        loadMore={loadData}
        hasMore={hasMore}
        loader={Array(1)
          .fill()
          .map((_, index) => (
            <FeedCard key={index} loading />
          ))}
      >
        {allFeeds.map((item) => (
          <FeedCard key={item.id} item={item} watchSeen={true} />
        ))}
      </InfiniteScroll>
    </MainDisplay>
  );
}

export default MainFeed;
