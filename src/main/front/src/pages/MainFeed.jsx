import { useEffect, useState } from "react";
import MainDisplay from "../components/MainDisplay";
import FeedCard from "../components/FeedCard";
import InfiniteScroll from "react-infinite-scroller";
import useLoadData from "../hooks/useLoadData";
import { useFeedCount } from "../hooks/useFeed";
import { useFetchBe } from "../tools/api";
import { useSetRecoilState } from "recoil";
import { feedCountAtom } from "../recoil/feedAtom";
import { Card, CardContent, Typography } from "@mui/material";

function MainFeed() {
  const fetch = useFetchBe();
  const setFeedCount = useSetRecoilState(feedCountAtom);
  const [allFeeds, hasMore, loadData] = useLoadData({ type: "unseen" });
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
  }, [hasMore, feedNumber, allFeeds, fetch, setFeedCount, doingBulkDelete]);

  return (
    <MainDisplay>
      {!hasMore && allFeeds.length === 0 && (
        <Card sx={{ my: 2 }}>
          <CardContent>
            <Typography variant="h5" component="div" align="center">
              모든 피드를 읽었어요!
            </Typography>
          </CardContent>
        </Card>
      )}
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
