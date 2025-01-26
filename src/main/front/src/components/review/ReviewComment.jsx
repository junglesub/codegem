import { Card, CardContent, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import ReviewCommentItem from "./ReviewCommentItem";
import { useFetchGh } from "../../tools/api";
import ReviewCommentNew from "./ReviewCommentNew";

function ReviewComment({ selectedIssue, refreshList }) {
  const fetchGh = useFetchGh();
  const [comments, setComments] = useState();
  const [loading, setLoading] = useState(true);
  const doGetComment = () => {
    fetchGh(selectedIssue.comments_url + "?per_page=100").then((doc) => {
      setComments(doc);
      setLoading(false);
    });
  };
  useEffect(() => {
    if (!selectedIssue) return;
    doGetComment({
      init: true,
    });
  }, [selectedIssue]);

  return (
    <Card sx={{ height: "100%", overflowY: "auto" }}>
      <CardContent>
        <Typography variant="h5" component="div" align="center">
          댓글
        </Typography>
        {comments &&
          comments.map((comment) => (
            <ReviewCommentItem
              key={comment.id}
              item={comment}
              loading={loading}
              afterDelete={() => {
                doGetComment();
                setTimeout(refreshList, 3000);
              }}
            />
          ))}
        <ReviewCommentNew
          selectedIssue={selectedIssue}
          afterCreate={() => {
            doGetComment();
            setTimeout(refreshList, 3000);
          }}
          loading={loading}
        />
      </CardContent>
    </Card>
  );
}

export default ReviewComment;
