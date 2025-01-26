import React, { useEffect, useState } from "react";
import { Box, Button, Card, CardContent, Typography } from "@mui/material";

import { ActionList } from "@primer/react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useRecoilValue } from "recoil";
import MainDisplay from "../components/MainDisplay";
import { userDetailAtom } from "../recoil/userAtom";
import { useFetchBe, useFetchGh } from "../tools/api";
import ReviewList from "../components/review/ReviewList";
import ReviewComment from "../components/review/ReviewComment";
import ReviewIssueContent from "../components/review/ReviewIssueContent";
import { se } from "date-fns/locale";

function ReviewScreenPage({ selectedFiles, setSelectedFiles, repo }) {
  const userDetail = useRecoilValue(userDetailAtom);
  const navigate = useNavigate();
  const fetchBe = useFetchBe();
  const fetchGh = useFetchGh();

  const { uid, issueId } = useParams();

  const [addedFiles, setAddedFiles] = useState([]);

  const [repoDetail, setRepoDetail] = useState({});
  const [selectedIssue, setSelectedIssue] = useState();
  const [allIssues, setAllIssues] = useState([]);
  const [allCommentedIssue, setAllCommentedIssue] = useState([]);

  const [loading, setLoading] = useState(false);

  const refreshList = () => {
    if (!repoDetail.repo) return;
    fetchGh(
      `https://api.github.com/search/issues?q=+${encodeURIComponent(
        `is:issue author:app/codegem-app repo:${repoDetail.repo} `
      )}&sort=created&per_page=100`
    ).then((doc) => {
      setAllIssues(doc.items);
      setSelectedIssue(doc.items.find((item) => `${item.number}` === issueId));
    });

    fetchGh(
      `https://api.github.com/search/issues?q=+${encodeURIComponent(
        `is:issue author:app/codegem-app repo:${repoDetail.repo} commenter:@me`
      )}&sort=created&per_page=100`
    ).then((doc) => setAllCommentedIssue(doc.items));
  };

  useEffect(() => {
    if (selectedIssue?.number)
      window.history.replaceState(
        null,
        undefined,
        `/review/${uid}/${selectedIssue.number}`
      );
  }, [selectedIssue]);

  useEffect(() => {
    fetchBe(`/tbuser/ghRepo/${uid}`).then((doc) => setRepoDetail(doc));
  }, [uid]);

  useEffect(() => {
    refreshList();
  }, [repoDetail.repo]);

  console.log(uid, repoDetail);
  console.log({ selectedIssue });
  return (
    <MainDisplay maxWidth={"100%"}>
      <Card>
        <CardContent sx={{ p: 3, "&:last-child": { pb: 3 } }}>
          <Box display="flex" sx={{}} gap={2} justifyContent="space-between">
            <Button component={Link} variant="contained" to="/">
              홈화면
            </Button>
            <Typography
              flexGrow={1}
              variant="h5"
              component="div"
              align="center"
            >
              피어리뷰
            </Typography>
          </Box>
        </CardContent>
      </Card>
      <Box
        display="flex"
        sx={{
          my: 2,
          gap: 1,
          flexDirection: {
            xs: "column",
            sm: "row",
          },
        }}
      >
        <Box sx={{ minWidth: { sm: 200 } }}>
          <ReviewList
            selectedIssue={selectedIssue}
            setSelectedIssue={setSelectedIssue}
            allIssues={allIssues}
            allCommentedIssue={allCommentedIssue}
          />
        </Box>
        <Box
          key={selectedIssue?.html_url}
          display="flex"
          sx={{
            flexGrow: 1,
            gap: 1,
            flexDirection: {
              xs: "column",
              lg: "row",
            },
            minWidth: 0,
            minHeight: "80vh",
          }}
        >
          {!selectedIssue && !issueId ? (
            <Card sx={{ flexGrow: 1, p: 3 }}>
              <Typography align="center" variant="h4">
                왼쪽에서 선택해주세요!
              </Typography>
            </Card>
          ) : (
            <>
              <Box sx={{ flexGrow: 1, minWidth: 0 }}>
                <ReviewIssueContent
                  selectedIssue={selectedIssue}
                  repoDetail={repoDetail}
                />
              </Box>
              <Box
                minWidth={{
                  xs: "100%",
                  lg: 400,
                }}
                height={{ lg: 0 }}
                minHeight={{ lg: "100%" }}
                overflow={{ lg: "auto" }}
              >
                <ReviewComment
                  selectedIssue={selectedIssue}
                  refreshList={refreshList}
                />
              </Box>
            </>
          )}
        </Box>
      </Box>
    </MainDisplay>
  );
}

export default ReviewScreenPage;
