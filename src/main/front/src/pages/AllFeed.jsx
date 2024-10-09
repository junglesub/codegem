import MainDisplay from "../components/MainDisplay";
import FeedCard from "../components/FeedCard";
import InfiniteScroll from "react-infinite-scroller";
import useLoadData from "../hooks/useLoadData";

function AllFeed() {
  const [allFeeds, hasMore, loadData] = useLoadData();

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
          // <FeedItemNew
          //   key={item.id}
          //   item={item}
          //   setAllSeenFeedId={setAllSeenFeedId}
          // />
          <FeedCard key={item.id} item={item} />
        ))}
      </InfiniteScroll>
    </MainDisplay>
  );
}

export default AllFeed;
