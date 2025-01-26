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
} from "../../tools/tools";
import ReactShowMoreText from "react-show-more-text";

import MarkdownPreview from "@uiw/react-markdown-preview";

import "../FeedCard.css";
import { Button, Link, Skeleton, Typography } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";
import { useFetchBe, useFetchGh } from "../../tools/api";
import { useRecoilValue, useSetRecoilState } from "recoil";
import ShareModal from "../modals/ShareModal";
import HistoryModal from "../modals/HistoryModal";
import SolvedList from "../SolvedList";
import { feedCountAtom } from "../../recoil/feedAtom";
import CommentBoxUi from "../CommentBoxUi";
import { userDetailAtom } from "../../recoil/userAtom";

export default function ReviewCommentNew({
  loading,
  item,
  watchSeen = false,
  afterCreate,
  selectedIssue,
}) {
  const userDetail = useRecoilValue(userDetailAtom);
  const [expanded, setExpanded] = useState(false);
  const [additionalContent, setAdditionalContent] = useState("");
  const [writeLoading, setWriteLoading] = useState(false);

  const fetchGh = useFetchGh();

  // eslint-disable-next-line unused-imports/no-unused-vars
  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  const cardRef = useRef(null);

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
                  src={`https://avatars.githubusercontent.com/u/${userDetail.gh.id}`}
                ></Avatar>
              }
              title={
                <Typography variant="body1">
                  <Link
                    component={RouterLink}
                    to={`/review/${userDetail.gh.id}`}
                  >
                    {userDetail.gh.login}
                  </Link>
                </Typography>
              }
              // subheader={
              //   <Typography variant="body2">
              //     {formatRelativeOrAbsoluteTimestamp(item.created_at)}{" "}
              //   </Typography>
              // }
              sx={{
                pb: 0,
              }}
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
              <CommentBoxUi
                additionalContent={additionalContent}
                setAdditionalContent={setAdditionalContent}
                placeholder="혹시 코드에서 개선할 점이나 더 효율적인 방법이 있다면 알려주세요."
              />
              <Button
                disabled={writeLoading}
                onClick={() => {
                  setWriteLoading(true);
                  fetchGh(selectedIssue.comments_url, "post", {
                    body: additionalContent,
                  })
                    .then(() => {
                      setAdditionalContent("");
                      afterCreate();
                    })
                    .finally(() => {
                      setWriteLoading(false);
                    });
                }}
              >
                등록
              </Button>
            </CardContent>
          </>
        )}
      </Card>
    </>
  );
}
