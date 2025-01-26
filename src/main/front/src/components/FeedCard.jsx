import { useEffect, useRef, useState } from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import { deepOrange, green } from "@mui/material/colors";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShareIcon from "@mui/icons-material/Share";
import {
  convertTextToLinks,
  formatRelativeOrAbsoluteTimestamp,
  getMaxTimestamp,
} from "../tools/tools";
import ReactShowMoreText from "react-show-more-text";

import "./FeedCard.css";
import FeedCardGallery from "./FeedCardGallery";
import { Link, Skeleton, Typography } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";
import { useFetchBe, useFetchGh } from "../tools/api";
import { useSetRecoilState } from "recoil";
import { feedCountAtom } from "../recoil/feedAtom";
import ShareModal from "./modals/ShareModal";
import HistoryModal from "./modals/HistoryModal";
import SolvedList from "./SolvedList";

export default function FeedCard({ loading, items, watchSeen = false }) {
  const fetchGh = useFetchGh();
  const [author, setAuthor] = useState();
  const [expanded, setExpanded] = useState(false);

  // eslint-disable-next-line unused-imports/no-unused-vars
  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  const cardRef = useRef(null);

  useEffect(() => {
    if (!items || items?.length == 0) return;
    fetchGh(
      `https://api.github.com/users/${items[0]?.repo.split("/")[0]}`,
      "get",
      null,
      {
        cache: "force-cache",
      }
    ).then((doc) => setAuthor(doc));
  }, [items]);

  return (
    <>
      <Card ref={cardRef} className="FeedCard" sx={{ my: 2 }}>
        {!author || loading ? (
          <>
            <CardHeader
              avatar={<Skeleton variant="circular" width={40} height={40} />}
              // action={<Skeleton variant="rectangular" width={24} height={24} />}
              title={<Skeleton width="80%" />}
              subheader={<Skeleton width="40%" />}
            />
            <Skeleton variant="rectangular" width="100%" height={150} />
            <CardContent>
              <Skeleton variant="text" width="90%" />
              <Skeleton variant="text" width="80%" />
              <Skeleton variant="text" width="95%" />
            </CardContent>
          </>
        ) : (
          <>
            <CardHeader
              avatar={
                <Avatar
                  src={`https://avatars.githubusercontent.com/u/${author.id}`}
                ></Avatar>
              }
              title={
                <Typography variant="body1">
                  <Link component={RouterLink} to={`/review/${author.id}`}>
                    {author.login}
                  </Link>
                  님이 문제를 풀었습니다!
                </Typography>
              }
              subheader={
                <Typography variant="body2">
                  {formatRelativeOrAbsoluteTimestamp(
                    getMaxTimestamp(items.map((item) => item.created_at))
                  )}{" "}
                </Typography>
              }
            />
            {/* <CardMedia sx={{ width: "100%" }}>
              {item.files && (
                <FeedCardGallery images={item.files} id={item.id} />
              )}
            </CardMedia> */}
            <CardContent
              sx={{
                whiteSpace: "pre-wrap",
                wordWrap: "break-word",
                wordBreak: "break-all",
                overflowWrap: "break-word",
                pt: 0,
              }}
            >
              {items.map((item) => (
                <SolvedList
                  key={item.id}
                  item={{ ...item, userId: author.id }}
                />
              ))}

              {/* <ReactShowMoreText lines={3} truncatedEndingComponent="">
                {convertTextToLinks(item.message?.trim())}
              </ReactShowMoreText> */}
            </CardContent>
            {/* <CardActions disableSpacing>
        <IconButton aria-label="add to favorites">
          <FavoriteIcon />
        </IconButton>
        <IconButton aria-label="share">
          <ShareIcon />
        </IconButton>
      </CardActions> */}
          </>
        )}
      </Card>
    </>
  );
}
