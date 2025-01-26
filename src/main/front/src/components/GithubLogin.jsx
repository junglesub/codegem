import { useRecoilValue, useResetRecoilState } from "recoil";
import { authJwtAtom } from "../recoil/authAtom";

import React, { useEffect } from "react";
import { Button, Box } from "@mui/material";
import { ghOauthClientId } from "../constants";
import { useNavigate } from "react-router-dom";

const GithubLogin = ({ noRedirect = false }) => {
  const authData = useRecoilValue(authJwtAtom);
  const logout = useResetRecoilState(authJwtAtom);
  const navigate = useNavigate();
  // if (authData) return <>Welcome</>;

  useEffect(() => {
    if (!!authData && !noRedirect) navigate("/");
  }, [authData]);

  const ghLogin = () => {
    const myWindow = window.open(
      `https://github.com/login/oauth/authorize?client_id=${ghOauthClientId}&redirect_uri=${window.location.origin}/api/tbuser/gh`,
      "_blank",
      "width=600,height=800"
    );

    var timer = setInterval(function () {
      if (myWindow.closed) {
        clearInterval(timer);
        // alert("closed");
      }
    }, 1000);
  };

  return (
    <Box textAlign="center" sx={{ mt: 3 }}>
      {!authData ? (
        <Button variant="contained" onClick={ghLogin}>
          깃허브로 로그인
        </Button>
      ) : (
        <Button variant="contained" color="secondary" onClick={logout}>
          로그아웃
        </Button>
      )}
    </Box>
  );
};

export default GithubLogin;
