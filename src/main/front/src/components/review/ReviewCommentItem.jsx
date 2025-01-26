import { useEffect, useRef, useState } from "react";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import { deepOrange, green } from "@mui/material/colors";
import FavoriteIcon from "@mui/icons-material/Favorite";
import DeleteIcon from "@mui/icons-material/Delete";
import {
  convertTextToLinks,
  formatRelativeOrAbsoluteTimestamp,
} from "../../tools/tools";
import ReactShowMoreText from "react-show-more-text";

import MarkdownPreview from "@uiw/react-markdown-preview";

import "../FeedCard.css";
import { Link, Skeleton, Typography } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";
import { useFetchBe, useFetchGh } from "../../tools/api";
import { useRecoilValue, useSetRecoilState } from "recoil";
import ShareModal from "../modals/ShareModal";
import HistoryModal from "../modals/HistoryModal";
import SolvedList from "../SolvedList";
import { feedCountAtom } from "../../recoil/feedAtom";
import { userDetailAtom } from "../../recoil/userAtom";

export default function ReviewCommentItem({
  loading,
  item,
  watchSeen = false,
  afterDelete,
}) {
  const fetchGh = useFetchGh();
  const userDetail = useRecoilValue(userDetailAtom);

  const [expanded, setExpanded] = useState(false);
  const openState = useState(false);

  // eslint-disable-next-line unused-imports/no-unused-vars
  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  const cardRef = useRef(null);

  if (!item) return <></>;

  return (
    <>
      <Card ref={cardRef} className="FeedCard" sx={{ my: 2 }} elevation={0}>
        {loading ? (
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
                  src={`https://avatars.githubusercontent.com/u/${item.user.id}`}
                ></Avatar>
              }
              action={
                <>
                  {item.user.id === userDetail.gh.id && (
                    <IconButton
                      aria-label="Share"
                      onClick={() => {
                        fetchGh(item.url, "delete").then(afterDelete);
                      }}
                    >
                      <DeleteIcon />
                    </IconButton>
                  )}
                </>
              }
              title={
                <Typography variant="body1">
                  <Link component={RouterLink} to={`/review/${item.user.id}`}>
                    {item.user.login}
                  </Link>
                </Typography>
              }
              subheader={
                <Typography variant="body2">
                  {formatRelativeOrAbsoluteTimestamp(item.created_at)}{" "}
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
                pt: 1,
              }}
            >
              <MarkdownPreview
                style={{ backgroundColor: "transparent" }}
                source={item.body}
                rehypeRewrite={(node, index, parent) => {
                  if (
                    node.tagName === "a" &&
                    parent &&
                    /^h(1|2|3|4|5|6)/.test(parent.tagName)
                  ) {
                    parent.children = parent.children.slice(1);
                  }
                }}
              />
            </CardContent>
          </>
        )}
      </Card>
    </>
  );
}
