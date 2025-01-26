import { useRecoilValue, useResetRecoilState } from "recoil";
import { authJwtAtom } from "../recoil/authAtom";

import React, { useEffect, useState } from "react";
import MainDisplay from "../components/MainDisplay";
import {
  Card,
  CardContent,
  Typography,
  TextField,
  Button,
  Box,
} from "@mui/material";
import { fetchBe, useFetchBe, useFetchGh } from "../tools/api";
import FeedCard from "../components/FeedCard";
import { ghOauthClientId } from "../constants";
import { userDetailAtom } from "../recoil/userAtom";
import { Link } from "react-router-dom";
import { getMaxTimestamp } from "../tools/tools";

const MainScreen = ({ noRedirect = false }) => {
  const authData = useRecoilValue(authJwtAtom);
  const userDetail = useRecoilValue(userDetailAtom);

  const fetchGh = useFetchGh();

  const [dashboardData, setDashBoardData] = useState(null);
  const [githubAllIssues, setGithubAllIssues] = useState(null);
  const logout = useResetRecoilState(authJwtAtom);
  // if (authData) return <>Welcome</>;

  useEffect(() => {
    // fetchBe("/solution/aggregateAllSolutions").then((doc) =>
    //   setDashBoardData(doc)
    // );

    fetchGh(
      `https://api.github.com/search/issues?q=${encodeURIComponent(
        `is:issue author:app/codegem-app`
      )}&sort=created&per_page=100`
    ).then((doc) =>
      setGithubAllIssues((prev) => {
        return doc.items.reduce(
          (prev, item) => {
            const kstDate = new Date(
              new Date(item.created_at).toLocaleString("en-US", {
                timeZone: "Asia/Seoul",
              })
            ).toLocaleDateString();

            const repo = item.repository_url.replace(
              "https://api.github.com/repos/",
              ""
            );
            const repoData = prev[kstDate]?.[repo] || [];
            return {
              ...prev,
              [kstDate]: {
                ...(prev[kstDate] || []),
                [repo]: [...repoData, { ...item, repo, kstDate }],
              },
            };
          },
          /*prev ||*/ {}
        );
      })
    );
  }, []);
  console.log(githubAllIssues);

  return (
    <>
      <MainDisplay>
        <Card sx={{ my: 2 }}>
          <CardContent>
            <Typography variant="h5" component="div" align="center">
              CodeGem
            </Typography>
            <Box display="flex" sx={{ mt: 3 }} gap={2} justifyContent="center">
              <Button component={Link} variant="contained" to="/repo">
                파일 추가
              </Button>
              <Box textAlign="center">
                {!authData ? (
                  <Button variant="contained" onClick={ghLogin}>
                    깃허브로 로그인
                  </Button>
                ) : (
                  <Button
                    variant="contained"
                    color="secondary"
                    onClick={logout}
                  >
                    로그아웃
                  </Button>
                )}
              </Box>
            </Box>
          </CardContent>
        </Card>

        {githubAllIssues ? (
          Object.keys(githubAllIssues)
            .sort()
            .reverse()
            .map((date) => {
              const sortedList = Object.values(githubAllIssues[date]).sort(
                (a, b) =>
                  getMaxTimestamp(a.map((item) => item.created_at)) >
                  getMaxTimestamp(b.map((item) => item.created_at))
              );
              return sortedList.map((items) => (
                <FeedCard key={`${date}-${items[0]?.repo}`} items={items} />
              ));
            })
        ) : (
          <FeedCard loading />
        )}
      </MainDisplay>
    </>
  );
};

export default MainScreen;
