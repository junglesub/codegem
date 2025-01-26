import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { useSetRecoilState } from "recoil";
import { authJwtAtom } from "../recoil/authAtom";

function GithubLoginCb() {
  const setAuthData = useSetRecoilState(authJwtAtom);
  const [msg, setMsg] = useState(
    "Login Screen. If this doesn't close, try again."
  );
  useEffect(() => {
    const code = new URLSearchParams(location.search).get("code");
    localStorage.setItem("code_gem_gh_token", JSON.stringify(code)); // Incase of strictmode error
    setAuthData(code);
    setMsg("Login Success. Close the window.");
    window.close();
  }, []);
  return <div>{msg}</div>;
}

export default GithubLoginCb;
