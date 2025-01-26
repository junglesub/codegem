import {
  Avatar,
  Box,
  Card,
  CardContent,
  CardHeader,
  Link,
  Skeleton,
  Typography,
} from "@mui/material";
import { ActionList } from "@primer/react";
import React, { useEffect, useState } from "react";
import MarkdownPreview from "@uiw/react-markdown-preview";
import { useFetchGh } from "../../tools/api";
import { formatRelativeOrAbsoluteTimestamp } from "../../tools/tools";

function ReviewIssueContent({ selectedIssue, repoDetail }) {
  const fetchGh = useFetchGh();

  const [issue, setIssue] = useState();

  useEffect(() => {
    if (!selectedIssue) return;
    if (selectedIssue.body) {
      // Normally result from search api has body
      setIssue(selectedIssue);
    } else {
      fetchGh(selectedIssue.url).then((doc) => setIssue(doc));
    }
  }, [selectedIssue]);

  // if (!selectedIssue) return <Card>Please select review</Card>;

  return (
    <Card key={selectedIssue?.url} sx={{ height: "100%" }}>
      {!selectedIssue || !issue ? (
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
              repoDetail?.githubUserId && (
                <Avatar
                  src={`https://avatars.githubusercontent.com/u/${repoDetail?.githubUserId}`}
                ></Avatar>
              )
            }
            title={
              <Typography variant="body1">
                {repoDetail.githubUsername}
              </Typography>
            }
            subheader={
              <Typography variant="body2">
                {formatRelativeOrAbsoluteTimestamp(issue.created_at)}{" "}
              </Typography>
            }
          />
          <CardContent>
            <Typography variant="h4" gutterBottom>
              {issue.title}
            </Typography>
            <MarkdownPreview
              style={{ backgroundColor: "transparent" }}
              source={issue.body}
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
            <Box sx={{ mt: 1 }}>
              Github 이슈:{" "}
              <Link
                href={issue.html_url}
                target="_blank"
                rel="noopener noreferrer"
              >
                {issue.html_url}
              </Link>
            </Box>
          </CardContent>
        </>
      )}
    </Card>
  );
}

export default ReviewIssueContent;
