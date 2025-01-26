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
      </MainDisplay>
    </>
  );
};

export default MainScreen;
