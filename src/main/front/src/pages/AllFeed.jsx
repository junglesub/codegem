import { Box, Button, CssBaseline, Typography } from "@mui/material";
import React, { useState } from "react";
import MyAppbar from "../components/MyAppbar";
import MainDrawer from "../components/MainDrawer";
import MainDisplay from "../components/MainDisplay";
import FeedCard from "../components/FeedCard";
import { useFetchBe } from "../tools/api";
import InfiniteScroll from "react-infinite-scroller";
import { removeDuplicates } from "../tools/tools";
import useLoadData from "../hooks/useLoadData";

function AllFeed() {
  const [allSeenFeedId, setAllSeenFeedId] = useState(new Set());

  const [allFeeds, hasMore, loadData] = useLoadData({ all: true });

  const allFeedsToDisplay = allFeeds.filter(
    (item) => !allSeenFeedId.has(item.subjectId)
  );

  return (
    <MainDisplay>
      <InfiniteScroll
        loadMore={loadData}
        hasMore={hasMore}
        loader={Array(3)
          .fill()
          .map((_, index) => (
            <FeedCard key={index} loading />
          ))}
      >
        {allFeedsToDisplay.map((item) => (
          // <FeedItemNew
          //   key={item.id}
          //   item={item}
          //   setAllSeenFeedId={setAllSeenFeedId}
          // />
          <FeedCard
            key={item.id}
            item={item}
            setAllSeenFeedId={setAllSeenFeedId}
          />
        ))}
      </InfiniteScroll>
    </MainDisplay>
  );
}

export default AllFeed;
