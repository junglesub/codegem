import { useRecoilValue, useResetRecoilState } from "recoil";
import { authJwtAtom } from "../recoil/authAtom";

import React from "react";
import MainDisplay from "../components/MainDisplay";
import { Card, CardContent, Typography } from "@mui/material";
import GithubLogin from "../components/GithubLogin";

const MainScreen = ({ noRedirect = false }) => {
  const authData = useRecoilValue(authJwtAtom);
  const logout = useResetRecoilState(authJwtAtom);
  // if (authData) return <>Welcome</>;
  return (
    <>
      <MainDisplay>
        <Card sx={{ my: 2 }}>
          <CardContent>
            <Typography variant="h5" component="div" align="center">
              깃허브로 시작하기
            </Typography>
            <GithubLogin />
          </CardContent>
        </Card>
        <Card sx={{ my: 2 }}>
          <CardContent>
            <Typography
              variant="h5"
              component="div"
              align="center"
              gutterBottom
            >
              시연영상
            </Typography>
            <iframe
              style={{ margin: "auto", display: "block" }}
              width="560"
              height="315"
              src="https://www.youtube.com/embed/17Xo9NdE4wM?si=lFr2HN5aaMq5l7cc"
              title="YouTube video player"
              frameBorder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
              referrerPolicy="strict-origin-when-cross-origin"
              allowFullScreen
            ></iframe>
          </CardContent>
        </Card>
      </MainDisplay>
    </>
  );
};

export default MainScreen;
